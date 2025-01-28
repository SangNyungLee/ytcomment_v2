package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.DTO.PagingDTO;
import site.ytcomment.popular.Repository.DTO.영상목록조회요청DbDto;

@Getter
@Builder
public class 특정정렬InDTO extends PagingDTO {
    private String 구분코드; // 01 : 좋아요, 02 : 최신 ...
    private String 일자;

    public 영상목록조회요청DbDto to영상목록조회요청DbDto(){
        return 영상목록조회요청DbDto
                .builder()
                .구분코드(this.구분코드)
                .일자(this.일자)
                .build();
    }

}
