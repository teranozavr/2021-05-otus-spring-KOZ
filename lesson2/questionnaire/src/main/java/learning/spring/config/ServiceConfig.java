package learning.spring.config;

import learning.spring.dao.QuestionDao;
import learning.spring.service.QuestionService;
import learning.spring.service.QuestionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public QuestionService questionService(QuestionDao dao) {
        return new QuestionServiceImpl(dao);
    }
}
