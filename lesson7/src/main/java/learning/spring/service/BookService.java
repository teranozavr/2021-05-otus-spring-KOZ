package learning.spring.service;

import learning.spring.domain.Author;
import learning.spring.domain.Book;
import learning.spring.domain.Genre;

import java.util.List;

public interface BookService {
    String printBookInfo(long id);

    String printBookInfo(String title);

    String printBookInfo(String title, Author author, Genre genre);

    String printBooksInfo(List<Book> books);

    int getBookCount();

    List<Book> getBookList(Long id, String title);

    void setTitle(Long bookId, String title);

    int createBook(String title, Author author, Genre genre);

    String deleteBook(Book book);

    String printSelectedBooksInfo(List<Book> books);
}
