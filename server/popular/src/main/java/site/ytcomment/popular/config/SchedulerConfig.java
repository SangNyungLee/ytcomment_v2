package site.ytcomment.popular.config;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import site.ytcomment.popular.Controller.YoutubeController;

//@Component
@RequiredArgsConstructor
public class SchedulerConfig {

//    private final YoutubeController youtubeController;
//
//    // 매일 자정에 실행
//    @Scheduled(cron = "0 0 0 * * *")
//    public void getTrendingVideos(){
//        youtubeController.getVideos();
//    }
}
