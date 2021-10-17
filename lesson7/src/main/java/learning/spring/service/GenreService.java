package learning.spring.service;

public interface GenreService {
    String printGenreInfo(long id);

    boolean isGenreExists(String name);

    void createGenre(String name);

    Long getGenreId(String name);

    Long addGenreId(String name);
}
