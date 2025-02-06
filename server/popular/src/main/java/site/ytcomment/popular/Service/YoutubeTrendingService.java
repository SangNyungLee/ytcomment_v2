package site.ytcomment.popular.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import site.ytcomment.popular.domain.YoutubeChannelInfoDTO;
import site.ytcomment.popular.mapper.TrendingMapper;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class YoutubeTrendingService implements TrendingMapper {

    private final WebClient.Builder webClientBuilder;
    private final TrendingMapper trendingMapper;

    @Value("${youtube.api.key}")
    private String apiKey;
    @Value("${youtube.api.url}")
    private String apiUrl;

    @Override
    public void insertVideo(YoutubeChannelInfoDTO youtubeChannelInfoDTO) {
    }

    public JsonNode searchVideos() {
        String result = webClientBuilder
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", apiKey)
                        .queryParam("part", "snippet")
                        .queryParam("chart", "mostPopular")
                        .queryParam("maxResults", 2)
                        .queryParam("videoCategoryId", 0)
                        .queryParam("regionCode", "KR")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JsonNode data = new ObjectMapper().readTree(result);
            /// JsonNode -> stream으로 변경하기 위해서 StreamSupport.stream함수 사용, spliterator는 stream짤라주는 함수임
            List<YoutubeChannelInfoDTO> videoList = StreamSupport.stream(data.get("items").spliterator(), false)
                    .map(item -> YoutubeChannelInfoDTO.builder()
                            .title(item.get("snippet").get("title").asText())
                            .channelId(item.get("snippet").get("channelId").asText())
                            .channelTitle(item.get("snippet").get("channelTitle").asText())
                            .build())
                    .toList();
            /// forEach(video -> youtubeUserMapper.insertVideo(video)) 람다식표현
            videoList.forEach(trendingMapper::insertVideo);
            return data;
        } catch (JsonProcessingException e){
            return null;
        }
    }

}
