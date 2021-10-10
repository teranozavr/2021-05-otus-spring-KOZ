package learning.spring.dao;

import learning.spring.domain.Genre;
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

@DisplayName("Тесты GenreDao")
@JdbcTest
@Import({GenreDaoJdbc.class})
class GenreDaoTests {
    private static final String EXIST_GENRE = "Былина";
    private static final String NOT_EXIST_GENRE = "Небыль";

    @Autowired
    GenreDaoJdbc genreDaoJdbc;

    @DisplayName("Создает жанр")
    @Test
    public void createGenreTest() {
        genreDaoJdbc.createGenre(NOT_EXIST_GENRE);
        assertNotNull(genreDaoJdbc.getByName(NOT_EXIST_GENRE));
    }

    @DisplayName("Получает данные жанра по ID")
    @Test
    public void getByIdTest(){
        Genre genre = genreDaoJdbc.getById(1L);
        assertEquals(genre.getId(), 1L);
        assertEquals(genre.getGenreName(), EXIST_GENRE);
    }

    @DisplayName("Получает данные по имени жанра")
    @Test
    public void getByNameTest(){
        Genre genre = genreDaoJdbc.getByName(EXIST_GENRE);
        assertEquals(genre.getId(), 1L);
        assertEquals(genre.getGenreName(), EXIST_GENRE);
    }
}
