package learning.spring.service;

import learning.spring.domain.Book;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class Commands {

    private final BookService bookService;

    private List<Book> books;

    private static final String NULL = null;

    public Commands(BookService bookService) {
        this.bookService = bookService;
    }


    @ShellMethod("Select book")
    public String selectBook(@ShellOption(defaultValue = "") String title,
                             @ShellOption(defaultValue = "") Long id){
        books = bookService.getBookList(id, title);
        return bookService.printBooksInfo(books);
    }


    @ShellMethod(key = "get-by-id", value = "Shows book information")
    public String getBookById(
            @ShellOption("id") String id){
        return bookService.printBookInfo(Long.parseLong(id));
    }

    @ShellMethod(key = "get-by-title", value = "Shows book information")
    public String getBookByTitle(
            @ShellOption("title") String title){
        return bookService.printBookInfo(title);
    }

    @ShellMethod(key = "get-count", value = "Shows books count")
    public int getBookCount(){
        return bookService.getBookCount();
    }

    @ShellMethod(key = "create-book", value = "Create book")
    public String createBook(
            @ShellOption("title") String title,
            @ShellOption("author") String author,
            @ShellOption("genre") String genre){
        return bookService.printBookInfo(title);
    }

    @ShellMethod("Set title")
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String setTitle(@ShellOption(defaultValue = "") String title){
        Long bookId = books.get(0).getId();
        bookService.setTitle(bookId, title);
        books = bookService.getBookList(bookId, null);
        return bookService.printBooksInfo(books);
    }

    private Availability isBookSelected() {
        return books.size() != 1 ? Availability.unavailable("Выберите одну книгу"): Availability.available();
    }



}
