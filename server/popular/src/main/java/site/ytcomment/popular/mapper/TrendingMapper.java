package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.domain.YoutubeChannelInfoDTO;

@Mapper
public interface TrendingMapper {
    void insertVideo(YoutubeChannelInfoDTO youtubeChannelInfoDTO);
}
