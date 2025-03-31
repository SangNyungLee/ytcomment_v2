package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class LoginAuthDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final String userPw;
        private final String userAuth;
        private final String userEmail;
    }
}
