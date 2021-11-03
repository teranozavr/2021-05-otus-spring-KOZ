package learning.spring.config;

import learning.spring.dao.QuestionDao;
import learning.spring.domain.Exam;
import learning.spring.service.ExamService;
import learning.spring.service.ExamServiceImpl;
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

    @Bean
    public Exam getExam(QuestionService questionService){
        return new Exam(questionService);
    }

    @Bean
    public ExamService examService(Exam e){
        return new ExamServiceImpl(e);
    }
}
