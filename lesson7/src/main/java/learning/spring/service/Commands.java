package learning.spring.service;

import learning.spring.domain.Book;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Commands {

    private final BookService bookService;


    public Commands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(key = "get-by-id", value = "Shows book information")
    public String getBookById(
            @ShellOption("id") String id){
        return bookService.printBookInfo(Long.parseLong(id));
    }

    @ShellMethod(key = "get-count", value = "Shows books count")
    public int getBookCount(){
        return bookService.getBookCount();
    }


}
