package com.sprint.sb06deokhugamteam01.exception;

import com.sprint.sb06deokhugamteam01.exception.book.*;
import com.sprint.sb06deokhugamteam01.exception.common.InvalidException;
import com.sprint.sb06deokhugamteam01.exception.review.ReviewAlreadyExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GlobalExceptionTest {

    @Test
    @DisplayName("OcrProcessingException 생성 테스트")
    void ocrProcessingException() {
        // given & when
        OcrProcessingException e = new OcrProcessingException(Map.of("message", "details"));

        // then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.OCR_PROCESSING_FAILED);
        assertThat(e.getMessage()).isEqualTo("OCR processing failed");
    }

    @Test
    @DisplayName("BookInfoFetchFailedException 생성 테스트")
    void bookInfoFetchFailedException() {
        // given & when
        BookInfoFetchFailedException e = new  BookInfoFetchFailedException(Map.of("isbn", "details"));

        // then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.BOOK_INFO_FETCH_FAILED);
        assertThat(e.getMessage()).isEqualTo("Book info fetch failed");
    }

    @Test
    @DisplayName("S3ObjectNotFoundException 생성 테스트")
    void s3ObjectNotFoundException() {
        // given & when
        S3ObjectNotFound e = new S3ObjectNotFound(Map.of("id", 1L));

        // then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.S3_OBJECT_NOT_FOUND);
        assertThat(e.getMessage()).isEqualTo("S3 object not found");
    }

    @Test
    @DisplayName("InvalidIsbnException 생성 테스트")
    void invalidIsbnException() {
        // given & when
        InvalidIsbnException e = new InvalidIsbnException(Map.of());

        // then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.INVALID_ISBN);
        assertThat(e.getMessage()).isEqualTo("Invalid ISBN");
    }

    @Test
    @DisplayName("S3DeleteFailedException 생성 테스트")
    void  s3DeleteFailedException() {
        // given & when
        S3DeleteFailedException e = new  S3DeleteFailedException(Map.of("id", 1L));

        // then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.S3_DELETE_FAILED);
        assertThat(e.getMessage()).isEqualTo("S3 delete failed");
    }

    @Test
    @DisplayName("InvalidException 생성 테스트")
    void invalidException() {
        // given & when
        InvalidException e = new InvalidException(Map.of());

        // then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.INVALID_REQUEST);
        assertThat(e.getMessage()).isEqualTo("Invalid request");
    }

    @Test
    @DisplayName("RootException 생성 테스트 - 파라미터 ErrorCode")
    void rootException() {
        // given & when
        RootException e = new RootException(ErrorCode.INTERNAL_SERVER_ERROR);
        e.addDetail("details", "details");

        // then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("ReviewAlreadyExistsException 생성 테스트")
    void  reviewAlreadyExistsException() {
        // given & when
        ReviewAlreadyExistsException e = new ReviewAlreadyExistsException(Map.of("cursor", "cursor"));

        // then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.REVIEW_ALREADY_EXISTS);
        assertThat(e.getMessage()).isEqualTo("Review already exists");
    }
}
