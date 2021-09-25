package learning.spring.dao;

import learning.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(long id);
    Genre getByName(String name);
    int createGenre(String name);
}
