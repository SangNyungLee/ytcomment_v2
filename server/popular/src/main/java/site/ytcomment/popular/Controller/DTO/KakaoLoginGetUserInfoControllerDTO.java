package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.KakaoLoginGetUserInfoServiceDTO;

public class KakaoLoginGetUserInfoControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String accessToken;

        public KakaoLoginGetUserInfoServiceDTO.In to(){
            return KakaoLoginGetUserInfoServiceDTO.In.builder()
                    .accessToken(this.accessToken)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final String kakaoId;
        private final String KakaoNickname;

        public static KakaoLoginGetUserInfoControllerDTO.Out from(KakaoLoginGetUserInfoServiceDTO.Out out){
            return Out.builder()
                    .kakaoId(out.getKakaoId())
                    .KakaoNickname(out.getKakaoNickname())
                    .build();
        }
    }
}
