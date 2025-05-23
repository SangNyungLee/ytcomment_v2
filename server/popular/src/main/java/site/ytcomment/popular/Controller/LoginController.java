package site.ytcomment.popular.Controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.*;
import site.ytcomment.popular.DTO.TokenResponseDTO;
import site.ytcomment.popular.Service.*;
import site.ytcomment.popular.Service.DTO.LoginAuthServiceDTO;
import site.ytcomment.popular.Service.DTO.UserInfoServiceDTO;
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
        String token = jwtTokenProvider.createToken(userInfo.getKakaoNickname(), userInfo.getKakaoId());
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
    public ResponseEntity<?> login(HttpServletResponse response, @RequestBody LoginAuthControllerDTO.In in){
        LoginAuthServiceDTO.Out result = loginAuthService.getUserPw(in.to());
        // 0 : 인증 안된 계정
        // 1 : 인증 된 계정
        if (result == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("잘못된 인증정보 입니다.");
        }
        if ("2".equals(result.getUserAuth())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(result.getUserEmail());
        }
            // 로그인 성공시 JWT 토큰 생성
            // in.. 이런 애들 전부다 DTO로 감싸야됨
            UserInfoServiceDTO.Out userInfo = loginAuthService.getUserEmailById(UserInfoControllerDTO.In.to(in.getUserId()));
            String token = jwtTokenProvider.createToken(userInfo.getUserEmail(), in.getUserId());
            String refreshToken = jwtTokenProvider.createRefreshToken(userInfo.getUserEmail(), in.getUserId());
            ResponseCookie getCookie = jwtTokenProvider.giveMeCookie(refreshToken);
            // refreshToken 응답헤더(쿠키)에 저장해두는 거임
            response.setHeader(HttpHeaders.SET_COOKIE, getCookie.toString());
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION) // CORS 문제 방지
                    .body(userInfo.getUserName());
    }
}
