package com.sprint.sb06deokhugamteam01.service.book;

import com.sprint.sb06deokhugamteam01.domain.Book;
import com.sprint.sb06deokhugamteam01.dto.book.request.BookCreateRequest;
import com.sprint.sb06deokhugamteam01.dto.book.BookDto;
import com.sprint.sb06deokhugamteam01.dto.book.request.BookUpdateRequest;
import com.sprint.sb06deokhugamteam01.dto.book.request.PagingBookRequest;
import com.sprint.sb06deokhugamteam01.dto.book.response.CursorPageResponseBookDto;
import com.sprint.sb06deokhugamteam01.exception.book.AllReadyExistsIsbnException;
import com.sprint.sb06deokhugamteam01.exception.book.NoSuchBookException;
import com.sprint.sb06deokhugamteam01.mapper.book.BookMapper;
import com.sprint.sb06deokhugamteam01.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements  BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto getBookById(UUID id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException("존재하지 않는 도서입니다.")));
    }

    @Override
    public BookDto getBookByIsbn(String isbn) {
        return bookMapper.toDto(bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new NoSuchBookException("존재하지 않는 도서입니다.")));
    }

    @Override
    public CursorPageResponseBookDto getBooksByPage(PagingBookRequest pagingBookRequest) {
        return null;
    }

    @Transactional
    @Override
    public BookDto createBook(BookCreateRequest bookCreateRequest, @Nullable MultipartFile file) {

        if (bookRepository.existsByIsbn(bookCreateRequest.isbn())) {
            throw new AllReadyExistsIsbnException("이미 존재하는 ISBN 입니다.");
        }

        Book book = bookMapper.toNewEntity(bookCreateRequest);

        //ToDo: S3 파일 업로드 처리

        return bookMapper.toDto(bookRepository.save(book));

    }

    @Transactional
    @Override
    public BookDto updateBook(UUID id, BookUpdateRequest bookUpdateRequest, @Nullable MultipartFile file) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException("존재하지 않는 도서입니다."));

        book.updateBook(
                bookUpdateRequest.title(),
                bookUpdateRequest.author(),
                bookUpdateRequest.description(),
                bookUpdateRequest.publisher(),
                bookUpdateRequest.publishedDate()
        );

        return bookMapper.toDto(book);

    }

    @Transactional
    @Override
    public void deleteBookById(UUID id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException("존재하지 않는 도서입니다."));

        book.softDelete();

    }

    @Transactional
    @Override
    public void hardDeleteBookById(UUID id) {

        if (!bookRepository.existsById(id)) {
            throw new NoSuchBookException("존재하지 않는 도서입니다.");
        }

        bookRepository.deleteById(id);

        //ToDo: 연관관계 매핑된 리뷰들 모두 삭제하기

    }
}
