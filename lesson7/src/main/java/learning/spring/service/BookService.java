package learning.spring.service;

import learning.spring.dao.BookDao;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public String getBookById(String id) {
        return "Book " + id;
    }

    public int getBookCount(){
        return bookDao.count();
    }

}
