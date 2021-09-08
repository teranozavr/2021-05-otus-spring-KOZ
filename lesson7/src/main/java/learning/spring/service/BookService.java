package learning.spring.service;

import learning.spring.dao.BookDao;
import learning.spring.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookService(BookDao bookDao, AuthorService authorService, GenreService genreService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    public String printBookInfo(long id){
        Book book = bookDao.getById(id);
        StringBuilder sb = new StringBuilder()
                .append("Id: ")
                .append(book.getId())
                .append("\n")
                .append(" ")
                .append("Название: ")
                .append(book.getTitle())
                .append("\n")
                .append(" ")
                .append(authorService.printAuthorInfo(book.getAuthorId()))
                .append(" ")
                .append(genreService.printGenreInfo(book.getGenreId()))
                .append("\n");
        return sb.toString();
    }

    public String printBookInfo(String title){
        List<Book> books = bookDao.getByTitle(title);
        return printBooksInfo(books);
    }

    public String printBooksInfo(List<Book> books){
        StringBuilder sb = new StringBuilder();
        for (Book b: books
        ) {
            sb.append(printBookInfo(b.getId()))
                    .append("\n");
        }
        return sb.toString();
    }

    public int getBookCount(){
        return bookDao.count();
    }

    public List<Book> getBookList(Long id, String title){
        if(id!=null){
            List<Book> books = new ArrayList<>();
            books.add(bookDao.getById(id));
            return books;
        }
        return bookDao.getByTitle(title);
    }

    public void setTitle(Long bookId, String title) {
        bookDao.setTitle(bookId, title);
    }
}
