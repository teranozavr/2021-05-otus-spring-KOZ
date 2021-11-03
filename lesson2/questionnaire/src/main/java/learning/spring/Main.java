package learning.spring;

import learning.spring.service.ExamService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import learning.spring.service.QuestionService;

import java.io.IOException;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

//        QuestionService service = context.getBean(QuestionService.class);
//
//        service.printAllQuestions();

        ExamService examService = context.getBean(ExamService.class);
        examService.startExam();
        examService.printExamResult();
    }
}
