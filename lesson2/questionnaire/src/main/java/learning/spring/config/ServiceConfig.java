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

    private final PrintStream out;

    private final InputStream in;


    public ServiceConfig(@Value("#{T(java.lang.System).out}")
                                 PrintStream out,
                         @Value("#{T(java.lang.System).in}")
                                 InputStream in){
        this.out = out;
        this.in = in;
    }

    @Bean
    public QuestionService questionService(QuestionDao dao) {
        return new QuestionServiceImpl(dao);
    }

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(out, in);
    }

    @Bean
    public ExceptionPrinterService exceptionPrinterService(IOService ioService) {
        return new ExceptionPrinterServiceConsole(ioService);
    }

    @Bean
    public QuestionPrinterService questionPrinterService(IOService ioService) {
        return new QuestionPrinterServiceConsole(ioService);
    }

    @Bean
    public ExamService examService(QuestionService questionService, IOService ioService, ExceptionPrinterService exceptionPrinterService, QuestionPrinterService questionPrinterService) throws Exception {
        return new ExamServiceImpl(questionService, ioService, exceptionPrinterService, questionPrinterService);
    }
}
