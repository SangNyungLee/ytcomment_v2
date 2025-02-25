package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import site.ytcomment.popular.Service.DTO.KakaoGetTokenServiceDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoGetTokenService {

    private final WebClient.Builder webclientBuilder;

    @Value("${kakao.api.key}")
    private String client_id;
    @Value("${kakao.redirect.url}")
    private String redirect_uri;

    public KakaoGetTokenServiceDTO.Out getToken(KakaoGetTokenServiceDTO.In in) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", client_id);
        formData.add("redirect_uri", redirect_uri);
        formData.add("code", in.getCode());

        //"Content-Type", "application/x-www-form-urlencoded;charset=utf-8"
        try {
            return webclientBuilder.baseUrl("https://kauth.kakao.com")
                    .build()
                    .post()
                    .uri("/oauth/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(KakaoGetTokenServiceDTO.Out.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("카카오 토큰 요청 실패. status: {}, Response : {} ", e.getStatusCode(), e.getResponseBodyAsString());
            throw new RuntimeException("카카오 토큰 요청 실패", e);
        }

    }
}
