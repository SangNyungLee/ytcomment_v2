package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.DetailPageCommentServiceDTO;
import site.ytcomment.popular.Service.DTO.DetailPageStatisticsServiceDTO;

import java.time.LocalDateTime;

public class DetailPageStatisticsControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In{
        private final String id;

        public DetailPageStatisticsServiceDTO.In to(){
            return DetailPageStatisticsServiceDTO.In.builder()
                    .id(this.id)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int idx;
        private final String id;
        private final int channelViewCount;
        private final int channelFavoriteCount;
        private final int channelCommentCount;
        private final int channelLikeCount;
        private final String publishedAt;

        public static DetailPageStatisticsControllerDTO.Out from(DetailPageStatisticsServiceDTO.Out detailPageStatisticsServiceDTO){
            return Out.builder()
                    .idx(detailPageStatisticsServiceDTO.getIdx())
                    .id(detailPageStatisticsServiceDTO.getId())
                    .channelViewCount(detailPageStatisticsServiceDTO.getChannelViewCount())
                    .channelFavoriteCount(detailPageStatisticsServiceDTO.getChannelFavoriteCount())
                    .channelCommentCount(detailPageStatisticsServiceDTO.getChannelCommentCount())
                    .channelLikeCount(detailPageStatisticsServiceDTO.getChannelLikeCount())
                    .publishedAt(detailPageStatisticsServiceDTO.getPublishedAt())
                    .build();
        }

    }
}
