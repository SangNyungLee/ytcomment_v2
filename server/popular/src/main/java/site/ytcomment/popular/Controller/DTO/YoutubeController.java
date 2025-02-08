package site.ytcomment.popular.Controller.DTO;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ytcomment.popular.Service.YoutubeTrendingService;

@RestController
@RequiredArgsConstructor
public class YoutubeController {

    private final YoutubeTrendingService youtubeTrendingService;

    @GetMapping("/getVideos")
    public String getVideos() {
        return youtubeTrendingService.searchVideos();
    }
}
