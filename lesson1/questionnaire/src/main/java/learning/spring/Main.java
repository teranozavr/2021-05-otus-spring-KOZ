package learning.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import learning.spring.service.QuestionService;
import java.io.IOException;

import static learning.spring.helpers.QuestionPrinter.printQuestionarie;


public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        service.printAllQuestions();
    }
}
