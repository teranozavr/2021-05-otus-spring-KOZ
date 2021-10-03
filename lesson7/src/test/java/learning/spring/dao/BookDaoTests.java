package learning.spring.dao;

import learning.spring.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DisplayName("Тесты BookDao")
@JdbcTest
@Import({BookDaoJdbc.class})
class BookDaoTests {

    private static final String EXIST_TITLE = "Евгений Онегин";
    private static final String NOT_EXIST_TITLE = "Новый Евгений Онегин";
    private static final Long EXIST_BOOK_ID = 1L;
    private static final Long EXIST_AUTHOR_ID = 2L;
    private static final Long EXIST_GENRE_ID = 5L;
    private Book existBook = new Book(EXIST_BOOK_ID, EXIST_AUTHOR_ID, EXIST_GENRE_ID, EXIST_TITLE);
    @Autowired
    BookDaoJdbc bookDaoJdbc;

    @DisplayName("Выводит количество книг")
    @Test
    void countTest() {
        Assertions.assertEquals(bookDaoJdbc.count(), 3);
    }

    @DisplayName("Получает данные книги по ID")
    @Test
    public void getByIdTest(){
        Book book = bookDaoJdbc.getById(1L);
        assertEquals(book.getTitle(), existBook.getTitle());
        assertEquals(book.getAuthorId(), existBook.getAuthorId());
        assertEquals(book.getGenreId(), existBook.getGenreId());
        assertEquals(book.getId(), existBook.getId());
    }

    @DisplayName("Получает данные книги по title, authorId, genreId")
    @Test
    public void getByParamsTest(){
        Book book = bookDaoJdbc.getByParams(EXIST_TITLE, EXIST_AUTHOR_ID, EXIST_GENRE_ID);
        assertEquals(book.getTitle(), existBook.getTitle());
        assertEquals(book.getAuthorId(), existBook.getAuthorId());
        assertEquals(book.getGenreId(), existBook.getGenreId());
        assertEquals(book.getId(), existBook.getId());
    }

    @DisplayName("Получает данные книги по названию")
    @Test
    public void getByTitleTest(){
        List<Book> books = bookDaoJdbc.getByTitle(EXIST_TITLE);
        assertEquals(1, books.size());
        Book book = books.get(0);
        assertEquals(book.getTitle(), existBook.getTitle());
        assertEquals(book.getAuthorId(), existBook.getAuthorId());
        assertEquals(book.getGenreId(), existBook.getGenreId());
        assertEquals(book.getId(), existBook.getId());
    }

    @Description("Создает книгу")
    @Test
    public void createBookTest() {
        int bookCount = bookDaoJdbc.count();
        List<Book> books = bookDaoJdbc.getByTitle(NOT_EXIST_TITLE);
        assertEquals(0, books.size());
        int createStatus = bookDaoJdbc.createBook(NOT_EXIST_TITLE, EXIST_AUTHOR_ID, EXIST_GENRE_ID);
        books = bookDaoJdbc.getByTitle(NOT_EXIST_TITLE);
        Book book = books.get(0);
        assertEquals(1, createStatus);
        assertTrue(++bookCount == bookDaoJdbc.count());
        assertEquals(1, books.size());
        assertEquals(book.getTitle(), NOT_EXIST_TITLE);
        assertEquals(book.getAuthorId(), existBook.getAuthorId());
        assertEquals(book.getGenreId(), existBook.getGenreId());
        assertNotEquals(book.getId(), existBook.getId());
        createStatus = bookDaoJdbc.createBook(NOT_EXIST_TITLE, EXIST_AUTHOR_ID, EXIST_GENRE_ID);
        assertTrue(bookCount == bookDaoJdbc.count());
        assertEquals(-1, createStatus);
    }

    @Description("Удаляет книгу по ID")
    @Test
    public void deleteBookTest(){
        int bookCount = bookDaoJdbc.count();
        assertNotNull(bookDaoJdbc.getById(EXIST_BOOK_ID));
        bookDaoJdbc.deleteBook(EXIST_BOOK_ID);
        assertTrue(-- bookCount ==  bookDaoJdbc.count());
        assertNull(bookDaoJdbc.getById(EXIST_BOOK_ID));

    }
}
