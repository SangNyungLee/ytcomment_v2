package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.Service.DTO.CardRequestServiceDTO;
import site.ytcomment.popular.Service.DTO.CardResponseServiceDTO;

import java.util.List;

@Mapper
public interface TrendingMapper {
    List<CardResponseServiceDTO> getTrendingVideos(CardRequestServiceDTO cardRequestServiceDTO);
}
