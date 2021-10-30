package learning.spring.service;

import learning.spring.dao.*;
import learning.spring.domain.Author;
import learning.spring.domain.Book;
import learning.spring.domain.Genre;
import learning.spring.service.impl.AuthorServiceDefault;
import learning.spring.service.impl.BookServiceDefault;
import learning.spring.service.impl.GenreServiceDefault;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayName("Тесты BookService")
@ExtendWith(MockitoExtension.class)
public class BookServiceDefaultTests {

    private static final String TEST_TITLE = "Spring. Все паттерны проектирования";
    private static final Long EXIST_BOOK_ID = 1L;
    private static final Long EXIST_AUTHOR_ID = 1L;
    private static final Long EXIST_GENRE_ID = 5L;
    private static final String EXIST_TITLE = "Евгений Онегин";

    private static final String EXIST_NAME = "Александр";
    private static final String EXIST_SUR_NAME = "Пушкин";
    private static final String EXIST_MIDDLE_NAME = "Сергеевич";

    private static final String EXIST_GENRE_NAME = "Роман";
    private static final Genre EXIST_GENRE = new Genre(EXIST_GENRE_ID, EXIST_GENRE_NAME);

    private static final Author EXIST_AUTHOR = new Author(1L, EXIST_NAME, EXIST_SUR_NAME, EXIST_MIDDLE_NAME);

    private static final Book BOOK = new Book(EXIST_BOOK_ID, EXIST_AUTHOR, EXIST_GENRE, EXIST_TITLE);
    private static final List<Book> BOOKS = new ArrayList<>();

    @Mock
    private AuthorDao authorDao;

    @Mock
    private AuthorServiceDefault authorService;

    @Mock
    GenreDao genreDao;

    @Mock
    private GenreServiceDefault genreService;

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private BookServiceDefault bookService;

    @BeforeEach
    private void init(){
        ReflectionTestUtils.setField(authorService, "authorDao", authorDao);
        ReflectionTestUtils.setField(genreService, "genreDao", genreDao);
        if(BOOKS.size()==0) BOOKS.add(BOOK);
    }

    @DisplayName("Выводит информацию о книге по ID")
    @Test
    void printBookInfoByIdTest() {
        when(authorService.printAuthorInfo(anyLong())).thenCallRealMethod();
        when(authorDao.getById(EXIST_AUTHOR_ID)).thenReturn(EXIST_AUTHOR);
        when(genreService.printGenreInfo(anyLong())).thenCallRealMethod();
        when(genreDao.getById(EXIST_GENRE_ID)).thenReturn(EXIST_GENRE);
        when(bookDao.getById(any(Long.class))).thenReturn(BOOK);

        assertEquals(bookService.printBookInfo(EXIST_BOOK_ID), "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n");
    }

    @DisplayName("Выводит информацию о книге по Title")
    @Test
    void printBookInfoByTitleTest() {
        when(authorService.printAuthorInfo(anyLong())).thenCallRealMethod();
        when(authorDao.getById(EXIST_AUTHOR_ID)).thenReturn(EXIST_AUTHOR);
        when(genreService.printGenreInfo(anyLong())).thenCallRealMethod();
        when(genreDao.getById(EXIST_GENRE_ID)).thenReturn(EXIST_GENRE);
        when(bookDao.getByTitle(EXIST_TITLE)).thenReturn(BOOKS);
        when(bookDao.getById(any(Long.class))).thenReturn(BOOK);

        assertEquals(bookService.printBookInfo(EXIST_TITLE), "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n");
    }

    @DisplayName("\"Выводит информацию о книге по Title, authorId, genreId")
    @Test
    void printBookInfoByParamsTest(){
        when(authorService.printAuthorInfo(anyLong())).thenCallRealMethod();
        when(authorDao.getById(EXIST_AUTHOR_ID)).thenReturn(EXIST_AUTHOR);
        when(genreService.printGenreInfo(anyLong())).thenCallRealMethod();
        when(genreDao.getById(EXIST_GENRE_ID)).thenReturn(EXIST_GENRE);
        when(bookDao.getById(any(Long.class))).thenReturn(BOOK);
        when(bookDao.getByParams(BOOK)).thenReturn(BOOK);

        assertEquals(bookService.printBookInfo(EXIST_TITLE, EXIST_AUTHOR, EXIST_GENRE), "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n");
    }

    @DisplayName("Выводит количество книг")
    @Test
    void getBookCount() {
        when(bookDao.count()).thenReturn(3);
        assertEquals(bookService.getBookCount(), 3);
    }

    @DisplayName("Устанавливает Title")
    @Test
    void setTitleTest(){
        when(bookDao.updateTitleById(EXIST_BOOK_ID, EXIST_TITLE)).thenReturn(1);
        bookService.setTitle(EXIST_BOOK_ID, EXIST_TITLE);
        verify(bookDao, times(1)).updateTitleById(eq(EXIST_BOOK_ID), eq(EXIST_TITLE));
    }

    @DisplayName("Создает книгу")
    @Test
    void createBookTest(){
        when(bookDao.createBook(BOOK)).thenReturn(1);
        assertEquals(bookService.createBook(EXIST_TITLE, EXIST_AUTHOR, EXIST_GENRE), 1);
        verify(bookDao, times(1)).createBook(eq(BOOK));
    }

    @DisplayName("Удаляет книгу")
    @Test
    void deleteBookTest(){
        when(bookDao.deleteBook(BOOK)).thenReturn(1);
        assertEquals(bookService.deleteBook(BOOK), "Удалена книга с Id: 1\n");
        verify(bookDao, times(1)).deleteBook(eq(BOOK));
    }

    @DisplayName("Удаляет книгу (негативный тест)")
    @Test
    void deleteBookNegativeTest(){
        when(bookDao.deleteBook(BOOK)).thenThrow(new RuntimeException());
        assertEquals(bookService.deleteBook(BOOK), "Ошибка удаления книги. \n");
        verify(bookDao, times(1)).deleteBook(eq(BOOK));
    }

    @DisplayName("Печатает информацию о книге")
    @Test
    void printSelectedBooksInfoOneBookTest(){
        when(authorService.printAuthorInfo(anyLong())).thenCallRealMethod();
        when(authorDao.getById(EXIST_AUTHOR_ID)).thenReturn(EXIST_AUTHOR);
        when(genreService.printGenreInfo(anyLong())).thenCallRealMethod();
        when(genreDao.getById(EXIST_GENRE_ID)).thenReturn(EXIST_GENRE);
        when(bookDao.getById(any(Long.class))).thenReturn(BOOK);
        assertEquals(bookService.printSelectedBooksInfo(BOOKS), "Выбрана одна книга. Доступны команды изменения/удаления.\n" +
                "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n\n");
    }

    @DisplayName("Печатает информацию о нескольких книгах")
    @Test
    void printSelectedBooksInfoMultyBookTest(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, EXIST_AUTHOR, EXIST_GENRE, TEST_TITLE));
        books.add(new Book(EXIST_BOOK_ID, EXIST_AUTHOR, EXIST_GENRE, EXIST_TITLE));

        when(authorService.printAuthorInfo(anyLong())).thenCallRealMethod();
        when(authorDao.getById(EXIST_AUTHOR_ID)).thenReturn(EXIST_AUTHOR);
        when(genreService.printGenreInfo(anyLong())).thenCallRealMethod();
        when(genreDao.getById(EXIST_GENRE_ID)).thenReturn(EXIST_GENRE);
        when(bookDao.getById(any(Long.class))).thenReturn(BOOK);

        assertEquals(bookService.printSelectedBooksInfo(books), "Выбрано 2 книг. Для изменения данных необходимо выбрать одну книгу.\n" +
                "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n" +
                "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n\n");
    }

    @DisplayName("Печатает информацию об отсутствующей книге")
    @Test
    void printSelectedBooksInfoEmptyBookTest(){
        List<Book> books = null;
        assertEquals(bookService.printSelectedBooksInfo(books), "По данному запросу ни одна книга не найдена.\n");
    }
}
