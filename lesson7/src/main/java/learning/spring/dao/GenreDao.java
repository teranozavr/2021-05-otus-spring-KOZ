package learning.spring.dao;

import learning.spring.domain.Genre;

public interface GenreDao {
    int createGenre(Genre genre);

    Genre getById(long id);

    Genre getByName(Genre genre);
}
