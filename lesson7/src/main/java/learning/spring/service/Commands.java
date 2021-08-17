package learning.spring.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Commands {

    private final BookService bookService;


    public Commands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(key = "Show book by ID", value = "Shows book information")
    public String getBookById(
            @ShellOption("id") String id){
        return bookService.getBookById(id);
    }

    @ShellMethod(key = "get-count", value = "Shows books count")
    public int getBookCount(){
        return bookService.getBookCount();
    }
}
