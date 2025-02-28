package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.EmailSignUpDbDTO;

@Mapper
public interface EmailSignUpMapper {
    // 이거 void로 하는게 맞나?
    void insertUserInfo(EmailSignUpDbDTO.In in);
}
