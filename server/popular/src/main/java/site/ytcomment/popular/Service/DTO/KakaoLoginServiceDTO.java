package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class KakaoLoginServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String code;

        public KakaoLoginServiceDTO.In to(){
            return In.builder()
                    .code(this.code)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final String access_token;
        private final String refresh_token;
        private final String id_token;
    }
}
