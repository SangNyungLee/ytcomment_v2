package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.CardDbDTO;

import java.util.List;

@Mapper
public interface CardTrendingMapper {
    List<CardDbDTO.Out> getTrendingVideos(CardDbDTO.In cardDbDTOIn);
}
