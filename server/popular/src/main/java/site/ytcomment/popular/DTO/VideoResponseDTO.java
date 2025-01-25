package site.ytcomment.popular.DTO;

import lombok.Builder;

import java.util.Date;

@Builder
public class VideoResponseDTO {
    private String id;
    private String channelTitle;
    private String title;
    private String description;
    private String thumbnails;
    private String channelId;
    private String tags;
    private String categoryId;
    private Date publishedAt;
}
