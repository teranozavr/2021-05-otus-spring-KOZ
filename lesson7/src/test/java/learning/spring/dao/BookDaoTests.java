package learning.spring.dao;

import learning.spring.domain.Author;
import learning.spring.domain.Book;
import learning.spring.domain.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты BookDao")
@JdbcTest
@Import({BookDaoJdbc.class})
class BookDaoTests {

    private static final String EXIST_TITLE = "Евгений Онегин";
    private static final String NOT_EXIST_TITLE = "Новый Евгений Онегин";
    private static final Long EXIST_BOOK_ID = 1L;
    private static final Author EXIST_AUTHOR = new Author(2L, "Александр", "Пушкин", "Сергеевич");
    private static final Genre EXIST_GENRE = new Genre(5L, "Роман");
    private static final Book existBook = new Book(EXIST_BOOK_ID, EXIST_AUTHOR, EXIST_GENRE, EXIST_TITLE);
    private static final Book NOT_EXIST_BOOK = new Book(EXIST_BOOK_ID, EXIST_AUTHOR, EXIST_GENRE, NOT_EXIST_TITLE);
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
        assertEquals(book.getAuthor(), existBook.getAuthor());
        assertEquals(book.getGenre(), existBook.getGenre());
        assertEquals(book.getId(), existBook.getId());
    }

    @DisplayName("Получает данные книги по title, authorId, genreId")
    @Test
    public void getByParamsTest(){
        Book book = bookDaoJdbc.getByParams(existBook);
        assertEquals(book.getTitle(), existBook.getTitle());
        assertEquals(book.getAuthor(), existBook.getAuthor());
        assertEquals(book.getGenre(), existBook.getGenre());
        assertEquals(book.getId(), existBook.getId());
    }

    @DisplayName("Получает данные книги по названию")
    @Test
    public void getByTitleTest(){
        List<Book> books = bookDaoJdbc.getByTitle(EXIST_TITLE);
        assertEquals(1, books.size());
        Book book = books.get(0);
        assertEquals(book.getTitle(), existBook.getTitle());
        assertEquals(book.getAuthor(), existBook.getAuthor());
        assertEquals(book.getGenre(), existBook.getGenre());
        assertEquals(book.getId(), existBook.getId());
    }

    @DisplayName("Создает книгу")
    @Test
    public void createBookTest() {
        int bookCount = bookDaoJdbc.count();
        List<Book> books = bookDaoJdbc.getByTitle(NOT_EXIST_TITLE);
        assertEquals(0, books.size());
        int createStatus = bookDaoJdbc.createBook(NOT_EXIST_BOOK);
        books = bookDaoJdbc.getByTitle(NOT_EXIST_TITLE);
        Book book = books.get(0);
        assertEquals(1, createStatus);
        assertTrue(++bookCount == bookDaoJdbc.count());
        assertEquals(1, books.size());
        assertEquals(book.getTitle(), NOT_EXIST_TITLE);
        assertEquals(book.getAuthor(), existBook.getAuthor());
        assertEquals(book.getGenre(), existBook.getGenre());
        assertNotEquals(book.getId(), existBook.getId());
        createStatus = bookDaoJdbc.createBook(NOT_EXIST_BOOK);
        assertTrue(bookCount == bookDaoJdbc.count());
        assertEquals(-1, createStatus);
    }

    @DisplayName("Удаляет книгу по ID")
    @Test
    public void deleteBookTest(){
        int bookCount = bookDaoJdbc.count();
        assertNotNull(bookDaoJdbc.getById(EXIST_BOOK_ID));
        bookDaoJdbc.deleteBook(existBook);
        assertTrue(-- bookCount ==  bookDaoJdbc.count());
        assertNull(bookDaoJdbc.getById(EXIST_BOOK_ID));

    }
}
