package site.ytcomment.popular.Controller.DTO.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.scrap.UserVideoLikeServiceDTO;

public class UserVideoLikeControllerDTO {

    @Getter
    @Builder
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

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int result;
    }
}
