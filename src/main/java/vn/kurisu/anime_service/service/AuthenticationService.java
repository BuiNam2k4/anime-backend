package vn.kurisu.anime_service.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.kurisu.anime_service.dto.request.AuthenticationRequest;
import vn.kurisu.anime_service.dto.request.IntrospectRequest;
import vn.kurisu.anime_service.dto.request.LogoutRequest;
import vn.kurisu.anime_service.dto.request.RegisterRequest;
import vn.kurisu.anime_service.dto.response.AuthenticationResponse;
import vn.kurisu.anime_service.dto.response.UserResponse;
import vn.kurisu.anime_service.entity.InvalidatedToken;
import vn.kurisu.anime_service.entity.User;
import vn.kurisu.anime_service.exception.AppException;
import vn.kurisu.anime_service.exception.ErrorCode;
import vn.kurisu.anime_service.mapper.UserMapper;
import vn.kurisu.anime_service.repository.InvalidatedTokenRepository;
import vn.kurisu.anime_service.repository.UserRepository;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;
    private final InvalidatedTokenRepository invalidatedTokenRepository;

    public UserResponse register (RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.EXISTS_EXCEPTION);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }
    public AuthenticationResponse authenticate (AuthenticationRequest request){
        var user = userRepository.findByUsername(request.getUsername());

        if (user==null){
            throw new AppException(ErrorCode.NOT_FOUND);

        }

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
    private String buildScope (User user){
        if (user.getRoles() == null || user.getRoles().isEmpty())
            return "";
        // StringJoiner giúp nối chuỗi với dấu cách
        StringJoiner stringJoiner = new StringJoiner(" ");
        user.getRoles().forEach(stringJoiner::add);
        return stringJoiner.toString();
    }
    public AuthenticationResponse refreshToken(IntrospectRequest request) throws ParseException, JOSEException {
        // 1. Kiểm tra hiệu lực của token (Verify)
        var signedJWT = verifyToken(request.getToken());

        // 2. Lấy thông tin user từ token cũ
        String username = signedJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findByUsername(username);
        if (user == null)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        // 3. Tạo token mới (Renew)
        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
    public void logout (LogoutRequest request) throws ParseException, JOSEException {
        try{
            var signToken = verifyToken(request.getToken());
            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken= InvalidatedToken.builder()
                    .id(jit)
                    .expiryTime(expiryTime)
                    .build();
            invalidatedTokenRepository.save(invalidatedToken);
        }catch (AppException e){

        }
    }
    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        // Parse token từ chuỗi string
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Kiểm tra hạn sử dụng (Expired time)
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);



        // Nếu hết hạn HOẶC chữ ký sai -> Báo lỗi
        if (!(verified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }
    private String generateToken (User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("kurisu.vn")
                .issueTime(new Date())
                .expirationTime(
                        new Date(
                                Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                        )
                ).claim("userId",user.getId())
                .claim("scope", buildScope(user))
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject= new JWSObject(header, payload);

        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        }
        catch (JOSEException e){
            throw new RuntimeException("Cannot create token", e);
        }
    }
}
