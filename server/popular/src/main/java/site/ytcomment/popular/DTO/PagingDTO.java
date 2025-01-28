package site.ytcomment.popular.DTO;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class PagingDTO {
    private int page;
}
