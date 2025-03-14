package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.ytcomment.popular.Service.DTO.scrap.UserVideoLikeServiceDTO;
import site.ytcomment.popular.mapper.DTO.scrap.FindVideoLikeDbDTO;
import site.ytcomment.popular.mapper.DTO.scrap.UserVideoLikeDbDTO;

import java.util.Optional;

@Mapper
public interface UserVideoLikeMapper {
    @Insert("INSERT INTO likes (userId, videoId) VALUES(#{userId}, #{videoId}) ON DUPLICATE KEY UPDATE likedAt = CURRENT_TIMESTAMP")
    void insertUserVideoLike(UserVideoLikeDbDTO.In in);

    @Select("SELECT COUNT(*) FROM likes WHERE userId = #{userId} AND videoId = #{videoId}")
    FindVideoLikeDbDTO.Out countUserVideoLike(FindVideoLikeDbDTO.In in);

    @Delete("DELETE FROM likes WHERE userId = #{userId} AND videoId = #{videoId}")
    void deleteUserVideoLike(UserVideoLikeDbDTO.In in);
}
