package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.DTO.YoutubeChannelInfoDTO;
import site.ytcomment.popular.DTO.YoutubeVideoCommentDTO;
import site.ytcomment.popular.DTO.YoutubeVideoStatisticsDTO;

import java.util.List;

@Getter
@Builder
public class ControllerResponseDTO {
    private YoutubeChannelInfoDTO channelInfo;
    private YoutubeVideoCommentDTO comment;
    private YoutubeVideoStatisticsDTO statistics;
}
