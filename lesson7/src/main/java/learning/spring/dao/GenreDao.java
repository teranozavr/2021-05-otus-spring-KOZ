package learning.spring.dao;

import learning.spring.domain.Genre;

public interface GenreDao {
    int createGenre(String name);

    Genre getById(long id);

    Genre getByName(String name);
}
