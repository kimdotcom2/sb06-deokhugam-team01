package com.sprint.sb06deokhugamteam01.exception.book;

import com.sprint.sb06deokhugamteam01.exception.ErrorCode;
import com.sprint.sb06deokhugamteam01.exception.RootException;
import java.util.Map;

public class BookNotFoundException extends RootException {

    ErrorCode errorCode = ErrorCode.BOOK_NOT_FOUND;
    public BookNotFoundException(Map<String, Object> details) {
        super(ErrorCode.BOOK_NOT_FOUND, details);
    }

}
