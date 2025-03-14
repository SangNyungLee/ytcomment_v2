package site.ytcomment.popular.Service.DTO.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.scrap.FindVideoLikeDbDTO;

public class FindVideoLikeServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In {
        private final String userId;
        private final String videoId;

        public FindVideoLikeDbDTO.In to() {
            return FindVideoLikeDbDTO.In.builder()
                    .userId(this.userId)
                    .videoId(this.videoId)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int count;
        private final String userId;
        private final String videoId;

        public static FindVideoLikeServiceDTO.Out from(FindVideoLikeDbDTO.Out out, String userId, String videoId) {
            return Out.builder()
                    .videoId(videoId)
                    .userId(userId)
                    .count(out.getCount())
                    .build();
        }

        public UserVideoLikeServiceDTO.In to() {
            return UserVideoLikeServiceDTO.In.builder()
                    .count(this.getCount())
                    .videoId(this.getVideoId())
                    .userId(this.getUserId())
                    .build();
        }
    }
}
