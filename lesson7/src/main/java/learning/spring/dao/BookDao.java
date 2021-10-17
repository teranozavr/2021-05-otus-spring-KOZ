package learning.spring.dao;

import learning.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    Book getById(long id);

    Book getByParams(String title, Long authorId, Long genreID);

    List<Book> getByTitle(String title);

    int updateTitleById(Long bookId, String title);

    int createBook(String title, Long authorId, Long genreId);

    int deleteBook(Long bookId);
}
