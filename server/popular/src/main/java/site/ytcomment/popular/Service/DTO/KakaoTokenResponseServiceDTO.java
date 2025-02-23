package site.ytcomment.popular.Service.DTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class KakaoTokenResponseServiceDTO {
    private final String access_token;
    private final String refresh_token;
    private final String id_token;
}
