package learning.spring.dao;

import learning.spring.domain.Genre;

public interface GenreDao {
    Genre getByName(String name);
}
