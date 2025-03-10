package site.ytcomment.popular.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
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
}
