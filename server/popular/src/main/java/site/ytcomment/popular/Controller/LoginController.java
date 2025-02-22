package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.KakaoLoginControllerDTO;
import site.ytcomment.popular.Service.KakaoLoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final KakaoLoginService kakaoLoginService;
//    @PostMapping("/kakao")
    public String kakaoLogin(@RequestBody KakaoLoginControllerDTO loginControllerDTO) {
        System.out.println("받아온 토큰 값 : " + loginControllerDTO.token());
        return "success";
    }

    @GetMapping("/kakaoReq")
    public String RestKakaoLogin(){
        String result = kakaoLoginService.getTokenRequest();
        System.out.println("KakaoReq : " + result);
        return result;
    }
    @GetMapping("/kakao")
    public void KakaoLogin(@RequestParam(name = "code") String code){
        System.out.println("여기에 들어온 값은? " + code);
    }

}
