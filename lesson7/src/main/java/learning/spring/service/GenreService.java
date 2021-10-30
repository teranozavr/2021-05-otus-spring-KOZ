package learning.spring.service;

import learning.spring.domain.Genre;

public interface GenreService {
    String printGenreInfo(long id);

    boolean isGenreExists(String name);

    void createGenre(String name);

    Genre getGenre(String name);

    Genre addGenre(String name);
}
