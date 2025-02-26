package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.KakaoLoginServiceDTO;

public record KakaoLoginControllerDTO(String token) {
//    private final String token;

    /* 필드에 final을 사용하고 싶은데 @RequestBody를 사용해서 객체에 매핑시키려고 하면 Jackson에서 오류가 생김
     * @RequiredArgsConstructord와 @Builder를 사용하면 Jackson이 적절한 기본 생성자를 찾지 못해서 사용을 못함
     * 그럼 @NoArgs, @AllArgs를 추가해야되는데 final을 꼭 사용하고 싶으면 다음과 같은 방법으로 해결해야됨
     * @JsonCreator = Jackson한테 이 생성자를 사용해서 객체를 만들라고 알려줌
     * @JsonProperty = 지정한 값을 Java필드와 매칭시킴
     */
//    @JsonCreator
//    public KakaoLoginControllerDTO(@JsonProperty("Token") String token) {
//        this.token = token;
//    }

    @Builder
    public record In(String token){
        public KakaoLoginControllerDTO.In to(String code){
            return In.builder()
                    .token(code)
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

        public static KakaoLoginControllerDTO.Out from(KakaoLoginServiceDTO.Out kakaoLoginServiceDTOOut){
            return Out.builder()
                    .access_token(kakaoLoginServiceDTOOut.getAccess_token())
                    .refresh_token(kakaoLoginServiceDTOOut.getRefresh_token())
                    .id_token(kakaoLoginServiceDTOOut.getId_token())
                    .build();
        }
    }
}
