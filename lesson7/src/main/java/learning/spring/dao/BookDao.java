package learning.spring.dao;

import learning.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    Book getById(long id);

    Book getByParams(Book book);

    List<Book> getByTitle(String title);

    int updateTitleById(Long bookId, String title);

    int createBook(Book book);

    int deleteBook(Book book);
}
