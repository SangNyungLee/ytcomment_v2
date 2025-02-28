package site.ytcomment.popular.Controller.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.EmailSignUpServiceDTO;

public class EmailSignUpControllerDTO {

    @Getter
    @Builder
//    @RequiredArgsConstructor
    public static class In{
        private final String userId;
        private final String userEmail;
        private final String userPw;
        private final String userName;
        private final String social;

        @JsonCreator
        public In(
                @JsonProperty("userId") String userId, @JsonProperty("userEmail") String userEmail,
                @JsonProperty("userPw") String userPw, @JsonProperty("userName") String userName,
                @JsonProperty("social") String social) {
            this.userId = userId;
            this.userEmail = userEmail;
            this.userPw = userPw;
            this.userName = userName;
            this.social = social;
        }

        public EmailSignUpServiceDTO.In to(){
            return EmailSignUpServiceDTO.In
                    .builder()
                    .userId(this.userId)
                    .userEmail(this.userEmail)
                    .userPw(this.userPw)
                    .userName(this.userName)
                    .social(this.social)
                    .build();
        }
    }
}
