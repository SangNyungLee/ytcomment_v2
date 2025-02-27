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

        private final String KakaoId;
        private final String KakaoNickname;

        public KakaoLoginCheckUserServiceDTO.In to(){
            return KakaoLoginCheckUserServiceDTO.In.builder()
                    .KakaoId(this.KakaoId)
                    .KakaoNickname(this.KakaoNickname)
                    .build();
        }
    }
}
