package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.KakaoLoginCheckUserServiceDTO;

public class KakaoLoginCheckUserControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{

        private final String kakaoId;
        private final String kakaoNickname;

        public KakaoLoginCheckUserServiceDTO.In to(){
            return KakaoLoginCheckUserServiceDTO.In.builder()
                    .kakaoId(this.kakaoId)
                    .kakaoNickname(this.kakaoNickname)
                    .build();
        }
    }
}
