package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.CardServiceDTO;
import site.ytcomment.popular.mapper.CardTrendingMapper;
import site.ytcomment.popular.mapper.DTO.CardDbDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardTrendingService {

    private final CardTrendingMapper cardTrendingMapper;

    // Service에서는 CardServiceDTO In, Out만 받으면 됨
    public List<CardServiceDTO.Out> getTrendingService(CardServiceDTO.In cardServiceDTOIn) {
        List<CardDbDTO.Out> serviceResults = cardTrendingMapper.getTrendingVideos(cardServiceDTOIn.to());

        return serviceResults.stream()
                .map(CardServiceDTO.Out::from)
                .collect(Collectors.toList());
    }
}
