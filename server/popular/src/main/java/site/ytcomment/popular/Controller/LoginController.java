package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.KakaoGetTokenControllerDTO;
import site.ytcomment.popular.Controller.DTO.KakaoLoginCheckUserControllerDTO;
import site.ytcomment.popular.Controller.DTO.KakaoLoginGetUserInfoControllerDTO;
import site.ytcomment.popular.Controller.DTO.LoginAuthControllerDTO;
import site.ytcomment.popular.Service.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

    private final KakaoLoginService kakaoLoginService;
    private final TokenService tokenService;
    private final KakaoLoginGetUserInfoService kakaoLoginGetUserInfoService;
    private final KakaoLoginCheckUserService kakaoLoginCheckUserService;
    private final LoginAuthService loginAuthService;

    @PostMapping("/kakao")
    public String kakaoLogin(@RequestBody KakaoGetTokenControllerDTO.In in) {
        // 카카오에서 쿼리스트링에 준 인가코드로 토큰, 리프레쉬 토큰 요청 받는 controller
        KakaoGetTokenControllerDTO.Out result = KakaoGetTokenControllerDTO.Out.from(tokenService.getToken(in.to()));

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
        return kakaoLoginCheckUserService.findByUser(userInfo.to());
    }

    // 카카오에 인가코드 요청받는 uri 반환하는 controller
    @GetMapping("/kakaoReq")
    public String initiateKakaoLogin() {
        return kakaoLoginService.getAuthorizeUrl();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginAuthControllerDTO.In in){
        ResponseEntity<String> result = loginAuthService.getUserPw(in.to());
        System.out.println("결과값" + result);
        // 로그인이 성공하면 서버 자체 토큰 발급해서 로그인 유지하는 로직 작성해야될듯
        return result;
    }

}
