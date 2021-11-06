package learning.spring.config;

import learning.spring.dao.QuestionDao;
import learning.spring.domain.Exam;
import learning.spring.domain.ExamResult;
import learning.spring.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
public class ServiceConfig {

    private final int rightAnswersLimit;

    private final PrintStream out;

    private final InputStream in;


    public ServiceConfig(@Value("#{new Integer(${right.count})}") int rightAnswersLimit,
                         @Value("#{T(java.lang.System).out}")
                                 PrintStream out,
                         @Value("#{T(java.lang.System).in}")
                                 InputStream in){
        this.rightAnswersLimit = rightAnswersLimit;
        this.out = out;
        this.in = in;
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
    public ExamResult getExamResult() {
        return new ExamResult(rightAnswersLimit);
    }

    @Bean
    public IOService getIOService() {
        return new IOServiceConsole(out, in);
    }

    @Bean
    public ExamService examService(Exam e, ExamResult er, IOServiceConsole ioService){
        return new ExamServiceImpl(e, er, ioService);
    }
}
