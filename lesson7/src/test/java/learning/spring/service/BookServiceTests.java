package learning.spring.service;

import learning.spring.dao.AuthorDaoJdbc;
import learning.spring.dao.BookDaoJdbc;
import learning.spring.dao.GenreDaoJdbc;
import learning.spring.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@DisplayName("Тесты BookService")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
public class BookServiceTests {

    private BookService bookService;
    private static final String TEST_TITLE = "Spring. Все паттерны проектирования";

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
        Assertions.assertEquals(bookService.printBookInfo(1L), "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n");
    }

    @DisplayName("Выводит информацию о книге по Title")
    @Test
    void printBookInfoByTitleTest() {
        Assertions.assertEquals(bookService.printBookInfo("Евгений Онегин"), "Id: 1\n" +
                " Название: Евгений Онегин\n" +
                " Автор: Александр Сергеевич Пушкин\n" +
                " Жанр: Роман\n" +
                "\n");
    }

    @DisplayName("Выводит количество книг")
    @Test
    void getBookCount() {
        Assertions.assertEquals(bookService.getBookCount(), 3);
    }

    @DisplayName("Устанавливает Title")
    @Test
    void setTitleTest(){
        bookService.setTitle(1L, "Test");
        Assertions.assertEquals(bookService.printBookInfo(1L), "Id: 1\n" +
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
        Assertions.assertEquals(bookCount+1, bookService.getBookCount());
        Assertions.assertTrue(bookService.printBookInfo(TEST_TITLE).contains(TEST_TITLE));
    }

    @DisplayName("Удаляет книгу")
    @Test
    void deleteBookTest(){
        Book book = new Book(1L, 1L, 1L, TEST_TITLE);
        int bookCount = bookService.getBookCount();
        bookService.deleteBook(book);
        Assertions.assertEquals(bookCount-1, bookService.getBookCount());
    }
}
