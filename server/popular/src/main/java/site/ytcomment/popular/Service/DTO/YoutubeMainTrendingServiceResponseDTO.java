package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class YoutubeMainTrendingServiceResponseDTO {
    private int page; // 페이지 위치
    private int newCategory; // 특정 카테고리 눌렀을 때 걔들만 가져오게 하는 값
}
