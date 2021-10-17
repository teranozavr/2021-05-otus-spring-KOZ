package learning.spring.service;

import learning.spring.domain.Book;

import java.util.List;

public interface BookService {
    String printBookInfo(long id);

    String printBookInfo(String title);

    String printBookInfo(String title, Long authorId, Long genreId);

    String printBooksInfo(List<Book> books);

    int getBookCount();

    List<Book> getBookList(Long id, String title);

    void setTitle(Long bookId, String title);

    int createBook(String title, Long authorId, Long genreId);

    String deleteBook(Book book);

    String printSelectedBooksInfo(List<Book> books);
}
