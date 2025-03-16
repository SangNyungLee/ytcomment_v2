package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.ytcomment.popular.DTO.DetailPageTagsDTO;

@Mapper
public interface DetailPageTagsMapper {
    @Select("SELECT tags from channelInfo where id=${videoId}")
    DetailPageTagsDTO selectDetailPageTags(DetailPageTagsDTO detailPageTags);
}
