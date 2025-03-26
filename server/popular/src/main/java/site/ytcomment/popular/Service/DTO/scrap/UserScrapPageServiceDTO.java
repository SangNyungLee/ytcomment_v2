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
        private final int page;
        private final int size;

        public UserScrapPageDbDTO.In to(){
            return UserScrapPageDbDTO.In.builder()
                    .page(this.page)
                    .size(this.size)
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
        private final int categoryId;
        private final int channelViewCount;
        private final int channelLikeCount;
        private final int channelCommentCount;
        private final String publishedAt;

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
                    .publishedAt(out.getPublishedAt().toString())
                    .build();
        }
    }
}
