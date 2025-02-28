package site.ytcomment.popular.Controller.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.Service.DTO.LoginAuthServiceDTO;

public class LoginAuthControllerDTO {

    @Getter
    @Builder
    public static class In{
        private final String userId;
        private final String userPw;

        @JsonCreator
        public In(@JsonProperty("userId") String userId, @JsonProperty("userPw") String userPw) {
            this.userId = userId;
            this.userPw = userPw;
        }

        public LoginAuthServiceDTO.In to(){
            return LoginAuthServiceDTO.In.builder()
                    .userId(this.userId)
                    .userPw(this.userPw)
                    .build();
        }
    }
}
