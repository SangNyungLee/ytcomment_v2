package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.UserVideoLikeDbDTO;

@Mapper
public interface UserVideoLikeMapper {
    void insertUserVideoLike(UserVideoLikeDbDTO.In in);
}
