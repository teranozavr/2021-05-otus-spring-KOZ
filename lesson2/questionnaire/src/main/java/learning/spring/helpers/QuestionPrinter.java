package learning.spring.helpers;

import learning.spring.domain.Answer;
import learning.spring.domain.Question;

import java.util.List;

public class QuestionPrinter {
    public static void printQuestionarie(List<Question> questionList){
        for (Question q : questionList
        ) {
            System.out.println(q.getQuestionText());
            for (Answer a : q.getAnswers()
            ) {
                System.out.println("-"+a.getAnswerText());
            }
        }
    }

    public static void printQuestion(Question question){
        System.out.println(question.getQuestionText());
        for (Answer a : question.getAnswers()
        ) {
            System.out.println("-"+a.getAnswerText());
        }
    }
}
