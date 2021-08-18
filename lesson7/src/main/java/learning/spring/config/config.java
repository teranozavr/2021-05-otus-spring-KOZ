package learning.spring.config;

import learning.spring.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    BookDao getBookDao(){
        return new BookDaoJdbc();
    }

    @Bean
    GenreDao getGenreDao(){
        return new GenreDaoJdbc();
    }

    @Bean
    AuthorDao getAuthorDao(){
        return new AuthorDaoJdbc();
    }

}
