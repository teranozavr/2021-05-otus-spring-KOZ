package learning.spring.service;

import learning.spring.dao.GenreDaoJdbc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты GenreService")
@JdbcTest
@Import({GenreDaoJdbc.class})
public class GenreServiceTests {
    private static final String EXIST_GENRE = "Былина";
    private static final String NOT_EXIST_GENRE = "Небыль";
    private static final String GENRE = "";
    private GenreService genreService;


    @Autowired
    GenreDaoJdbc genreDaoJdbc;

    @BeforeEach
    private void init(){
        genreService = new GenreService(genreDaoJdbc);
    }

    @DisplayName("Выводит название жанра")
    @Test
    public void printGenreInfoTest(){
        assertEquals(genreService.printGenreInfo(1L), "Жанр: Былина\n");
    }

    @DisplayName("Проверяет существование жанра")
    @Test
    public void isGenreExistsTest(){
        assertTrue(genreService.isGenreExists(EXIST_GENRE));
        assertFalse(genreService.isGenreExists(NOT_EXIST_GENRE));
    }

    @DisplayName("Создает жанр")
    @Test
    public void createGenre(){
        assertFalse(genreService.isGenreExists(GENRE));
        genreService.createGenre(GENRE);
        assertTrue(genreService.isGenreExists(GENRE));
        genreService.createGenre(GENRE);
    }

    @DisplayName("Полуает ID жанра по имени")
    @Test
    public void getGenreIdTest(){
        assertNotNull(genreService.getGenreId(EXIST_GENRE));
        assertNull(genreService.getGenreId(NOT_EXIST_GENRE));
    }

    @DisplayName("Получает Id нового, или существующего жанра")
    @Test
    public void addGenreIdTest(){
        assertNotNull(genreService.addGenreId(EXIST_GENRE));
        assertNotNull(genreService.addGenreId(NOT_EXIST_GENRE));
    }
}
