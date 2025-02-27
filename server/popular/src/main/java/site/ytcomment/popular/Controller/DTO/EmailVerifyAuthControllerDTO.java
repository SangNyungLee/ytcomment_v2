package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.EmailVerifyAuthServiceDTO;

public class EmailVerifyAuthControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In {
        private final String email;
        private final String authNum;


        public EmailVerifyAuthServiceDTO.In to(){
            return EmailVerifyAuthServiceDTO.In
                    .builder()
                    .email(this.email)
                    .authNum(this.authNum)
                    .build();
        }
    }
}
