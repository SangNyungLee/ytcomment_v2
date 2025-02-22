package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;

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
        public KakaoLoginControllerDTO.In to(String token){
            return In.builder()
                    .token(token)
                    .build();
        }
    }
}
