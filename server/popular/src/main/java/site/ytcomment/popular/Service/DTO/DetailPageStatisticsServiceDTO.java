package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.DetailPageStatisticsDbDTO;

import java.time.LocalDateTime;

public class DetailPageStatisticsServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String id;

        public DetailPageStatisticsDbDTO.In to(){
            return DetailPageStatisticsDbDTO.In.builder()
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

        public static DetailPageStatisticsServiceDTO.Out from(DetailPageStatisticsDbDTO.Out detailPageStatisticsDbDTO){
            return Out
                    .builder()
                    .idx(detailPageStatisticsDbDTO.getIdx())
                    .id(detailPageStatisticsDbDTO.getId())
                    .channelViewCount(detailPageStatisticsDbDTO.getChannelViewCount())
                    .channelFavoriteCount(detailPageStatisticsDbDTO.getChannelFavoriteCount())
                    .channelCommentCount(detailPageStatisticsDbDTO.getChannelCommentCount())
                    .channelLikeCount(detailPageStatisticsDbDTO.getChannelLikeCount())
                    .publishedAt(detailPageStatisticsDbDTO.getPublishedAt().toString())
                    .build();
        }
    }
}
