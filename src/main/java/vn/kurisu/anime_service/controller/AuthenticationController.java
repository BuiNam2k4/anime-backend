package vn.kurisu.anime_service.controller;

import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.kurisu.anime_service.dto.request.AuthenticationRequest;
import vn.kurisu.anime_service.dto.request.IntrospectRequest;
import vn.kurisu.anime_service.dto.request.LogoutRequest;
import vn.kurisu.anime_service.dto.request.RegisterRequest;
import vn.kurisu.anime_service.dto.response.ApiResponse;
import vn.kurisu.anime_service.dto.response.AuthenticationResponse;
import vn.kurisu.anime_service.dto.response.UserResponse;
import vn.kurisu.anime_service.entity.User;
import vn.kurisu.anime_service.service.AuthenticationService;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody @Valid RegisterRequest request){
        return ApiResponse.<UserResponse>builder()
                .message("Dang ky thanh cong!")
                .result(authenticationService.register(request))
                .build();
    }
    @PostMapping("/logout")
    public ApiResponse<Void> logout (@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .message("Dang xuat thanh cong")
                .build();
    }
    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh (@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(introspectRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();

    }
    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .message("Dang nhap thanh cong")
                .build();
    }
}
