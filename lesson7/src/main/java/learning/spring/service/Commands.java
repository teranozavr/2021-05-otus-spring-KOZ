package learning.spring.service;

import learning.spring.domain.Book;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class Commands {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private List<Book> books;

    private static final String NULL = null;

    public Commands(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    //select-book --title "Евгений Онегин"
    //select-book --title "Евгений Онегин" --id 1
    @ShellMethod("Select book")
    public String selectBook(@ShellOption(defaultValue = "") String title,
                             @ShellOption(defaultValue = "") Long id){
        books = bookService.getBookList(id, title);
        return printSelectedBooksInfo(books);
    }

    @ShellMethod(key = "get-count", value = "Shows books count")
    public int getBookCount(){
        return bookService.getBookCount();
    }

    //create-book "Название" Фамильяр Имен Отчествович Рассказ
    @ShellMethod(key = "create-book", value = "Create book")
    public String createBook(
            @ShellOption({"title", "t"}) String title,
            @ShellOption({"surname", "s"}) String surname,
            @ShellOption({"name", "n"}) String name,
            @ShellOption({"middlename", "m"}) String middleName,
            @ShellOption({"genre", "g"}) String genre){
        Long authorId = authorService.addAuthorId(name, surname, middleName);
        Long genreId = genreService.addGenreId(genre);
        bookService.createBook(title, authorId, genreId);
        return bookService.printBookInfo(title);
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

    @ShellMethod("Delete book")
    @ShellMethodAvailability(value = "isBookSelected")
    public String deleteBook(){
        books.clear();
      return bookService.deleteBook(books.get(0));
    }

    private Availability isBookSelected() {
        if(books==null || books.isEmpty()) return  Availability.unavailable("Выберите одну книгу");
        return books.size() != 1 ? Availability.unavailable("Выберите одну книгу"): Availability.available();
    }

    private String printSelectedBooksInfo(List<Book> books){
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
        }
        for (Book book : books){
            stringBuilder.append(bookService.printBookInfo(book.getId()));
        }
        return stringBuilder.toString();
    }

}
