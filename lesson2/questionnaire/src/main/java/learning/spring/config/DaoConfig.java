package learning.spring.config;

import learning.spring.dao.QuestionDao;
import learning.spring.dao.QuestionDaoCsv;
import learning.spring.exceptions.QuestionProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileNotFoundException;

@Configuration
@PropertySource("classpath:application.properties")
public class DaoConfig {

    private final String location;

    public DaoConfig(@Value("${questions.location}") String location) {
        this.location = location;
    }

    @Bean
    public QuestionDao questionDao() throws FileNotFoundException, QuestionProcessingException {
        return new QuestionDaoCsv(location);
    }
}
