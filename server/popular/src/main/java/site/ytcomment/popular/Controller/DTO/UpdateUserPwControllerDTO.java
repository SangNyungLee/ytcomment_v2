package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.LoginAuthServiceDTO;
import site.ytcomment.popular.Service.DTO.UpdateUserPwServiceDTO;

public class UpdateUserPwControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In{
        private final String currentPassword;
        private final String confirmPassword;

        public UpdateUserPwServiceDTO.In to(String result, String userId){
            return UpdateUserPwServiceDTO.In.builder()
                    .result(result)
                    .userId(userId)
                    .currentPassword(this.currentPassword)
                    .confirmPassword(this.confirmPassword)
                    .build();
        }

        public LoginAuthServiceDTO.In toLoginAuth(String userId){
            return LoginAuthServiceDTO.In.builder()
                    .userId(userId)
                    .userPw(this.currentPassword)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int result;

        public static UpdateUserPwControllerDTO.Out from(UpdateUserPwServiceDTO.Out out){
            return Out.builder()
                    .result(out.getResult())
                    .build();
        }
    }
}
