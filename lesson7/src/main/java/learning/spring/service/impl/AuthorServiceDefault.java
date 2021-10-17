package learning.spring.service.impl;

import learning.spring.dao.AuthorDao;
import learning.spring.domain.Author;
import learning.spring.service.AuthorService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
            authorDao.createAuthor(getAuthor(name, surname, middlename));
        }
        catch (Exception e){
            System.out.println("Ошибка создания автора. Автор с данным ФИО уже сущетвует.");
        }
    }

    @Override
    public Long getAuthorId(String name, String surname, String middlename){
        try{
            Author author = authorDao.getByName(getAuthor(name, surname, middlename));
            return author.getId();
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public boolean isAuthorExists(String name, String surname, String middlename){
        try {
            authorDao.getByName(getAuthor(name, surname, middlename));
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public Long addAuthorId(String name, String surname, String middlename){
        if(!isAuthorExists(name, surname, middlename)){
            createAuthor(name, surname, middlename);
        }
        return getAuthorId(name, surname, middlename);
    }

    private Author getAuthor(String name, String surname, String middlenamme){
        return new Author(1L, name, surname, middlenamme);
    }
}
