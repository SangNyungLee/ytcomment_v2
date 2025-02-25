package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.KakaoGetTokenServiceDTO;

public class KakaoGetTokenControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In{

        private final String code;

        public KakaoGetTokenServiceDTO.In to(){
            return KakaoGetTokenServiceDTO.In.builder()
                    .code(this.code)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{

        private final String token;
        private final String refresh_token;
        private final String id_token;

        public static KakaoGetTokenControllerDTO.Out from(KakaoGetTokenServiceDTO.Out kakaoGetTokenServiceDTOOut){
            return KakaoGetTokenControllerDTO.Out
                    .builder()
                    .id_token(kakaoGetTokenServiceDTOOut.getId_token())
                    .refresh_token(kakaoGetTokenServiceDTOOut.getRefresh_token())
                    .id_token(kakaoGetTokenServiceDTOOut.getId_token())
                    .build();
        }
    }
}
