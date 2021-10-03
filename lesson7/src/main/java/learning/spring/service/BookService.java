package learning.spring.service;

import learning.spring.dao.BookDao;
import learning.spring.domain.Book;
import org.apache.logging.log4j.message.StringFormattedMessage;
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
        if(book == null){
            return null;
        }
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

    public String printBookInfo(String title, Long authorId, Long genreId){
        Book book = bookDao.getByParams(title, authorId, genreId);
        return printBooksInfo(book);
    }

    public String printBooksInfo(List<Book> books){
        StringBuilder sb = new StringBuilder();
        for (Book b: books
        ) {
            sb.append(printBookInfo(b.getId()));
        }
        return sb.toString();
    }

    private String printBooksInfo(Book book){
        List<Book> books = new ArrayList<>();
        books.add(book);
        return printBooksInfo(books);
    }

    public int getBookCount(){
        return bookDao.count();
    }

    public List<Book> getBookList(Long id, String title){
        if(id!=null){
            Book book = bookDao.getById(id);
            if(book != null){
                List<Book> books = new ArrayList<>();
                books.add(bookDao.getById(id));
                return books;
            }
            return null;
        }
        return bookDao.getByTitle(title);
    }

    public void setTitle(Long bookId, String title) {
        bookDao.setTitle(bookId, title);
    }

    public int createBook(String title, Long authorId, Long genreId){
        return bookDao.createBook(title, authorId, genreId);
    }

    public String deleteBook(Book book){
        try{
            bookDao.deleteBook(book.getId());
            return ("Удалена книга с Id: " + book.getId() + "\n");
        }
        catch (Exception e ){
            return "Ошибка удаления книги. \n";
        }
    }

    public String printSelectedBooksInfo(List<Book> books){
        StringBuilder stringBuilder = new StringBuilder();
        if(books==null || books.isEmpty()){
            stringBuilder.append("По данному запросу ни одна книга не найдена.\n");
        }
        else{
            if(books.size()>1){
                stringBuilder.append(new StringFormattedMessage("Выбрано %s книг. Для изменения данных необходимо выбрать одну книгу.\n",books.size()));
            }
            else {
                stringBuilder.append("Выбрана одна книга. Доступны команды изменения/удаления.\n");
            }
            for (Book book : books){
                stringBuilder.append(printBookInfo(book.getId()));
            }
        }
        return stringBuilder.toString();
    }
}
