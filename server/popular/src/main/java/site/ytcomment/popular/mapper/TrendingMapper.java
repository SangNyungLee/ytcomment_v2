package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.DTO.YoutubeChannelInfoDTO;
import site.ytcomment.popular.DTO.YoutubeVideoStatisticsDTO;

@Mapper
public interface TrendingMapper {
    void insertVideo(YoutubeChannelInfoDTO youtubeChannelInfoDTO);
    void insertStatistics(YoutubeVideoStatisticsDTO youtubeVideoStatisticsDTO);
}
