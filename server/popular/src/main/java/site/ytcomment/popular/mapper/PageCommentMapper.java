package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.Service.DTO.DetailPageRequestServiceDTO;
import site.ytcomment.popular.Service.DTO.DetailPageResponseServiceDTO;

import java.util.List;

@Mapper
public interface PageCommentMapper {
    List<DetailPageResponseServiceDTO> selectComment(DetailPageRequestServiceDTO detailPageRequestServiceDTO);
}
