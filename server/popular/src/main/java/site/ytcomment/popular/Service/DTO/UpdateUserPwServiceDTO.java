package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.UpdateUserPwDbDTO;

public class UpdateUserPwServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String result; // 비밀번호 비교 결과값
        private final String userId;
        private final String currentPassword;
        private final String confirmPassword;

        public UpdateUserPwDbDTO.In to(String encryptPassword){
            return UpdateUserPwDbDTO.In.builder()
                    .encryptConfirmPassword(encryptPassword)
                    .userId(this.userId)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int result;

        public static UpdateUserPwServiceDTO.Out from(int result){
            return UpdateUserPwServiceDTO.Out.builder()
                    .result(result)
                    .build();
        }
    }


}
