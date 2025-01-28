package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import site.ytcomment.popular.DTO.PagingDTO;
import site.ytcomment.popular.Service.DTO.특정정렬InDTO;

@Getter
@Builder
public class 특정정렬ReqDTO extends PagingDTO {
    private String 구분코드; // 01 : 좋아요, 02 : 최신 ...
    private String 일자;

    public 특정정렬InDTO to특정정렬InDTO(){
        return 특정정렬InDTO
                .builder()
                .구분코드(this.구분코드)
                .일자(this.일자)
                .build();
    }
}
