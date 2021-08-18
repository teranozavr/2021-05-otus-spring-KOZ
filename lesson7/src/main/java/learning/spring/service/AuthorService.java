package learning.spring.service;

import learning.spring.dao.AuthorDao;
import learning.spring.domain.Author;
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
}
