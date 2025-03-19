package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.UserInfoServiceDTO;

public class UserInfoControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;

        public static UserInfoServiceDTO.In to(String userId){
            return UserInfoServiceDTO.In.builder()
                    .userId(userId)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final String id;
        private final String userEmail;
        private final String userName;
        private final String social;

        public static UserInfoControllerDTO.Out from(UserInfoServiceDTO.Out out){
            return Out.builder()
                    .id(out.getId())
                    .userEmail(out.getUserEmail())
                    .userEmail(out.getUserEmail())
                    .social(out.getSocial())
                    .build();
        }
    }
}
