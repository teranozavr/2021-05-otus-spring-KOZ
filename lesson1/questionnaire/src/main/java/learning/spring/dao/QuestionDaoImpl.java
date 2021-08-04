package learning.spring.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import learning.spring.domain.Question;
import lombok.extern.slf4j.Slf4j;

import static learning.spring.helpers.StringProcessor.getQuestion;
import static learning.spring.helpers.StringProcessor.getStringList;
import static learning.spring.helpers.ResourceFileReader.*;

@Slf4j
public class QuestionDaoImpl implements QuestionDao {
    private List<learning.spring.domain.Question> questionList = new ArrayList<>();
    private final String LOCATION;

    public QuestionDaoImpl(String location){
        this.LOCATION = location;
        fillQuestionList();
    }

    public List<Question> getData(){
        return questionList;
    }

    private void fillQuestionList(){
        List<String> stringList;
        try{
            String resourceString = getResourceFileAsString(LOCATION);
            stringList = getStringList(resourceString);
            for (String s: stringList) {
                questionList.add(getQuestion(s));
            }
        }
        catch (IOException e){
            log.error(e.getMessage());
        }
    }

    public String getLocation(){
        return this.LOCATION;
    }
}
