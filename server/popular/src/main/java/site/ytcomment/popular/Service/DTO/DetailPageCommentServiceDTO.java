package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.DetailPageCommentDbDTO;

public class DetailPageCommentServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{

        private final String id;

        public DetailPageCommentDbDTO.In to(){
            return DetailPageCommentDbDTO.In.builder()
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

        // DbDTO -> Service로 변환하는 메서드
        public static Out from(DetailPageCommentDbDTO.Out detailPageDbDTOOut){
            return Out.builder()
                    .idx(detailPageDbDTOOut.getIdx())
                    .id(detailPageDbDTOOut.getId())
                    .likeCount(detailPageDbDTOOut.getLikeCount())
                    .textOriginal(detailPageDbDTOOut.getTextOriginal())
                    .authorDisplayName(detailPageDbDTOOut.getAuthorDisplayName())
                    .authorProfileImageUrl(detailPageDbDTOOut.getAuthorProfileImageUrl())
                    // LocalDateTime으로 toList 만들면 배열로 와버려서 String으로 바꿔버렸음
                    .publishedAt(detailPageDbDTOOut.getPublishedAt().toString())
                    .build();
        }
    }
}
