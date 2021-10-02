package learning.spring.config;

import learning.spring.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class config {
    @Primary
    @Bean
    BookDao getBookDao(){
        return new BookDaoJdbc();
    }

    @Primary
    @Bean
    GenreDao getGenreDao(){
        return new GenreDaoJdbc();
    }

    @Primary
    @Bean
    AuthorDao getAuthorDao(){
        return new AuthorDaoJdbc();
    }

}
