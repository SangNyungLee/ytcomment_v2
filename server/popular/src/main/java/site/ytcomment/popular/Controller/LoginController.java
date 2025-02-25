package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.KakaoGetTokenControllerDTO;
import site.ytcomment.popular.Service.DTO.KakaoGetTokenServiceDTO;
import site.ytcomment.popular.Service.KakaoGetTokenService;
import site.ytcomment.popular.Service.KakaoLoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final KakaoLoginService kakaoLoginService;
    private final KakaoGetTokenService kakaoGetTokenService;
    @PostMapping("/kakao")
    public String kakaoLogin(@RequestBody KakaoGetTokenControllerDTO.In in) {
        KakaoGetTokenServiceDTO.Out result = kakaoGetTokenService.getToken(in.to());
        System.out.println("token : " + result.getAccess_token());
        System.out.println("refresh_token : " + result.getRefresh_token());
        System.out.println("id_token : " + result.getId_token());
        // GetTokenControllerDTO 써야 되는데 어디다 쓰지 ?
        return "success";
    }


    @GetMapping("/kakaoReq")
    public String initiateKakaoLogin() {
        String result = kakaoLoginService.getAuthorizeUrl();
        System.out.println("result = " + result);
        return result;
    }

}
