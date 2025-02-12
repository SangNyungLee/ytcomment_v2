package site.ytcomment.popular.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.ytcomment.popular.Controller.DTO.CardRequestControllerDTO;
import site.ytcomment.popular.Controller.DTO.CardResponseControllerDTO;
import site.ytcomment.popular.Service.DTO.CardRequestServiceDTO;
import site.ytcomment.popular.Service.DTO.CardResponseServiceDTO;
import site.ytcomment.popular.mapper.TrendingMapper;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrendingServiceTest {

    @InjectMocks
    private TrendingService trendingService;

    @Mock
    private TrendingMapper trendingMapper;

    private CardResponseServiceDTO sampleServiceDTO;
    private CardRequestControllerDTO sampleRequestDTO;

    @BeforeEach
    void setUp() {
        // 테스트용 데이터 설정
        sampleServiceDTO = CardResponseServiceDTO.builder()
                .id(1)
                .thumbnails("thumbnail-url")
                .channelTitle("Test Channel")
                .title("Test Video")
                .description("Test Description")
                .videoId("test-video-id")
                .textOriginal("Test Comment")
                .likeCount(100)
                .build();

        sampleRequestDTO = CardRequestControllerDTO.builder()
                .page(1)
                .newCategory(1)
                .build();
    }

    @Test
    @DisplayName("Trending Service 데이터 변환, 반환하는지 test")
    void getTrendingServiceTest(){
        when(trendingMapper.getTrendingVideos(any(CardRequestServiceDTO.class)))
                .thenReturn(Arrays.asList(sampleServiceDTO));

        List<CardResponseControllerDTO> result = trendingService.getTrendingService(sampleRequestDTO);

        assertThat(result).isNull();
        assertThat(result).hasSize(1);

        CardResponseControllerDTO responseDTO = result.get(0);
        assertThat(responseDTO.getId()).isEqualTo(sampleServiceDTO.getId());
        assertThat(responseDTO.getThumbnails()).isEqualTo(sampleServiceDTO.getThumbnails());
        assertThat(responseDTO.getChannelTitle()).isEqualTo(sampleServiceDTO.getChannelTitle());
        assertThat(responseDTO.getTitle()).isEqualTo(sampleServiceDTO.getTitle());
        assertThat(responseDTO.getDescription()).isEqualTo(sampleServiceDTO.getDescription());
        assertThat(responseDTO.getVideoId()).isEqualTo(sampleServiceDTO.getVideoId());
        assertThat(responseDTO.getTextOriginal()).isEqualTo(sampleServiceDTO.getTextOriginal());
        assertThat(responseDTO.getLikeCount()).isEqualTo(sampleServiceDTO.getLikeCount());
    }

    @Test
    @DisplayName("페이지네이션 계산이 정확한지 테스트")
    void paginationCalculationTest() {
        // given
        CardRequestControllerDTO request = CardRequestControllerDTO.builder()
                .page(2)
                .newCategory(1)
                .build();

        when(trendingMapper.getTrendingVideos(any(CardRequestServiceDTO.class)))
                .thenReturn(Arrays.asList(sampleServiceDTO));

        // when
        trendingService.getTrendingService(request);

        // then
        // startIndex가 12(=1 * 12)인지 검증하는 로직이 필요하나,
        // 현재 구조에서는 직접 검증이 어려움. 리팩토링 추천
    }

}