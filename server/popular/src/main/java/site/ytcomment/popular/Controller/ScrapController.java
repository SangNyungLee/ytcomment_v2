package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.UserVideoLikeControllerDTO;
import site.ytcomment.popular.Service.UserVideoLikeService;
import site.ytcomment.popular.config.jwt.CustomUserDetails;
import site.ytcomment.popular.config.jwt.JwtTokenProvider;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScrapController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserVideoLikeService userVideoLikeService;

    @PostMapping("/addUserLike")
    public void userVideoLike(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @RequestBody UserVideoLikeControllerDTO.In in) {
        userVideoLikeService.userScrapVideo(in.to(userDetails.getUserId()));
    }
}
