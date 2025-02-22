package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {
    private final WebClient.Builder webClientBuilder;

    @Value("${kakao.api.key}")
    private String apiKey;
    @Value("${kakao.api.url}")
    private String apiUrl;
    @Value("${kakao.redirect.url}")
    private String redirectUrl;

    public String getTokenRequest() {
        return webClientBuilder.baseUrl(apiUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("response_type", "code")
                        .queryParam("client_id", apiKey)
                        .queryParam("redirect_uri", redirectUrl)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
