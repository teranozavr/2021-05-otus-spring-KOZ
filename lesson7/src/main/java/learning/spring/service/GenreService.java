package learning.spring.service;

import learning.spring.dao.GenreDao;
import learning.spring.domain.Genre;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public boolean isGenreExists(String name){
        try {
           genreDao.getByName(name);
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    public void createGenre(String name){
        genreDao.createGenre(name);
    }

    public Long getGenreId(String name){
        Genre genre = genreDao.getByName(name);
        return genre.getId();
    }

    public Long addGenreId(String name){
        if(!isGenreExists(name)){
            createGenre(name);
        }
        return getGenreId(name);
    }
}
