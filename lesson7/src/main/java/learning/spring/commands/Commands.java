package learning.spring.commands;

import learning.spring.domain.Book;
import learning.spring.service.impl.AuthorServiceDefault;
import learning.spring.service.impl.BookServiceDefault;
import learning.spring.service.impl.GenreServiceDefault;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class Commands {

    private final BookServiceDefault bookService;

    private final AuthorServiceDefault authorService;

    private final GenreServiceDefault genreService;

    private List<Book> books;

    public Commands(BookServiceDefault bookService, AuthorServiceDefault authorService, GenreServiceDefault genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    //select-book --id 1
    //select-book --title "Евгений Онегин"
    //select-book --title "Евгений Онегин" --id 1
    //select-book --title "Мед"
    //select-book
    @ShellMethod("Select book")
    public String selectBook(@ShellOption(defaultValue = "") String title,
                             @ShellOption(defaultValue = "") Long id){
        books = bookService.getBookList(id, title);
        return bookService.printSelectedBooksInfo(books);
    }
    //get-count
    @ShellMethod("Shows books count")
    public int getCount(){
        return bookService.getBookCount();
    }

    //create-book "Название" Фамильяр Имен Отчествович Рассказ
    @ShellMethod("Create book")
    public String createBook(
            @ShellOption({"title", "t"}) String title,
            @ShellOption({"surname", "s"}) String surname,
            @ShellOption({"name", "n"}) String name,
            @ShellOption({"middlename", "m"}) String middleName,
            @ShellOption({"genre", "g"}) String genre){
        Long authorId = authorService.addAuthorId(name, surname, middleName);
        Long genreId = genreService.addGenreId(genre);
        int createStatus = bookService.createBook(title, authorId, genreId);
        switch (createStatus) {
            case 1: return bookService.printBookInfo(title, authorId, genreId);
            case -1: return "Книга с данными параметрами уже сущестует!";
            case -2: return "При создании книги произошла ошибка!";
        }
        return null;
    }

    //select-book --id 4
    //set-title Название2
    @ShellMethod("Set title")
    @ShellMethodAvailability(value = "isBookSelected")
    public String setTitle(@ShellOption(defaultValue = "") String title){
        Long bookId = books.get(0).getId();
        bookService.setTitle(bookId, title);
        books = bookService.getBookList(bookId, null);
        return bookService.printBooksInfo(books);
    }

    //delete-book
    @ShellMethod("Delete book")
    @ShellMethodAvailability(value = "isBookSelected")
    public String deleteBook(){
        String result = bookService.deleteBook(books.get(0));
        books.clear();
      return result;
    }

    private Availability isBookSelected() {
        if(books==null || books.isEmpty()) return  Availability.unavailable("Выберите одну книгу");
        return books.size() != 1 ? Availability.unavailable("Выберите одну книгу"): Availability.available();
    }
}
