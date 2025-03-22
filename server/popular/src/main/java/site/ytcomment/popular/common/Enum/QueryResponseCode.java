package site.ytcomment.popular.common.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QueryResponseCode {
    성공("1"),
    실패("0");

    private final String code;
}
