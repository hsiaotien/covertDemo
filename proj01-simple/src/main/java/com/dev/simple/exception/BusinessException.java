package com.dev.simple.exception;

import com.dev.simple.enums.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

public class BusinessException extends RuntimeException {
    @Setter
    @Getter
    private Integer code;
    @Setter
    @Getter
    private ExceptionEnum exceptionEnum;

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.exceptionEnum = exceptionEnum;
    }
}
