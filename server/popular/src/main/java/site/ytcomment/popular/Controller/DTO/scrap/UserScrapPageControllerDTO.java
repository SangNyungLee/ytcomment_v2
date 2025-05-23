package site.ytcomment.popular.Controller.DTO.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.scrap.UserScrapPageServiceDTO;
import site.ytcomment.popular.common.Enum.YoutubeCategory;

public class UserScrapPageControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In{
        private final int page;
        private final int size;

        public UserScrapPageServiceDTO.In to(String userId){
            return UserScrapPageServiceDTO.In.builder()
                    .page(this.page)
                    .size(this.size)
                    .userId(userId)
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
        private final String categoryId;
        private final int channelViewCount;
        private final int channelLikeCount;
        private final int channelCommentCount;
        private final String publishedAt;

        public static UserScrapPageControllerDTO.Out from(UserScrapPageServiceDTO.Out out){
            return Out.builder()
                    .id(out.getId())
                    .title(out.getTitle())
                    .channelTitle(out.getChannelTitle())
                    .thumbnails(out.getThumbnails())
                    // 카테고리ID(int) -> 카테고리 name(String)
                    .categoryId(YoutubeCategory.fromId(out.getCategoryId()).getName())
                    .channelViewCount(out.getChannelViewCount())
                    .channelLikeCount(out.getChannelLikeCount())
                    .channelCommentCount(out.getChannelCommentCount())
                    .publishedAt(out.getPublishedAt())
                    .build();
        }
    }
}
