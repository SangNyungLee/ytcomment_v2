package site.ytcomment.popular.Service.DTO.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.scrap.UserScrapPageDbDTO;

public class UserScrapPageServiceDTO {
    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;

        public UserScrapPageDbDTO.In to(){
            return UserScrapPageDbDTO.In.builder()
                    .userId(this.userId)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final String id;
        private final String title;
        private final String channelTitle;
        private final String thumbnails;
        //        private final LocalDateTime publishedAt;
        private final int categoryId;
        private final int channelViewCount;
        private final int channelLikeCount;
        private final int channelCommentCount;

        public static UserScrapPageServiceDTO.Out from(UserScrapPageDbDTO.Out out){
            return Out.builder()
                    .id(out.getId())
                    .title(out.getTitle())
                    .channelTitle(out.getChannelTitle())
                    .thumbnails(out.getThumbnails())
                    .categoryId(out.getCategoryId())
                    .channelViewCount(out.getChannelViewCount())
                    .channelLikeCount(out.getChannelLikeCount())
                    .channelCommentCount(out.getChannelCommentCount())
                    .build();
        }
    }
}
