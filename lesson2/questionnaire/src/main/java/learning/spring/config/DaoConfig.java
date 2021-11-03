package learning.spring.config;

import learning.spring.dao.QuestionDao;
import learning.spring.dao.QuestionDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class DaoConfig {

    @Value( "${location}" )
    private String location;

    @Bean
    public QuestionDao questionDao(){
        return new QuestionDaoImpl(location);
    }
}
