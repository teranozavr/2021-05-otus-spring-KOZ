package learning.spring.service;

import learning.spring.dao.AuthorDao;
import learning.spring.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DisplayName("Тесты AuthorService")
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

    private static final String EXIST_NAME = "Роджер";
    private static final String EXIST_SUR_NAME = "Желязны";
    private static final String EXIST_MIDDLE_NAME = "Джозеф";
    private static final String NOT_EXIST_NAME = "Роджер2";
    private static final String NOT_EXIST_SUR_NAME = "Желязны2";
    private static final String NOT_EXIST_MIDDLE_NAME = "Джозеф2";
    private static final Author existAuthor = new Author(1L, EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME);
    private static final Author notExistAuthor = new Author(1L, NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME);

    @Mock
    private AuthorDao authorDaoMock;
    @InjectMocks
    private AuthorService authorService;

    public static String getExistMiddleName() {
        return EXIST_MIDDLE_NAME;
    }

    @DisplayName("Выводит ФИО автора")
    @Test
    public void printAuthorInfoTest(){
        when(authorDaoMock.getById(anyLong())).thenReturn(existAuthor);
        assertEquals(authorService.printAuthorInfo(1L), "Автор: Роджер Джозеф Желязны\n");
    }

    @DisplayName("Создает автора")
    @Test
    public void createAuthorTest(){
        authorService.createAuthor(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME);
        verify(authorDaoMock, times(1)).createAuthor(eq(existAuthor));
    }

    @DisplayName("Получает ID автора по ФИО")
    @Test
    public void getAuthorIdTest(){
        when(authorDaoMock.getByName(eq(existAuthor))).thenReturn(existAuthor);

        assertNotNull(authorService.getAuthorId(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME));
        verify(authorDaoMock, times(1)).getByName(eq(existAuthor));
    }

    @DisplayName("Получает ID автора по ФИО (негативный тест)")
    @Test
    public void getAuthorIdNegativeTest(){
        when(authorDaoMock.getByName(eq(notExistAuthor))).thenThrow(EmptyResultDataAccessException.class);

        assertNull(authorService.getAuthorId(NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME));

        verify(authorDaoMock, times(1)).getByName(eq(notExistAuthor));
    }

    @DisplayName("Проверяет существование автора")
    @Test
    public void isAuthorExistsTest(){
        when(authorDaoMock.getByName(eq(existAuthor))).thenReturn(existAuthor);

        assertTrue(authorService.isAuthorExists(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME));
    }

    @DisplayName("Проверяет существование автора (негативный тест)")
    @Test
    public void isAuthorExistsNegativeTest(){
        when(authorDaoMock.getByName(eq(notExistAuthor))).thenThrow(EmptyResultDataAccessException.class);

        assertFalse(authorService.isAuthorExists(NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME));
    }

    @DisplayName("Получает Id нового, или существующего автора")
    @Test
    public void addAuthorIdTest(){
        when(authorDaoMock.getByName(eq(existAuthor))).thenReturn(existAuthor);

        assertEquals(authorService.addAuthorId(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME), existAuthor.getId());
        verify(authorDaoMock, times(0)).createAuthor(eq(existAuthor));
    }

    @DisplayName("Получает Id нового, или существующего автора (негативный тест)")
    @Test
    public void addAuthorIdNegativeTest(){
        when(authorDaoMock.getByName(eq(notExistAuthor))).thenThrow(EmptyResultDataAccessException.class);

        assertNull(authorService.addAuthorId(NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME));
        verify(authorDaoMock, times(1)).createAuthor(eq(notExistAuthor));

    }

}
