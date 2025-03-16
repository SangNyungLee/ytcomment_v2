package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.ytcomment.popular.DTO.DetailPageInfoDTO;

@Mapper
public interface DetailPageInfoMapper {
    @Select("select a.*, b.channelLikeCount from channelInfo as a left join statistics as b on a.id = b.id where a.id =#{videoId}")
    DetailPageInfoDTO.Out selectDetailPageInfo(DetailPageInfoDTO.In in);
}
