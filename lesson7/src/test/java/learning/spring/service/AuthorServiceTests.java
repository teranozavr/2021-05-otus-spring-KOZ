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
    private static final Author EXIST_AUTHOR = new Author(1L, EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME);
    private static final Author NOT_EXIST_AUTHOR = new Author(1L, NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME);

    @Mock
    private AuthorDao authorDao;
    @InjectMocks
    private AuthorService authorService;

    @DisplayName("Выводит ФИО автора")
    @Test
    public void printAuthorInfoTest(){
        when(authorDao.getById(anyLong())).thenReturn(EXIST_AUTHOR);
        assertEquals(authorService.printAuthorInfo(1L), "Автор: Роджер Джозеф Желязны\n");
    }

    @DisplayName("Создает автора")
    @Test
    public void createAuthorTest(){
        authorService.createAuthor(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME);
        verify(authorDao, times(1)).createAuthor(eq(EXIST_AUTHOR));
    }

    @DisplayName("Получает ID автора по ФИО")
    @Test
    public void getAuthorIdTest(){
        when(authorDao.getByName(eq(EXIST_AUTHOR))).thenReturn(EXIST_AUTHOR);

        assertNotNull(authorService.getAuthorId(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME));
        verify(authorDao, times(1)).getByName(eq(EXIST_AUTHOR));
    }

    @DisplayName("Получает ID автора по ФИО (негативный тест)")
    @Test
    public void getAuthorIdNegativeTest(){
        when(authorDao.getByName(eq(NOT_EXIST_AUTHOR))).thenThrow(EmptyResultDataAccessException.class);

        assertNull(authorService.getAuthorId(NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME));

        verify(authorDao, times(1)).getByName(eq(NOT_EXIST_AUTHOR));
    }

    @DisplayName("Проверяет существование автора")
    @Test
    public void isAuthorExistsTest(){
        when(authorDao.getByName(eq(EXIST_AUTHOR))).thenReturn(EXIST_AUTHOR);

        assertTrue(authorService.isAuthorExists(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME));
    }

    @DisplayName("Проверяет существование автора (негативный тест)")
    @Test
    public void isAuthorExistsNegativeTest(){
        when(authorDao.getByName(eq(NOT_EXIST_AUTHOR))).thenThrow(EmptyResultDataAccessException.class);

        assertFalse(authorService.isAuthorExists(NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME));
    }

    @DisplayName("Получает Id нового, или существующего автора")
    @Test
    public void addAuthorIdTest(){
        when(authorDao.getByName(eq(EXIST_AUTHOR))).thenReturn(EXIST_AUTHOR);

        assertEquals(authorService.addAuthorId(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME), EXIST_AUTHOR.getId());
        verify(authorDao, times(0)).createAuthor(eq(EXIST_AUTHOR));
    }

    @DisplayName("Получает Id нового, или существующего автора (негативный тест)")
    @Test
    public void addAuthorIdNegativeTest(){
        when(authorDao.getByName(eq(NOT_EXIST_AUTHOR))).thenThrow(EmptyResultDataAccessException.class);

        assertNull(authorService.addAuthorId(NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME));
        verify(authorDao, times(1)).createAuthor(eq(NOT_EXIST_AUTHOR));

    }

}
