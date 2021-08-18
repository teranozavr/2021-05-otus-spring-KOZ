package learning.spring.service;

import learning.spring.dao.GenreDao;
import learning.spring.domain.Genre;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private final GenreDao genreDao;
    private Genre genre;

    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public String printGenreInfo(long id){
        genre = genreDao.getById(id);
        StringBuilder sb = new StringBuilder()
                .append("Жанр: ")
                .append(genre.getGenreName())
                .append("\n");
        return sb.toString();
    }
}
