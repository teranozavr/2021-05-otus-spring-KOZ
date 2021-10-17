package learning.spring.service;

public interface AuthorService {
    String printAuthorInfo(long id);

    void createAuthor(String name, String surname, String middlename);

    Long getAuthorId(String name, String surname, String middlename);

    boolean isAuthorExists(String name, String surname, String middlename);

    Long addAuthorId(String name, String surname, String middlename);
}
