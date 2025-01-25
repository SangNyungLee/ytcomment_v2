package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrendingMapper {
    List<Map<String, Object>> getTrendingVideos(
            @Param("newCategory") int newCategory,
            @Param("startIndex") int startIndex,
            @Param("itemsPage") int itemsPage
            );
}
