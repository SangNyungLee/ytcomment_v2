package site.ytcomment.popular.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class YoutubeChannelInfoDTO {
    private String id;
    private String channelTitle;
    private String title;
    private String description;
    private String thumbnails;
    private String channelId;
    private String tags;
    private int categoryId;
    private LocalDateTime publishedAt;

}
