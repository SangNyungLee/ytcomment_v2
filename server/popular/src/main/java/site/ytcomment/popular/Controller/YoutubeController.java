package site.ytcomment.popular.Controller.DTO;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ytcomment.popular.Service.YoutubeTrendingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController {

    private final YoutubeTrendingService youtubeTrendingService;

    @GetMapping("/getVideos")
    public String getVideos() {
        return youtubeTrendingService.searchVideos();
    }

    @PostMapping("/trending")
    public ResponseEntity<>
}
