package site.ytcomment.popular.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
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
