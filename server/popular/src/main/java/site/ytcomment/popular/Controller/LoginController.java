package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.KakaoLoginCheckUserControllerDTO;
import site.ytcomment.popular.Controller.DTO.KakaoLoginGetUserInfoControllerDTO;
import site.ytcomment.popular.Controller.DTO.KakaoGetTokenControllerDTO;
import site.ytcomment.popular.Service.KakaoLoginCheckUserService;
import site.ytcomment.popular.Service.KakaoLoginGetUserInfoService;
import site.ytcomment.popular.Service.KakaoGetTokenService;
import site.ytcomment.popular.Service.KakaoLoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

    private final KakaoLoginService kakaoLoginService;
    private final KakaoGetTokenService kakaoGetTokenService;
    private final KakaoLoginGetUserInfoService kakaoLoginGetUserInfoService;
    private final KakaoLoginCheckUserService kakaoLoginCheckUserService;
    @PostMapping("/kakao")
    public String kakaoLogin(@RequestBody KakaoGetTokenControllerDTO.In in) {
        // 카카오에서 쿼리스트링에 준 인가코드로 토큰, 리프레쉬 토큰 요청 받는 controller
        KakaoGetTokenControllerDTO.Out result = KakaoGetTokenControllerDTO.Out.from(kakaoGetTokenService.getToken(in.to()));

        // 받아온 access_token으로 사용자의 정보를 받아옴
        KakaoLoginGetUserInfoControllerDTO.In loginToken = KakaoLoginGetUserInfoControllerDTO.In.builder()
                        .accessToken(result.getAccess_token()).build();
        KakaoLoginGetUserInfoControllerDTO.Out userInfoResult = KakaoLoginGetUserInfoControllerDTO.Out
                .from(kakaoLoginGetUserInfoService.GetLoginUserInfo(loginToken.to()));

        /*
        1. User정보가 Db에 저장되어 있는지 확인하는 로직 작성하기
         - 회원조회, 조회 후 가입 -> 로그인 처리 나눠야되는데 그럼 service가 너무 많아져서 하나에 다 넣을예정
         */
        KakaoLoginCheckUserControllerDTO.In userInfo = KakaoLoginCheckUserControllerDTO.In.builder()
                .kakaoId(userInfoResult.getKakaoId())
                .kakaoNickname(userInfoResult.getKakaoNickname())
                .build();
        String checkUserResult = kakaoLoginCheckUserService.findByUser(userInfo.to());
        return "success";
    }


    @GetMapping("/kakaoReq")
    public String initiateKakaoLogin() {
        // 카카오에 인가코드 요청받는 uri 반환하는 controller
        return kakaoLoginService.getAuthorizeUrl();
//        System.out.println("result = " + result);
    }

}
