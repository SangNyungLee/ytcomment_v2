package site.ytcomment.popular.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import site.ytcomment.popular.config.jwt.JwtAuthenticationFilter;
import site.ytcomment.popular.config.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig, JwtTokenProvider jwtTokenProvider) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("api/trending", "/api/auth/**",
//                                "/api/login", "/api/register", "/api/email/check-email"
//                        , "/api/totalPage").permitAll()
//                        .requestMatchers("/api/**").authenticated()
                        // TODO sanglee 2025.03.12 특정 페이지들만 나중에 authenticated 적용하기
                        .requestMatchers("/.git/**", "/.env").denyAll() // 봇 공격 차단
                        .anyRequest().permitAll()) // 인증 필요
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호를 해싱할 때 내부적으로 salt를 자동으로 생성하여 보안성을 높여줌
        return new BCryptPasswordEncoder();
    }

}