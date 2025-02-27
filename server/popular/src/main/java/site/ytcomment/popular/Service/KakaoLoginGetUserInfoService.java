package site.ytcomment.popular.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import site.ytcomment.popular.Service.DTO.KakaoLoginGetUserInfoServiceDTO;

@Service
@RequiredArgsConstructor
public class KakaoLoginGetUserInfoService {

    private final WebClient.Builder webClientBuilder;

    public KakaoLoginGetUserInfoServiceDTO.Out GetLoginUserInfo(KakaoLoginGetUserInfoServiceDTO.In in){
        String result = webClientBuilder.baseUrl("https://kapi.kakao.com")
                .build()
                .post()
                .uri("/v2/user/me")
                .header("Authorization","bearer " + in.getAccessToken())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            JsonNode data = new ObjectMapper().readTree(result);
            return KakaoLoginGetUserInfoServiceDTO.Out
                    .builder()
                    .KakaoId(data.path("id").asText())
                    .KakaoNickname(data.path("properties").path("nickname").asText())
                    .build();
            // 여기서 Db에 회원 정보가 저장되어 있는 로직을 바로 작성하게 되면 SRP(단일책임원칙)위반이 되니까
            // 따로 Service를 구현해서 Db에 회원 정보를 조회 후 저장하는 로직을 작성하는게 좋을듯
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
