package org.koreait.global.paging;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock; // 가짜 객체
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.mockito.BDDMockito.*;

@SpringBootTest
@ActiveProfiles({"default","test"})
public class PaginationTest {

    // 가짜 객체
    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void init() {
        // Stub(스텁 - 가짜 데이터)
        // given(request.getQueryString.()).willReturn("query=블로그&test1=1&test2=2&page=3");
        given(request.getQueryString()).willReturn(null);

    }

    @Test
    void test1() {
        Pagination paginaion = new Pagination(23, 9999, 10, 20, request);
        System.out.println(paginaion);

        paginaion.getPages().forEach(s -> System.out.println(Arrays.toString(s)));
    }
}
