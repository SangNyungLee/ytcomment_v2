package site.ytcomment.popular.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class TokenResponseDTO {
    private final String token;
    private final String userId;
}
