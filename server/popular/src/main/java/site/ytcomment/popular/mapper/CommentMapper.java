package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.DTO.YoutubeVideoCommentDTO;

@Mapper
public interface CommentMapper {
    void insertComment(YoutubeVideoCommentDTO youtubeVideoCommentDTO);
}
