package site.ytcomment.popular.Controller.DTO;

import lombok.*;
import site.ytcomment.popular.Service.DTO.CardServiceDTO;

public class CardControllerDTO {

    /*
    * NoArgsConstructor을 사용해준 이유?
    * Json -> Java객체로 변환할 때 (@RequestBody에서 Json을 객체로 변환)
    * (Deserialization) Jackson이 객체를 생성하는 방식 때문에 필요함
    * 따로 라이브러리를 사용하거나 역직렬화를 구현해야하는데 난 그냥 기본생성자 생성해줬음
    * */
    @Getter
    @Builder
    @NoArgsConstructor(force = true) // force = true : 기본 생성자를 만들면서, final 필드를 강제로 기본값(null, 0)으로 초기화함
    @RequiredArgsConstructor
    public static class In{
        private final int page;
        private final int categoryId;

        // CardController.In을 CardService.In으로 바꿔주는 to 메서드
        public CardServiceDTO.In to(){
            return CardServiceDTO.In
                    .builder()
                    .page(this.page)
                    .categoryId(this.categoryId)
                    // 한 페이지당 12개씩 가져올거임, 근데 이거 여기에 써도 되나?
                    .startIndex((this.page - 1) * 12)
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

        // from 메서드를 사용해서 CardServiceDTO.Out을 CardControllerDTO.Out으로 바꾸는 from 메서드
        public static CardControllerDTO.Out from(CardServiceDTO.Out cardServiceDTO){
            return CardControllerDTO.Out.builder()
                    .idx(cardServiceDTO.getIdx())
                    .id(cardServiceDTO.getId())
                    .thumbnails(cardServiceDTO.getThumbnails())
                    .channelTitle(cardServiceDTO.getChannelTitle())
                    .title(cardServiceDTO.getTitle())
                    .description(cardServiceDTO.getDescription())
                    .textOriginal(cardServiceDTO.getTextOriginal())
                    .channelId(cardServiceDTO.getChannelId())
                    .likeCount(cardServiceDTO.getLikeCount())
                    .build();
        }
    }
}
