package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {
    private final WebClient.Builder webClientBuilder;

    @Value("${kakao.javaScript.api.key}")
    private String apiKey;
    @Value("${kakao.api.url}")
    private String apiUrl;
    @Value("${kakao.redirect.url}")
    private String redirectUrl;


    // 인증 URL 생성
    public String getAuthorizeUrl(){
        return apiUrl +
                "?response_type=code"
                + "&client_id=" + apiKey
                + "&redirect_uri=" + redirectUrl;
    }

    //Youtube로 api 요청 보내는 서비스
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
