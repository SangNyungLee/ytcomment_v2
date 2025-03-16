package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.KakaoGetTokenControllerDTO;
import site.ytcomment.popular.Controller.DTO.KakaoLoginCheckUserControllerDTO;
import site.ytcomment.popular.Controller.DTO.KakaoLoginGetUserInfoControllerDTO;
import site.ytcomment.popular.Controller.DTO.LoginAuthControllerDTO;
import site.ytcomment.popular.DTO.TokenResponseDTO;
import site.ytcomment.popular.Service.*;
import site.ytcomment.popular.common.Enum.ResponseCode;
import site.ytcomment.popular.config.jwt.JwtTokenProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

    private final KakaoLoginService kakaoLoginService;
    private final TokenService tokenService;
    private final KakaoLoginGetUserInfoService kakaoLoginGetUserInfoService;
    private final KakaoLoginCheckUserService kakaoLoginCheckUserService;
    private final LoginAuthService loginAuthService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody KakaoGetTokenControllerDTO.In in) {
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
        // DB에 저장되어 있는지 확인하는 로직인데 이거 어떻게 사용할지 고민해보기
        kakaoLoginCheckUserService.findByUser(userInfo.to());

        // email, name을 넣어서 token을 생성하는데 이거 여기서 할게 아니라 비즈니스계층에서 해야될듯 코드 너무 난잡함
        String token = jwtTokenProvider.createToken(userInfo.getKakaoId(), userInfo.getKakaoNickname());
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO(token, userInfo.getKakaoNickname());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(tokenResponseDTO);
    }

    // 카카오에 인가코드 요청받는 uri 반환하는 controller
    @GetMapping("/kakaoReq")
    public String initiateKakaoLogin() {
        return kakaoLoginService.getAuthorizeUrl();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginAuthControllerDTO.In in){
        String result = loginAuthService.getUserPw(in.to());
        System.out.println("결과값은 : " + result);
        if (result.equals(ResponseCode.인증없음.getCode()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("인증이 되지 않았습니다.");
        else if (result.equals(ResponseCode.실패.getCode()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("잘못된 인증정보 입니다.");
        else{
            // 로그인 성공시 JWT 토큰 생성
            String email = loginAuthService.getUserEmailById(in.getUserId());
            String token = jwtTokenProvider.createToken(email, in.getUserId());
            TokenResponseDTO tokenResponseDTO = new TokenResponseDTO(token, in.getUserId());

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body(tokenResponseDTO);

        }
    }
}
