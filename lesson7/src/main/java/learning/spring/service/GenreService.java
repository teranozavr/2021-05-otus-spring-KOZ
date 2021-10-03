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
        try {
            genre = genreDao.getById(id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
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
        try {
            genreDao.createGenre(name);
        }
            catch (Exception e){
            System.out.println("Ошибка создания жанра. Жанр с данным именем уже сущетвует.");
        }
    }

    public Long getGenreId(String name){
        try {
            Genre genre = genreDao.getByName(name);
            return genre.getId();
        }
            catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Long addGenreId(String name){
        if(!isGenreExists(name)){
            createGenre(name);
        }
        return getGenreId(name);
    }
}
