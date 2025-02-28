package site.ytcomment.popular.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // @Param rawPassword : 사용자 입력 비밀번호
    // @return : 해싱된 비밀번호
    public static String encodePassword(String rawPassword){
        return passwordEncoder.encode(rawPassword);
    }

    /*
    비밀번호와 해싱된 비밀번호 비교(로그인할 때 사용)
    @Param rawPassword : 사용자 입력 비밀번호
    @Param encodedPassword : DB에 해싱 저장된 비밀번호
    @return : 비밀번호가 일치하면 true, 아니면 false
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
