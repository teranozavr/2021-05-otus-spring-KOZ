package learning.spring.dao;

import java.util.ArrayList;
import java.util.List;

import learning.spring.config.QuestionsConfig;
import learning.spring.domain.Question;
import learning.spring.exceptions.QuestionReadingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static learning.spring.helpers.QuestionFactory.getQuestion;
import static learning.spring.helpers.StringProcessor.getStringList;
import static learning.spring.helpers.ResourceFileReader.*;

@Slf4j
@Repository
public class QuestionDaoCsv implements QuestionDao {
    private final String location;

    public QuestionDaoCsv(QuestionsConfig questionsConfig) {
        this.location = questionsConfig.getFilePath();
    }

    public List<Question> getAllQuestions() throws QuestionReadingException {
        List<Question> questionList = new ArrayList<>();
        List<String> stringList;
        try{
            String resourceString = getResourceFileAsString(location);
            stringList = getStringList(resourceString);
            for (String s: stringList) {
                questionList.add(getQuestion(s));
            }
            return questionList;
        }
        catch (Exception e) {
            throw new QuestionReadingException(e);
        }
    }
}
