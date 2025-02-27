package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.KakaoLoginCheckUserServiceDTO;
import site.ytcomment.popular.mapper.DTO.KakaoLoginCheckUserDbDTO;
import site.ytcomment.popular.mapper.KakaoLoginCheckUserMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoLoginCheckUserService {

    private final KakaoLoginCheckUserMapper kakaoLoginCheckUserMapper;

    public String findByUser(KakaoLoginCheckUserServiceDTO.In in){
        Optional<KakaoLoginCheckUserDbDTO.Out> queryResult = kakaoLoginCheckUserMapper.findByKakaoUser(in);
        if (queryResult.isPresent()){
            System.out.println("이미 저장된 회원 정보");
        }
        // 회원 정보 없으면 Db에 저장하는 로직, 이거 원래 나눠야 되는데 길어질거 같아서 그냥 여기에 작성함
        else{
            System.out.println("Db에 없는 회원 정보, 가입 후 반환");
            kakaoLoginCheckUserMapper.insertKakaoUser(in);
        }
        return "success";
    }
}
