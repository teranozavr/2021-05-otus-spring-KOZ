package learning.spring.service;

import learning.spring.dao.BookDao;
import learning.spring.domain.Book;
import org.springframework.stereotype.Service;

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

    public int getBookCount(){
        return bookDao.count();
    }

}
