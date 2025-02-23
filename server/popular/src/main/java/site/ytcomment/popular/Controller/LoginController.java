package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.KakaoLoginTestDTO;
import site.ytcomment.popular.Service.DTO.KakaoTokenResponseServiceDTO;
import site.ytcomment.popular.Service.KakaoGetTokenService;
import site.ytcomment.popular.Service.KakaoLoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final KakaoLoginService kakaoLoginService;
    private final KakaoGetTokenService kakaoGetTokenService;
    @PostMapping("/kakao")
    public String kakaoLogin(@RequestBody KakaoLoginTestDTO kakaoLoginTestDTO) {
        System.out.println("받아온 토큰 값 : " + kakaoLoginTestDTO.getCode());
        KakaoTokenResponseServiceDTO result = kakaoGetTokenService.GetToken(kakaoLoginTestDTO.getCode());
        System.out.println("token : " + result.getAccess_token());
        System.out.println("refresh_token : " + result.getRefresh_token());
        System.out.println("id_token : " + result.getId_token());
        return "success";
    }


    @GetMapping("/kakaoReq")
    public String initiateKakaoLogin() {
        String result = kakaoLoginService.getAuthorizeUrl();
        System.out.println("result = " + result);
        return result;
    }

}
