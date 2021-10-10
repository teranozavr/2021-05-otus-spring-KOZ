package learning.spring.service;

import learning.spring.dao.AuthorDaoJdbc;
import learning.spring.dao.BookDaoJdbc;
import learning.spring.dao.GenreDaoJdbc;
import learning.spring.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты BookService")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
public class BookServiceTests {

    private BookService bookService;
    private static final String TEST_TITLE = "Spring. Все паттерны проектирования";
    private static final Long EXIST_BOOK_ID = 1L;
    private static final Long EXIST_AUTHOR_ID = 2L;
    private static final Long EXIST_GENRE_ID = 5L;
    private static final String EXIST_TITLE = "Евгений Онегин";

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @BeforeEach
    private void init(){
        AuthorService authorService = new AuthorService(authorDaoJdbc);
        GenreService genreService = new GenreService(genreDaoJdbc);
        bookService = new BookService(bookDaoJdbc, authorService, genreService);
    }

    @DisplayName("Выводит информацию о книге по ID")
    @Test
    void printBookInfoByIdTest() {
        assertEquals(bookService.printBookInfo(EXIST_BOOK_ID), "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n");
    }

    @DisplayName("Выводит информацию о книге по Title")
    @Test
    void printBookInfoByTitleTest() {
        assertEquals(bookService.printBookInfo(EXIST_TITLE), "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n");
    }

    @DisplayName("\"Выводит информацию о книге по Title, authorId, genreId")
    @Test
    void printBookInfoByParamsTest(){
        assertEquals(bookService.printBookInfo(EXIST_TITLE, EXIST_AUTHOR_ID, EXIST_GENRE_ID), "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n");
    }

    @DisplayName("Выводит количество книг")
    @Test
    void getBookCount() {
        assertEquals(bookService.getBookCount(), 3);
    }

    @DisplayName("Устанавливает Title")
    @Test
    void setTitleTest(){
        bookService.setTitle(1L, "Test");
        assertEquals(bookService.printBookInfo(EXIST_BOOK_ID), "Id: 1\n" +
                " Название: Test\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n");
    }

    @DisplayName("Создает книгу")
    @Test
    void createBookTest(){
        int bookCount = bookService.getBookCount();
        bookService.createBook(TEST_TITLE, 1L, 1L);
        assertEquals(bookCount+1, bookService.getBookCount());
        assertTrue(bookService.printBookInfo(TEST_TITLE).contains(TEST_TITLE));
    }

    @DisplayName("Удаляет книгу")
    @Test
    void deleteBookTest(){
        Book book = new Book(1L, 1L, 1L, TEST_TITLE);
        int bookCount = bookService.getBookCount();
        bookService.deleteBook(book);
        assertEquals(bookCount-1, bookService.getBookCount());
    }

    @DisplayName("Печатает информацию о книге")
    @Test
    void printSelectedBooksInfoOneBookTest(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, 2L, 5L, TEST_TITLE));
        assertEquals(bookService.printSelectedBooksInfo(books), "Выбрана одна книга. Доступны команды изменения/удаления.\n" +
                "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n\n");
    }

    @DisplayName("Печатает информацию о нескольких книгах")
    @Test
    void printSelectedBooksInfoMultyBookTest(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, 2L, 5L, TEST_TITLE));
        books.add(new Book(EXIST_BOOK_ID, EXIST_AUTHOR_ID, EXIST_GENRE_ID, EXIST_TITLE));

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
