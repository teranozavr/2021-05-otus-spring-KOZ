package learning.spring.dao;

import learning.spring.domain.Author;

public interface AuthorDao {
    int createAuthor(Author author);

    Author getById(Long id);

    Author getByName(Author author);
}
