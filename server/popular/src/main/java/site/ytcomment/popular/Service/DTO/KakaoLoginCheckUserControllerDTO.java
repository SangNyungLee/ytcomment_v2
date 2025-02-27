package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
