package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.UserVideoLikeDbDTO;

public class UserVideoLikeServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;
        private final String videoId;

        public UserVideoLikeDbDTO.In to(){
            return UserVideoLikeDbDTO.In.builder()
                    .userId(this.userId)
                    .videoId(this.videoId)
                    .build();
        }
    }
}
