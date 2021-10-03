package learning.spring.dao;

import learning.spring.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DisplayName("Тесты AuthorDao")
@JdbcTest
@Import({AuthorDaoJdbc.class})
class AuthorDaoTests {
    private static final String NAME = "name";
    private static final String SUR_NAME = "surname";
    private static final String MIDDLE_NAME = "middlename";
    private static final String EXIST_NAME = "Роджер";
    private static final String EXIST_SUR_NAME = "Желязны";
    private static final String EXIST_MIDDLE_NAME = "Джозеф";

    @Autowired
    AuthorDaoJdbc authorDaoJdbc;

    @Description("Создает автора")
    @Test
    public void createAuthorTest() {
        authorDaoJdbc.createAuthor(NAME, SUR_NAME, MIDDLE_NAME);
        assertNotNull(authorDaoJdbc.getByName(NAME, SUR_NAME, MIDDLE_NAME));
    }

    @Description("Получает данные автора по ID")
    @Test
    public void getByIdTest(){
        Author author = authorDaoJdbc.getById(1L);
        assertEquals(author.getId(), 1L);
        assertEquals(author.getFirstName(), EXIST_NAME);
        assertEquals(author.getSurName(), EXIST_SUR_NAME);
        assertEquals(author.getMiddleName(), EXIST_MIDDLE_NAME);
    }

    @Description("Получает данные по ФИО автора")
    @Test
    public void getByNameTest(){
        Author author = authorDaoJdbc.getByName(EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME);
        assertEquals(author.getId(), 1L);
        assertEquals(author.getFirstName(), EXIST_NAME);
        assertEquals(author.getSurName(), EXIST_SUR_NAME);
        assertEquals(author.getMiddleName(), EXIST_MIDDLE_NAME);
    }
}
