package learning.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import learning.spring.service.QuestionService;
import learning.spring.domain.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext();
//        ctx.getResource("classpath*:spring-context.xml");
//  ApplicationContext context = ctx;
//                new ClassPathXmlApplicationContext("classpath*:spring-context.xml");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "/spring-context.xml");
//        ApplicationContext context =
//                new FileSystemXmlApplicationContext(
//                        "/WEB-INF/config/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        printQuestionarie(service.getAllQuestions());
    }

    private static void printQuestionarie(List<Question> questionList){
        for (Question q : questionList
        ) {
            System.out.println(q.getQuestionText());
            for (Answer a : q.getAnswers()
            ) {
                System.out.println("-"+a.getAnswerText());
            }
        }
    }
}
