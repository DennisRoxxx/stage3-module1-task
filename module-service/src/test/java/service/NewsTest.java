package service;

import com.mjc.school.service.Verification;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.AuthorIdException;
import com.mjc.school.service.exception.LengthContentException;
import com.mjc.school.service.exception.LengthTitleException;
import com.mjc.school.service.exception.NewsNotFoundException;
import com.mjc.school.service.impl.NewsServiceImpl;
import com.mjc.school.service.interfaces.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsTest {

    private final Verification verification = new Verification();
    private NewsService<NewsDtoRequest, NewsDtoResponse> newsService;

    @BeforeEach
    void setUp() {
        newsService = new  NewsServiceImpl();
    }
    @Test
    void tryToMakeNewNewsDtoRequest() {
        NewsDtoRequest newsDtoRequest = new NewsDtoRequest(20L, "TestTitle", "TestContent", 5L);
        assertAll(() -> verification.verificationDtoRequest(newsDtoRequest));
    }

    @Test
    void putNullInNewsId() {
        assertThrows(NewsNotFoundException.class, () ->
                verification.verificationNewsId(null));
    }

    @Test
    void tryToFindNonexistentAuthorId() {
        long id = 444;
        assertThrows(AuthorIdException.class, () ->
                verification.verificationAuthorId(id));
    }

    @Test
    void putNullInTitle() {
        assertThrows(LengthTitleException.class, () ->
                verification.lengthTitle(null));
    }

    @Test
    void putToLongValueInTitle() {
        String title = "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm";
        assertThrows(LengthTitleException.class, () ->
                verification.lengthTitle(title));
    }

    @Test
    void putNullInContent() {
        assertThrows(LengthContentException.class, () ->
                verification.lengthContent(null));
    }

    @Test
    void putToShortValueInContent() {
        String content = "qwe";
        assertThrows(LengthContentException.class, () ->
                verification.lengthContent(content));
    }

    @Test
    void checkToCreateNews() {
        NewsDtoResponse expected = new NewsDtoResponse(
                21L
                ,"TestTitle"
                , "TestContent"
                , LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                , LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                , 1L);
        NewsDtoRequest newsDtoRequest = new NewsDtoRequest(
                21L
                , "TestTitle"
                , "TestContent"
                , 1L);

        NewsDtoResponse actual = newsService.createNews(newsDtoRequest);
        assertEquals(expected, actual);
    }

    @Test
    void checkToUpdateNews() {
        LocalDateTime createDate = newsService.readNewsById(1L).getCreateDate();
        NewsDtoResponse expected = new NewsDtoResponse(
                5L
                , "TestTitle"
                , "TestContent"
                , createDate
                , LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                , 5L);

        NewsDtoResponse actual = newsService.updateNews(new NewsDtoRequest(
                5L
                , "TestTitle"
                , "TestContent"
                , 5L));

        assertEquals(expected, actual);
    }

    @Test
    void checkNewsId() {
        long newsId = 44;

        assertThrows(NewsNotFoundException.class, () -> newsService.readNewsById(newsId));
    }

    @Test
    void checkTheSizeOfNewsList() {
        int expected = 20;

        List<NewsDtoResponse> newsList = newsService.readAllNews();

        newsList.forEach(System.out::println);
        assertEquals(expected, newsList.size());
    }
}