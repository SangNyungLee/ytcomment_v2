package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.DTO.PagingDTO;

@Getter
@Builder
public class 특정정렬OutDTO extends PagingDTO {
    private String 영상ID;
    private String 발행일자;
    private int 좋아요수;
    private int 댓글수;
}
