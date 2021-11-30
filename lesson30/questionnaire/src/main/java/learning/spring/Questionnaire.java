package learning.spring;

import learning.spring.service.ExamService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Questionnaire {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Questionnaire.class, args);
        ExamService examService = context.getBean(ExamService.class);
        examService.startExam();
    }

}
