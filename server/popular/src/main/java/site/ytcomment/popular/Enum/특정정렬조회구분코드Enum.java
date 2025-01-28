package site.ytcomment.popular.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum 특정정렬조회구분코드Enum {
    // 01 : 좋아요, 02 : 최신 ...
    좋아요("01"),
    최신("02"),
    일자("03");

    private final String code;

    public static 특정정렬조회구분코드Enum getEnum(final String code) {
        return Arrays.stream(values())
                .filter(value -> value.code.equals(code))
                .findFirst()
                .orElse(null);
    }

    public static boolean is일자별(final String code){
        return code == 일자.code;
    }
}
