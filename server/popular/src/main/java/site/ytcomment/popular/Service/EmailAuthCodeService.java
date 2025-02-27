package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailVerifyAuthServiceDTO;
import site.ytcomment.popular.common.BaseResponse;
import site.ytcomment.popular.config.RedisConfig;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailAuthCodeService {

    private final RedisConfig redisConfig;
    private final Random random = new Random();

    // 랜덤 인증번호 생성
    public String generateAuthCode() {
        StringBuilder sb = new StringBuilder();
        // 랜덤한 6자리의 값으로 인증코드 생성
        for(int i = 0 ; i < 6 ; i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // Redis에 인증 코드 저장
    public void saveAuthCode(String email, String authCode) {
        ValueOperations<String, String> valueOperations = redisConfig.redisTemplate().opsForValue();
        valueOperations.set(email, authCode, 180, TimeUnit.SECONDS);
    }

    // 인증 코드 검증
    public BaseResponse verifyAuthCode(EmailVerifyAuthServiceDTO.In in) {
        ValueOperations<String, String> valueOperations = redisConfig.redisTemplate().opsForValue();
        String storedCode = valueOperations.get(in.getEmail());
        if (Objects.equals(storedCode, in.getAuthNum())) {
            return BaseResponse.success("인증 성공");
        }else {
            return BaseResponse.fail(400,"인증 실패");
        }
    }
}
