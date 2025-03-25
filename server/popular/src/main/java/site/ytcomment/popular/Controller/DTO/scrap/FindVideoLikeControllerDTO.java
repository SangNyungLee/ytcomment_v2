package site.ytcomment.popular.Controller.DTO.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.scrap.FindVideoLikeServiceDTO;
import site.ytcomment.popular.Service.DTO.scrap.UserVideoLikeServiceDTO;

public class FindVideoLikeControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In{
        private final String videoId;
        public FindVideoLikeServiceDTO.In to(String userId) {
            return FindVideoLikeServiceDTO.In.builder()
                    .userId(userId)
                    .videoId(this.videoId)
                    .build();
        }

        public FindVideoLikeServiceDTO.In toFindVideo(){
            return FindVideoLikeServiceDTO.In.builder()
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

        public static FindVideoLikeControllerDTO.Out from(FindVideoLikeServiceDTO.Out out){
            return FindVideoLikeControllerDTO.Out.builder()
                    .userId(out.getUserId())
                    .videoId(out.getVideoId())
                    .count(out.getCount())
                    .build();
        }

        public UserVideoLikeServiceDTO.In to(){
            return UserVideoLikeServiceDTO.In.builder()
                    .count(this.count)
                    .userId(this.userId)
                    .videoId(this.videoId)
                    .build();
        }
    }
}
