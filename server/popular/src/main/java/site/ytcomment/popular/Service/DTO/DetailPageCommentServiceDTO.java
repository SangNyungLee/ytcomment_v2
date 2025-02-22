package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.DetailPageCommentDbDTO;

public class DetailPageCommentServiceDTO {

    @Builder
    public record In(String id){

        public DetailPageCommentDbDTO.In to(String id){
            return DetailPageCommentDbDTO.In.builder()
                    .id(id)
                    .build();
        }
    }
    
    @Builder
    public record Out(int idx, String id, int likeCount, String textOriginal, String authorDisplayName,
                             String authorProfileImageUrl, String publishedAt){

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
