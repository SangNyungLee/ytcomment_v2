package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Enum.SocialEnum;
import site.ytcomment.popular.mapper.DTO.EmailSignUpDbDTO;

public class EmailSignUpServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;
        private final String userEmail;
        private final String userPw;
        private final String userName;
        private final String social;

        public EmailSignUpDbDTO.In to(String pw){
            return EmailSignUpDbDTO.In
                    .builder()
                    .userId(this.userId)
                    .userEmail(this.userEmail)
                    .userPw(pw)
                    .userName(this.userPw)
                    .social(SocialEnum.이메일.getCode())
                    .build();
        }
    }
}
