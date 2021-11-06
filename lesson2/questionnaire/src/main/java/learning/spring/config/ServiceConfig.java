package learning.spring.config;

import learning.spring.dao.QuestionDao;
import learning.spring.domain.Exam;
import learning.spring.service.ExamService;
import learning.spring.service.ExamServiceImpl;
import learning.spring.service.QuestionService;
import learning.spring.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private final int rightCount;

    public ServiceConfig(@Value("#{new Integer(${right.count})}") int rightCount){
        this.rightCount = rightCount;
    }

    @Bean
    public QuestionService questionService(QuestionDao dao) {
        return new QuestionServiceImpl(dao);
    }

    @Bean
    public Exam getExam(QuestionService questionService){
        return new Exam(questionService.getAllQuestions());
    }

    @Bean
    public ExamService examService(Exam e){
        return new ExamServiceImpl(e, rightCount);
    }
}
