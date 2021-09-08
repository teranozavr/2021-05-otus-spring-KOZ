package learning.spring.dao;

import learning.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    void insert(Book book);

    Book getById(long id);

    List<Book> getByTitle(String title);

    List<Book> getAll();

    void deleteById(long id);

    int setTitle(Long bookId, String title);
}
