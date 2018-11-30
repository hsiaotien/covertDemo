package com.dev.simple.enums;

import lombok.Getter;
import lombok.Setter;

public enum ExceptionEnum {
    DATABASE_ERROR(500001,"数据库错误"),
    ADD_USER_EXCP(500002,"增加用户失败")
    ;
    @Getter
    @Setter
    private Integer code;
    @Getter
    @Setter
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
