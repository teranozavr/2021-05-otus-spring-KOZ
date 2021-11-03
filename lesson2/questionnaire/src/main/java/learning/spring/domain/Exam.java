package learning.spring.domain;

import learning.spring.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Exam {

    private int currentQuestion = 0;
    private int rightAnswersCount = 0;
    private static List<Question> questionList;

    public Exam(@Autowired QuestionService questionService) {
        questionList = questionService.getAllQuestions();
    }

    public Question getNextQuestion(){
        if(currentQuestion<questionList.size()){
            int questionNumber = currentQuestion;
            currentQuestion++;
            return questionList.get(questionNumber);
        }
        return null;
    }

    public void increaseRightAnswersCount(){
        rightAnswersCount++;
    }

    public int getRightAnswersCount(){
        return rightAnswersCount;
    }

    public Boolean isFinalQuestion(){
        return currentQuestion+1==questionList.size();
    }
}
