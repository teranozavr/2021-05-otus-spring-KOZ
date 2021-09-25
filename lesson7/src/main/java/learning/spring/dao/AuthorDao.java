package learning.spring.dao;

import learning.spring.domain.Author;

public interface AuthorDao {
    void insert(Author author);

    Author getById(long id);

    int createAuthor(String name, String surname, String middlename);

    Author getByName(String name, String surname, String middlename);
}
