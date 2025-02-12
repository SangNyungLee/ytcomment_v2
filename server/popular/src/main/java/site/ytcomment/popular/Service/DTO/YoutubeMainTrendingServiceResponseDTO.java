package site.ytcomment.popular.Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class YoutubeMainTrendingServiceResponseDTO {
    private int id;
    private String thumbnails;
    private String channelTitle;
    private String title;
    private String description;
    private String videoId;
    private String textOriginal;
    private int likeCount;
}
