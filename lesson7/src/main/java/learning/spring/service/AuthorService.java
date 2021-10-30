package learning.spring.service;

import learning.spring.domain.Author;

public interface AuthorService {
    String printAuthorInfo(long id);

    void createAuthor(String name, String surname, String middlename);

    Author getAuthor(String name, String surname, String middlename);

    boolean isAuthorExists(String name, String surname, String middlename);

    Author addAuthor(String name, String surname, String middlename);
}
