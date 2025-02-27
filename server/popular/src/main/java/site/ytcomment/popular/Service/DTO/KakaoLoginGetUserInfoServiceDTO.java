package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class KakaoLoginGetUserInfoServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String accessToken;

        public KakaoLoginGetUserInfoServiceDTO.In to(){
            return In
                    .builder()
                    .accessToken(this.accessToken)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{

        private final String KakaoId;
        private final String KakaoNickname;

        // DB 등 변환할 값이 없기 때문에 굳이 from 메서드를 넣어줄 필요는 없을듯?
    }
}
