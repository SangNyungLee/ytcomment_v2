package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailCheckReqServiceDTO {
    private String setForm;
    private String toMail;
    private String title;
    private String content;
}
