package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.DetailPageCommentServiceDTO;

import java.time.LocalDateTime;

public class DetailPageCommentControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In{
        private final String id;

        public DetailPageCommentServiceDTO.In to(){
            return DetailPageCommentServiceDTO.In
                    .builder()
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
        private final int likeCount;
        private final String textOriginal;
        private final String authorDisplayName;
        private final String authorProfileImageUrl;
        private final String publishedAt;

        public static Out from(DetailPageCommentServiceDTO.Out detailPageCommentServiceDTO){
            return Out.builder()
                    .idx(detailPageCommentServiceDTO.idx())
                    .id(detailPageCommentServiceDTO.id())
                    .likeCount(detailPageCommentServiceDTO.likeCount())
                    .textOriginal(detailPageCommentServiceDTO.textOriginal())
                    .authorDisplayName(detailPageCommentServiceDTO.authorDisplayName())
                    .authorProfileImageUrl(detailPageCommentServiceDTO.authorProfileImageUrl())
                    .publishedAt(detailPageCommentServiceDTO.publishedAt())
                    .build();
        }

    }
}
