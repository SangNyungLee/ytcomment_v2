package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.KakaoLoginCheckUserDbDTO;

public class KakaoLoginCheckUserServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String KakaoId;
        private final String KakaoNickname;

        public KakaoLoginCheckUserDbDTO.In to(){
            return KakaoLoginCheckUserDbDTO.In.builder()
                    .KakaoId(this.KakaoId)
                    .KakaoNickname(this.KakaoNickname)
                    .build();
        }
    }
}
