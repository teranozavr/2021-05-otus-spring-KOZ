package learning.spring.dao;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import learning.spring.domain.Question;
import lombok.extern.slf4j.Slf4j;

import static learning.spring.helpers.QuestionFactory.getQuestion;
import static learning.spring.helpers.StringProcessor.getStringList;
import static learning.spring.helpers.ResourceFileReader.*;

@Slf4j
public class QuestionDaoCsv implements QuestionDao {
    private final List<Question> questionList;
    private final String location;

    public QuestionDaoCsv(String location){
        this.location = location;
        questionList = new ArrayList<>();
        try {
            fillQuestionList();
        }
        catch (FileNotFoundException e){
            log.error("File not found {}", e.getMessage());
        }
    }

    public List<Question> getAll(){
        return questionList;
    }

    private void fillQuestionList() throws FileNotFoundException {
        List<String> stringList;
        try{
            String resourceString = getResourceFileAsString(location);
            stringList = getStringList(resourceString);
            for (String s: stringList) {
                questionList.add(getQuestion(s));
            }
        }
        catch (Exception e) {
            throw new FileNotFoundException(location);
        }
    }
}
