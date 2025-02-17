package site.ytcomment.popular.Service.DTO;

import lombok.*;
import site.ytcomment.popular.mapper.DTO.CardDbDTO;

public class CardServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{

        private final int page;
        private final int newCategory;
        private final int startIndex;

        public CardDbDTO.In to(){
            return CardDbDTO.In.builder()
                    .page(this.page)
                    .newCategory(this.newCategory)
                    .startIndex(this.startIndex)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int idx;
        private final String id;
        private final String thumbnails;
        private final String channelTitle;
        private final String title;
        private final String description;
        private final String textOriginal;
        private final String channelId;
        private final int likeCount;

        // MyBatis의 결과 DTO -> outDTO 변환 메서드
        public static Out from(CardDbDTO.Out cardDbDTO){
            return Out.builder()
                    .idx(cardDbDTO.getIdx())
                    .id(cardDbDTO.getId())
                    .thumbnails(cardDbDTO.getThumbnails())
                    .channelTitle(cardDbDTO.getChannelTitle())
                    .title(cardDbDTO.getTitle())
                    .description(cardDbDTO.getDescription())
                    .textOriginal(cardDbDTO.getTextOriginal())
                    .channelId(cardDbDTO.getChannelId())
                    .likeCount(cardDbDTO.getLikeCount())
                    .build();
        }
    }

}
