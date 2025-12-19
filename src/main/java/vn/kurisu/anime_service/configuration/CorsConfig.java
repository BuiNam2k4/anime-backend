package vn.kurisu.anime_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 1. Cho phép các nguồn (Domain) nào được gọi API?
        // Khi dev thì để "*" (cho phép tất cả).
        // Khi deploy production thì nên đổi thành domain cụ thể (ví dụ: "https://my-anime-web.vercel.app")
        corsConfiguration.setAllowedOrigins(java.util.List.of("*"));

        // 2. Cho phép các method nào? (GET, POST, PUT, DELETE,...)
        corsConfiguration.setAllowedMethods(java.util.List.of("*"));

        // 3. Cho phép các Header nào? (Authorization, Content-Type,...)
        corsConfiguration.setAllowedHeaders(java.util.List.of("*"));

        // 4. Cấu hình áp dụng cho mọi đường dẫn (/**)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
