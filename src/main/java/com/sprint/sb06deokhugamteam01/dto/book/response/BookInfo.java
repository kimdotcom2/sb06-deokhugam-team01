package com.sprint.sb06deokhugamteam01.dto.book.response;

import com.sprint.sb06deokhugamteam01.dto.book.BookDto;

import java.time.LocalDate;

public record BookInfo(
        String title,
        String author,
        String description,
        String publisher,
        LocalDate publishedDate,
        String isbn,
        String thumbnailImage
) {
    public static BookInfo fromDto(BookDto bookDto) {
        return new BookInfo(
                bookDto.title(),
                bookDto.author(),
                bookDto.description(),
                bookDto.publisher(),
                bookDto.publishedDate(),
                bookDto.isbn(),
                bookDto.thumbnailUrl()
        );
    }
}
