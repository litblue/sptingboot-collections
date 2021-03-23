package cn.litblue.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author: litblue
 * @time: 2020/12/19  23:19
 * @version: 1.0.0
 * @description:
 */

@Getter
public enum  StatusEnum {
    STUDY(0, "Study"),
    PLAY(1, "Play"),
    ;

    StatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @EnumValue
    private final Integer code;
    private final String msg;
}
