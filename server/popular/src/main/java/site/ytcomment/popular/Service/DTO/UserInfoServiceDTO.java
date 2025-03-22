package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.UserInfoDbDTO;

public class UserInfoServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;

        public UserInfoDbDTO.In to(){
            return UserInfoDbDTO.In.builder()
                    .userId(this.userId)
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
        private final int count;

        public static UserInfoServiceDTO.Out from(UserInfoDbDTO.Out out){
            return Out.builder()
                    .id(out.getId())
                    .userEmail(out.getUserEmail())
                    .userName(out.getUserName())
                    .social(out.getSocial())
                    .count(out.getCount())
                    .build();
        }
    }
}
