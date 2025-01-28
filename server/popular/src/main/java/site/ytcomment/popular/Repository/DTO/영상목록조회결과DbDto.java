package site.ytcomment.popular.Repository.DTO;

import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.DTO.PagingDTO;

@Getter
@Builder
public class 영상목록조회결과DbDto extends PagingDTO {
    private String 영상ID;
    private String 발행일자;
    private int 좋아요수;
    private int 댓글수;
}
