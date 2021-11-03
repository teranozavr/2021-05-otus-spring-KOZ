package learning.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private final String questionText;
    private final List<Answer> answers;

    public Question(String questionText, List<Answer> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }

    public String getQuestionText(){
        return questionText;
    }

    public List<Answer> getAnswers(){
        return answers;
    }

    public Integer getRightAnswerNumber() {
        for (Answer a: answers
             ) {
            if(a.isRight()) return a.getAnswerNumber();
        }
        return null;
    }

}
