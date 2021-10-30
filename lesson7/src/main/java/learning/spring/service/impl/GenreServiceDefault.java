package learning.spring.service.impl;

import learning.spring.dao.GenreDao;
import learning.spring.domain.Genre;
import learning.spring.service.GenreService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceDefault implements GenreService {
    private final GenreDao genreDao;
    private Genre genre;

    public GenreServiceDefault(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
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

    @Override
    public boolean isGenreExists(String name){
        try {
           genreDao.getByName(new Genre(1L, name));
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public void createGenre(String name){
        try {
            genreDao.createGenre(new Genre(1L, name));
        }
            catch (Exception e){
            System.out.println("Ошибка создания жанра. Жанр с данным именем уже сущетвует.");
        }
    }

    @Override
    public Genre getGenre(String name){
        try {
            return genreDao.getByName(new Genre(1L, name));
        }
            catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Genre addGenre(String name){
        if(!isGenreExists(name)){
            createGenre(name);
        }
        return getGenre(name);
    }
}
