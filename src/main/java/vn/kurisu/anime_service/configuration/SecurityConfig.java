package vn.kurisu.anime_service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINTS = {
            "/api/users", "/api/auth/token", "/api/auth/introspect", "/api/auth/logout", "/api/auth/refresh",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
    @Value("${jwt.signerKey}")
    private String signerKey;
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        // cho phep post vao duong dan dang nhap ma khong can token

                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2->oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

        return httpSecurity.build();

    }
    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(),"HS256");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS256).build();
   }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

