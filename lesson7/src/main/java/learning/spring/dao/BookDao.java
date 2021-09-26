package learning.spring.dao;

import learning.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    Book getById(long id);

    List<Book> getByTitle(String title);

    int setTitle(Long bookId, String title);

    int createBook(String title, Long authorId, Long genreId);

    int deleteBook(Long bookId);
}
