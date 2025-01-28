package site.ytcomment.popular.Repository.DTO;

import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.DTO.PagingDTO;

@Getter
@Builder
public class 영상목록조회요청DbDto extends PagingDTO {
    private String 구분코드; // 01 : 좋아요, 02 : 최신 ...
    private String 일자;
}
