package learning.spring.dao;

import learning.spring.domain.Author;

public interface AuthorDao {
    int createAuthor(String name, String surname, String middlename);

    Author getById(long id);

    Author getByName(String name, String surname, String middlename);
}
