package site.ytcomment.popular.DTO;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class VideoResponseDTO extends PagingDTO {
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
