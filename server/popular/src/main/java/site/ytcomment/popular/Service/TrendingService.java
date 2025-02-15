package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Controller.DTO.CardRequestControllerDTO;
import site.ytcomment.popular.Controller.DTO.CardResponseControllerDTO;
import site.ytcomment.popular.Service.DTO.CardRequestServiceDTO;
import site.ytcomment.popular.Service.DTO.CardResponseServiceDTO;
import site.ytcomment.popular.mapper.TrendingMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrendingService {

    private final TrendingMapper trendingMapper;

    public List<CardResponseControllerDTO> getTrendingService(CardRequestControllerDTO cardRequestControllerDTO) {
        CardRequestServiceDTO cardRequestServiceDTO = CardRequestServiceDTO.builder()
                .page(cardRequestControllerDTO.getPage())
                .newCategory(cardRequestControllerDTO.getNewCategory())
                .startIndex((cardRequestControllerDTO.getPage() - 1) * 12) // 12 = itemsPerPage변수인데 나중에 바꾸셈
                .build();


        //mapper 호출해서 데이터 조회
        List<CardResponseServiceDTO> serviceResults = trendingMapper.getTrendingVideos(cardRequestServiceDTO);

        return serviceResults.stream()
                // .map(result -> convertToControllerDTO(result)); 이거 줄인거임
                .map(this::convertToControllerDTO)
                .collect(Collectors.toList());

    }

    private CardResponseControllerDTO convertToControllerDTO(CardResponseServiceDTO cardResponseServiceDTO) {
        return CardResponseControllerDTO.builder()
                .idx(cardResponseServiceDTO.getIdx())
                .id(cardResponseServiceDTO.getId())
                .thumbnails(cardResponseServiceDTO.getThumbnails())
                .channelTitle(cardResponseServiceDTO.getChannelTitle())
                .title(cardResponseServiceDTO.getTitle())
                .description(cardResponseServiceDTO.getDescription())
                .textOriginal(cardResponseServiceDTO.getTextOriginal())
                .channelId(cardResponseServiceDTO.getChannelId())
                .likeCount(cardResponseServiceDTO.getLikeCount())
                .build();
    }
}
