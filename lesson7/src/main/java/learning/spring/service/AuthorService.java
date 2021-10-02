package learning.spring.service;

import learning.spring.dao.AuthorDao;
import learning.spring.domain.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorDao authorDao;
    private Author author;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;

    }

    public String printAuthorInfo(long id){
        author = authorDao.getById(id);
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

    public void createAuthor(String name, String surname, String middlename){
        try {
            authorDao.createAuthor(name, surname, middlename);
        }
        catch (Exception e){
            System.out.println("Ошибка создания автора. Автор с данным ФИО уже сущетвует.");
        }
    }

    public Long getAuthorId(String name, String surname, String middlename){
        try{
            Author author = authorDao.getByName(name, surname, middlename);
            return author.getId();
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public boolean isAuthorExists(String name, String surname, String middlename){
        try {
            authorDao.getByName(name, surname, middlename);
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    public Long addAuthorId(String name, String surname, String middlename){
        if(!isAuthorExists(name, surname, middlename)){
            createAuthor(name, surname, middlename);
        }
        return getAuthorId(name, surname, middlename);
    }
}
