package site.ytcomment.popular.common.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum YoutubeCategory {
    FILM_AND_ANIMATION(1, "영화 & 애니메이션"),
    AUTOS_AND_VEHICLES(2, "자동차"),
    MUSIC(10, "음악"),
    PETS_AND_ANIMALS(15, "애완동물 & 동물"),
    SPORTS(17, "스포츠"),
    SHORT_FILMS(18, "단편 영화"),
    TRAVEL_AND_EVENTS(19, "여행 & 이벤트"),
    GAMING(20, "게임"),
    VLOGS(21, "블로그"),
    PEOPLE_AND_BLOGS(22, "사람 & 블로그"),
    COMEDY(23, "코미디"),
    ENTERTAINMENT(24, "엔터테인먼트"),
    NEWS_AND_POLITICS(25, "뉴스 & 정치"),
    HOWTO_AND_STYLE(26, "스타일"),
    EDUCATION(27, "교육"),
    SCIENCE_AND_TECHNOLOGY(28, "과학 & 기술"),
    NONPROFITS_AND_ACTIVISM(29, "비영리 & 사회활동");

    private final int id;
    private final String name;

    public static YoutubeCategory fromId(int id) {
        for (YoutubeCategory category : YoutubeCategory.values()) {
            if (category.getId() == id) {
                return category;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 카테고리 ID" + id);
    }
}
