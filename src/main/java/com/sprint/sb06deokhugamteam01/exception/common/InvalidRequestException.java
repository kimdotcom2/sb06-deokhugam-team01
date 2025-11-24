package com.sprint.sb06deokhugamteam01.exception.common;

import com.sprint.sb06deokhugamteam01.exception.ErrorCode;
import com.sprint.sb06deokhugamteam01.exception.RootException;
import java.util.Map;

public class InvalidRequestException extends RootException {

    ErrorCode errorCode = ErrorCode.INVALID_REQUEST;

    public InvalidRequestException(Map<String, Object> details) {
        super(ErrorCode.INVALID_REQUEST, details);
    }
}
