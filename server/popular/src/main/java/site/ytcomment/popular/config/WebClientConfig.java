package site.ytcomment.popular.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebClientConfig implements WebMvcConfigurer {

    // 비동기 요청을 위한 WebClient 추가
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    // CORS 설정 -> spring Security에서 설정해서 지워줘도 됨
    // 만약에 spring security없으면 이거 설정해줘야되고
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:5173")
//                .allowedHeaders("*")
//                .allowCredentials(true)
//                .maxAge(3600);
//    }
}
