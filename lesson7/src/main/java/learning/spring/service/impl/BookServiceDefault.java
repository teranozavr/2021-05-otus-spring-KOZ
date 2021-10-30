package learning.spring.service.impl;

import learning.spring.dao.BookDao;
import learning.spring.domain.Author;
import learning.spring.domain.Book;
import learning.spring.domain.Genre;
import learning.spring.service.BookService;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceDefault implements BookService {
    private final BookDao bookDao;
    private final AuthorServiceDefault authorService;
    private final GenreServiceDefault genreService;

    public BookServiceDefault(BookDao bookDao, AuthorServiceDefault authorService, GenreServiceDefault genreService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @Override
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
                .append(authorService.printAuthorInfo(book.getAuthor().getId()))
                .append(" ")
                .append(genreService.printGenreInfo(book.getGenre().getId()))
                .append("\n");
        return sb.toString();
    }

    @Override
    public String printBookInfo(String title){
        List<Book> books = bookDao.getByTitle(title);
        return printBooksInfo(books);
    }

    @Override
    public String printBookInfo(String title, Author author, Genre genre){
        Book book = bookDao.getByParams(new Book(1L, author, genre, title));
        return printBooksInfo(book);
    }

    @Override
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

    @Override
    public int getBookCount(){
        return bookDao.count();
    }

    @Override
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

    @Override
    public void setTitle(Long bookId, String title) {
        bookDao.updateTitleById(bookId, title);
    }

    @Override
    public int createBook(String title, Author author, Genre genre){
        Book book = new Book(1L, author, genre, title);
        return bookDao.createBook(book);
    }

    @Override
    public String deleteBook(Book book){
        try{
            bookDao.deleteBook(book);
            return ("Удалена книга с Id: " + book.getId() + "\n");
        }
        catch (Exception e ){
            return "Ошибка удаления книги. \n";
        }
    }

    @Override
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
