package learning.spring.service;

import learning.spring.dao.GenreDaoJdbc;
import learning.spring.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayName("Тесты GenreService")
@ExtendWith(MockitoExtension.class)
public class GenreServiceTests {
    private static final Long EXIST_GENRE_ID = 1L;
    private static final String EXIST_GENRE_NAME = "Былина";
    private static final Genre EXIST_GENRE = new Genre(EXIST_GENRE_ID, EXIST_GENRE_NAME);
    private static final String NOT_EXIST_GENRE_NAME = "Небыль";

    @Mock
    private GenreDaoJdbc genreDaoJdbc;
    @InjectMocks
    private GenreService genreService;

    @DisplayName("Выводит название жанра")
    @Test
    public void printGenreInfoTest(){
        when(genreDaoJdbc.getById(anyLong())).thenReturn(EXIST_GENRE);
        assertEquals(genreService.printGenreInfo(1L), "Жанр: Былина\n");
    }

    @DisplayName("Выводит название жанра (негативный тест)")
    @Test
    public void printGenreInfoNegativeTest(){
        when(genreDaoJdbc.getById(anyLong())).thenThrow(EmptyResultDataAccessException.class);
        assertEquals(genreService.printGenreInfo(1L), null);
    }

    @DisplayName("Проверяет существование жанра")
    @Test
    public void isGenreExistsTest(){
        when(genreDaoJdbc.getByName(eq(EXIST_GENRE_NAME))).thenReturn(EXIST_GENRE);
        assertTrue(genreService.isGenreExists(EXIST_GENRE_NAME));
    }

    @DisplayName("Проверяет существование жанра (негативный тест)")
    @Test
    public void isGenreExistsNegativeTest(){
        when(genreDaoJdbc.getByName(eq(NOT_EXIST_GENRE_NAME))).thenThrow(EmptyResultDataAccessException.class);
        assertFalse(genreService.isGenreExists(NOT_EXIST_GENRE_NAME));
    }

    @DisplayName("Создает жанр")
    @Test
    public void createGenreTest(){
        when(genreDaoJdbc.createGenre(eq(EXIST_GENRE_NAME))).thenReturn(1);
        genreService.createGenre(EXIST_GENRE_NAME);

        verify(genreDaoJdbc, times(1)).createGenre(eq(EXIST_GENRE_NAME));
    }

    @DisplayName("Создает жанр (негативный тест)")
    @Test
    public void createGenreNegativeTest(){
        when(genreDaoJdbc.createGenre(eq(NOT_EXIST_GENRE_NAME))).thenThrow(NullPointerException.class);
        genreService.createGenre(NOT_EXIST_GENRE_NAME);

        verify(genreDaoJdbc, times(1)).createGenre(eq(NOT_EXIST_GENRE_NAME));
    }

    @DisplayName("Полуает ID жанра по имени")
    @Test
    public void getGenreIdTest(){
        when(genreDaoJdbc.getByName(eq(EXIST_GENRE_NAME))).thenReturn(EXIST_GENRE);

        assertEquals(genreService.getGenreId(EXIST_GENRE_NAME), EXIST_GENRE.getId());
    }

    @DisplayName("Полуает ID жанра по имени (негативный тест)")
    @Test
    public void getGenreIdNegativeTest(){
        when(genreDaoJdbc.getByName(eq(NOT_EXIST_GENRE_NAME))).thenThrow(EmptyResultDataAccessException.class);

        assertNull(genreService.getGenreId(NOT_EXIST_GENRE_NAME));
    }

    @DisplayName("Получает Id существующего жанра")
    @Test
    public void addGenreIdTest(){
        when(genreDaoJdbc.getByName(eq(EXIST_GENRE_NAME))).thenReturn(EXIST_GENRE);

        assertEquals(genreService.addGenreId(EXIST_GENRE_NAME), EXIST_GENRE.getId());
        verify(genreDaoJdbc, times(0)).createGenre(eq(EXIST_GENRE_NAME));
    }

    @DisplayName("Получает Id нового жанра")
    @Test
    public void addGenreIdNegativeTest(){
        when(genreDaoJdbc.getByName(eq(NOT_EXIST_GENRE_NAME))).thenThrow(EmptyResultDataAccessException.class);

        assertNull(genreService.addGenreId(NOT_EXIST_GENRE_NAME));
        verify(genreDaoJdbc, times(1)).createGenre(eq(NOT_EXIST_GENRE_NAME));
    }
}
