package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import site.ytcomment.popular.mapper.DTO.UpdateUserNameDbDTO;
import site.ytcomment.popular.mapper.DTO.UserInfoDbDTO;

@Mapper
public interface UserInfoMapper {

    @Select("SELECT a.id, a.userEmail, a.userName, a.social, (select count(*) from likes where userId = #{userId}) as count from userinfo as a WHERE a.id = #{userId};")
    UserInfoDbDTO.Out selectUserInfo(UserInfoDbDTO.In in);

    @Update("UPDATE userinfo SET userName = #{userName} WHERE id = #{id};")
    int updateUserInfo(UpdateUserNameDbDTO.In in);
}
