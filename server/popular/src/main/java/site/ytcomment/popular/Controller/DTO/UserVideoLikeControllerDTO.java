package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.UserVideoLikeServiceDTO;

public class UserVideoLikeControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In{
        private final String videoId;

        public UserVideoLikeServiceDTO.In to(String userId) {
            return UserVideoLikeServiceDTO.In.builder()
                    .userId(userId)
                    .videoId(this.videoId)
                    .build();
        }
    }
}
