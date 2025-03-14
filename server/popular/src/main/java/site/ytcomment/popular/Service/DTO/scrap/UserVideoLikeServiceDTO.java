package site.ytcomment.popular.Service.DTO.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.scrap.UserVideoLikeDbDTO;

public class UserVideoLikeServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final int count;
        private final String userId;
        private final String videoId;

        public UserVideoLikeDbDTO.In to(){
            return UserVideoLikeDbDTO.In.builder()
                    .userId(this.userId)
                    .videoId(this.videoId)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final String result;

        public static UserVideoLikeServiceDTO.Out from(String result){
            return UserVideoLikeServiceDTO.Out.builder()
                    .result(result)
                    .build();
        }
    }
}
