package site.ytcomment.popular.Controller.DTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class KakaoLoginTestDTO {
    private final String code;
}
