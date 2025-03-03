package site.ytcomment.popular.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SocialEnum {
    카카오("0"),
    이메일("1");

    private final String code;

    public boolean is카카오() {
        return this == 카카오;
    }

//    public SocialEnum getEnum(final String code){
//        return Arrays.stream(SocialEnum.values()).map().findFirst()
//
//    }
}
