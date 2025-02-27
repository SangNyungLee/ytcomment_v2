package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class KakaoLoginCheckUserDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String kakaoId;
        private final String kakaoNickname;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final String idx;
        private final String id;
        private final String userName;
        private final String userPw;
        private final String social;
    }
}
