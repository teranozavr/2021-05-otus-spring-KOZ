package learning.spring.service.impl;

import learning.spring.dao.AuthorDao;
import learning.spring.domain.Author;
import learning.spring.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorServiceDefault implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceDefault(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public String printAuthorInfo(long id){
        Author author = authorDao.getById(id);
        StringBuilder sb = new StringBuilder()
                .append("Автор: ")
                .append(author.getFirstName())
                .append(" ")
                .append(author.getMiddleName())
                .append(" ")
                .append(author.getSurName())
                .append("\n");
        return sb.toString();
    }

    @Override
    public void createAuthor(String name, String surname, String middlename){
        try {
                Author author = new Author(1L, name, surname, middlename);
                authorDao.createAuthor(author);
        }
        catch (Exception e){
            log.error("Ошибка создания автора. Автор с данным ФИО уже сущетвует.");
        }
    }

    @Override
    public Author getAuthor(String name, String surname, String middlename){
        try{
            return authorDao.getByName(new Author(1L, name, surname, middlename));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public boolean isAuthorExists(String name, String surname, String middlename){
        return getAuthor(name, surname, middlename) != null;
    }

    @Override
    public Author addAuthor(String name, String surname, String middlename){
        if(!isAuthorExists(name, surname, middlename)){
            createAuthor(name, surname, middlename);
        }
        return getAuthor(name, surname, middlename);
    }
}
