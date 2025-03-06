package site.ytcomment.popular.common.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    성공("0"),
    실패("1"),
    인증없음("2");

    private final String code;
}
