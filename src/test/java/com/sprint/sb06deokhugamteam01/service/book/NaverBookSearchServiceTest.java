package com.sprint.sb06deokhugamteam01.service.book;

import com.sprint.sb06deokhugamteam01.dto.book.BookDto;
import com.sprint.sb06deokhugamteam01.exception.book.InvalidIsbnException;
import com.sprint.sb06deokhugamteam01.exception.book.NoSuchBookException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@DisplayName("NaverBookSearchService 테스트")
class NaverBookSearchServiceTest {

    @InjectMocks
    private NaverBookSearchService naverBookSearchService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("ISBN으로 도서 검색 테스트")
    void testSearchBookByIsbn() {

        //given
        String testIsbn = "9791155976043"; // 예시 ISBN

        //when
        BookDto book = naverBookSearchService.searchBookByIsbn(testIsbn);

        //then
        assertNotNull(book);
        assertEquals(testIsbn, book.isbn());

    }

    @Test
    @DisplayName("도서 검색 결과 없음")
    void testSearchBookByIsbn_Fail_NoSuchBookException() {

        //given
        String invalidIsbn = "0000000000000"; // 존재하지 않는 ISBN

        //when

        //then
        assertThrows(NoSuchBookException.class, () -> {
            naverBookSearchService.searchBookByIsbn(invalidIsbn);
        });

    }

}