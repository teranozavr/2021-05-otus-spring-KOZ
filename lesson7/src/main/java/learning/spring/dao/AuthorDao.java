package learning.spring.dao;

import learning.spring.domain.Author;
import learning.spring.domain.Book;

public interface AuthorDao {
    void insert(Author author);

    Author getById(long id);
}
