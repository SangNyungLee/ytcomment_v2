package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class EmailSignUpDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;
        private final String userEmail;
        private final String userPw;
        private final String userName;
        private final String social;
    }
}
