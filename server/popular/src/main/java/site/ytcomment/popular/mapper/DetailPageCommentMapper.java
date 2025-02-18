package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.DetailPageCommentDbDTO;

import java.util.List;

@Mapper
public interface DetailPageCommentMapper {
    List<DetailPageCommentDbDTO.Out> selectDetailPageComment(DetailPageCommentDbDTO.In detailPageDbDTOIn);
}
