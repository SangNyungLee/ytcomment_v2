package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.DTO.YoutubeChannelInfoDTO;

@Mapper
public interface TrendingMapper {
    void insertVideo(YoutubeChannelInfoDTO youtubeChannelInfoDTO);
}
