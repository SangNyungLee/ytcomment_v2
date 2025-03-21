package site.ytcomment.popular.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 로그 에러 뜨는거 때문에 만든 controller
public class RootController {
    @GetMapping("/")
    public String home() {
        return "API 서버입니다.";
    }

    @GetMapping("/favicon.ico")
    public void favicon() {
        // 아무 것도 안 함 (빈 응답)
    }
}
