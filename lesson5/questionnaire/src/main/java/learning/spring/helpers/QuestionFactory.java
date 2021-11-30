package learning.spring.helpers;

import learning.spring.domain.Answer;
import learning.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionFactory {

    private static List<Answer> getAnswerList(String str){
        List<Answer> answerList = new ArrayList<>();
        boolean isAnswer = false;
        int answerNumber = 0;
        for (String s : str.split(";")) {
            if(isAnswer) answerList.add(new Answer(s.substring(1), isRightAnswer(s), answerNumber));
            isAnswer = true;
            answerNumber++;
        }
        return answerList;
    }

    private static boolean isRightAnswer(String str){
        return str.substring(0, 1).contains("+");
    }

    public static Question getQuestion(String str){
        return new Question(getQuestionText(str),getAnswerList(str));
    }

    private static String getQuestionText(String str){
        return str.split(";", 2)[0];
    }
}
