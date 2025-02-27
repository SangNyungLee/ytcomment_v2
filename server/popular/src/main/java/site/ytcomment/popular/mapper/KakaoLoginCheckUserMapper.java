package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.Service.DTO.KakaoLoginCheckUserServiceDTO;
import site.ytcomment.popular.mapper.DTO.KakaoLoginCheckUserDbDTO;

import java.util.Optional;

@Mapper
public interface KakaoLoginCheckUserMapper {
    Optional<KakaoLoginCheckUserDbDTO.Out> findByKakaoUser(KakaoLoginCheckUserServiceDTO.In in);
    void insertKakaoUser(KakaoLoginCheckUserServiceDTO.In in);
}
