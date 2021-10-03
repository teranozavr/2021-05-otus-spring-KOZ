package learning.spring.helpers;

import learning.spring.domain.Answer;
import learning.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class StringProcessor {

    public static List<String> getStringList(String str){
        List<String> stringList = new ArrayList<>();
        for (String s : str.split("\n")) {
            stringList.add(s);
        }
        return stringList;
    }

    private static List<Answer> getAnswerList(String str){
        List<Answer> answerList = new ArrayList<>();
        boolean isAnswer = false;
        for (String s : str.split(";")) {
            if(isAnswer) answerList.add(new Answer(s));
            isAnswer = true;
        }
        return answerList;
    }

    public static Question getQuestion(String str){
        return new Question(getQuestionText(str),getAnswerList(str));
    }

    private static String getQuestionText(String str){
        return str.split(";", 2)[0];
    }

}
