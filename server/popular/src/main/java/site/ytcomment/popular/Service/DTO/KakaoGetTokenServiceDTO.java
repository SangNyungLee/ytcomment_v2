package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class KakaoGetTokenServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String code;

        public KakaoLoginServiceDTO.In to(){
            return KakaoLoginServiceDTO.In.builder()
                    .code(this.code)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class Out{
        private final String access_token;
        private final String refresh_token;
        private final String id_token;

        public static KakaoGetTokenServiceDTO.Out from(KakaoGetTokenServiceDTO.Out out){
            return Out.builder()
                    .access_token(out.getAccess_token())
                    .refresh_token(out.getRefresh_token())
                    .id_token(out.getId_token())
                    .build();
        }
    }
}
