package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.DTO.PagingDTO;
import site.ytcomment.popular.Service.DTO.특정정렬OutDTO;

@Getter
@Builder
public class 특정정렬ResDTO extends PagingDTO {
    private String 영상ID;
    private String 발행일자;
    private int 좋아요수;
    private int 댓글수;

    public static 특정정렬ResDTO from(final 특정정렬OutDTO 입력){
        return 특정정렬ResDTO
                .builder()
                .영상ID(입력.get영상ID())
                .발행일자(입력.get발행일자())
                .좋아요수(입력.get좋아요수())
                .댓글수(입력.get댓글수())
                .build();
    }
}
