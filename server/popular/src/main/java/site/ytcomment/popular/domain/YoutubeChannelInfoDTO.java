package site.ytcomment.popular.domain;

import java.time.LocalDateTime;

public record YoutubeChannelInfoDTO (String id, String channelTitle, String title, String description, String thumbnails, String channelId, String tags, int categoryId, LocalDateTime publishedAt){

}
