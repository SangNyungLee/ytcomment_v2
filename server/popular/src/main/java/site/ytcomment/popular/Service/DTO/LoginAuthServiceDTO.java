package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.LoginAuthDbDTO;

public class LoginAuthServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;
        private final String userPw;

        public LoginAuthDbDTO.In to(){
            return LoginAuthDbDTO.In.builder()
                    .userId(this.userId)
                    .build();
        }
    }
}
