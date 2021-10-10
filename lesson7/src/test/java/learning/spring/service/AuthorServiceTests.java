package learning.spring.service;

import learning.spring.dao.AuthorDaoJdbc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты AuthorService")
@JdbcTest
@Import({AuthorDaoJdbc.class})
public class AuthorServiceTests {
    private AuthorService authorService;
    private static final String NAME = "name";
    private static final String SUR_NAME = "surname";
    private static final String MIDDLE_NAME = "middlename";
    private static final String EXIST_NAME = "Роджер";
    private static final String EXIST_SUR_NAME = "Желязны";
    private static final String EXIST_MIDDLE_NAME = "Джозеф";
    private static final String NOT_EXIST_NAME = "Роджер2";
    private static final String NOT_EXIST_SUR_NAME = "Желязны2";
    private static final String NOT_EXIST_MIDDLE_NAME = "Джозеф2";

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @BeforeEach
    private void init(){
        authorService = new AuthorService(authorDaoJdbc);
    }

    @DisplayName("Выводит ФИО автора")
    @Test
    public void printAuthorInfoTest(){
        assertEquals(authorService.printAuthorInfo(1L), "Автор: Роджер Джозеф Желязны\n");
    }

    @DisplayName("Создает автора")
    @Test
    public void createAuthorTest(){
        assertFalse(authorService.isAuthorExists(NAME, SUR_NAME, MIDDLE_NAME));
        authorService.createAuthor(NAME, SUR_NAME, MIDDLE_NAME);
        assertTrue(authorService.isAuthorExists(NAME, SUR_NAME, MIDDLE_NAME));
        authorService.createAuthor(NAME, SUR_NAME, MIDDLE_NAME);
    }

    @DisplayName("Получает ID автора по ФИО")
    @Test
    public void getAuthorIdTest(){
        assertNotNull(authorService.getAuthorId(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME));
        assertNull(authorService.getAuthorId(NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME));
    }

    @DisplayName("Проверяет существование автора")
    @Test
    public void isAuthorExistsTest(){
        assertTrue(authorService.isAuthorExists(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME));
        assertFalse(authorService.isAuthorExists(NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME));
    }

    @DisplayName("Получает Id нового, или существующего автора")
    @Test
    public void addAuthorIdTest(){
        assertNotNull(authorService.addAuthorId(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME));
        assertNotNull(authorService.addAuthorId(NOT_EXIST_NAME, NOT_EXIST_SUR_NAME, NOT_EXIST_MIDDLE_NAME));
    }

}
