package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import site.ytcomment.popular.mapper.DTO.UpdateUserNameDbDTO;
import site.ytcomment.popular.mapper.DTO.UpdateUserPwDbDTO;
import site.ytcomment.popular.mapper.DTO.UserInfoDbDTO;

@Mapper
public interface UserInfoMapper {

    @Select("SELECT a.id, a.userEmail, a.userName, a.social, (select count(*) from likes where userId = #{userId}) as count from userinfo as a WHERE a.id = #{userId};")
    UserInfoDbDTO.Out selectUserInfo(UserInfoDbDTO.In in);

    // 사용자 닉네임 변경하는 쿼리
    @Update("UPDATE userinfo SET userName = #{userName} WHERE id = #{id};")
    int updateUserInfo(UpdateUserNameDbDTO.In in);

    // 사용자 비밀번호 변경하는 쿼리
    @Update("UPDATE userinfo SET userPw = #{encryptConfirmPassword} WHERE id = #{userId}")
    int updateUserPassword(UpdateUserPwDbDTO.In in);
}
