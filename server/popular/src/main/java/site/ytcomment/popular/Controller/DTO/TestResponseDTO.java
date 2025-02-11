package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.DTO.Test1;
import site.ytcomment.popular.DTO.Test2;
import site.ytcomment.popular.DTO.Test3;

@Getter
@Builder
public class TestResponseDTO {
    private final Test1 test1;
    private final Test2 test2;
    private final Test3 test3;
}
