package com.sprint.sb06deokhugamteam01.service.book;

import com.sprint.sb06deokhugamteam01.dto.book.BookCreateRequest;
import com.sprint.sb06deokhugamteam01.dto.book.BookDto;
import org.jspecify.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class BookServiceImpl implements  BookService {

    @Override
    public BookDto createBook(BookCreateRequest bookCreateRequest, @Nullable MultipartFile file) {
        return null;
    }
}
