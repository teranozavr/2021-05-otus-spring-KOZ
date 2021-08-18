package learning.spring.dao;

import learning.spring.domain.Genre;

public interface GenreDao {
    Genre getById(long id);
    Genre getByName(String name);
}
