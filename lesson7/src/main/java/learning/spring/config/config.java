package learning.spring.config;

import learning.spring.dao.BookDao;
import learning.spring.dao.BookDaoJdbc;
import liquibase.pro.packaged.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class config {

//    @Bean
//    NamedParameterJdbcTemplate getJdbcTemplate(){
//        return new NamedParameterJdbcTemplate();
//    }



    @Bean
    BookDao getBookDao(){
        return new BookDaoJdbc();
    }
}
