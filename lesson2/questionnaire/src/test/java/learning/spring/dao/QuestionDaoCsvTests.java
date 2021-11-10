package learning.spring.dao;

import learning.spring.exceptions.QuestionProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class QuestionDaoCsvTests {
    private QuestionDaoCsv questionDao;
    private static final String location = "questions.csv";
    private static final String wrongLocation = "wrong.csv";

    @Test
    public void questionDaoImplConstructorTest() throws Exception {
        questionDao = new QuestionDaoCsv(location);
        Assertions.assertNotNull(questionDao.getAllQuestions());
        Assertions.assertEquals(5, questionDao.getAllQuestions().size());
    }

    @Test
    public void nullTest(){
        try {
            questionDao = new QuestionDaoCsv(wrongLocation);
        }
        catch (Exception ex){
            Assertions.assertEquals(ex.getClass(), NullPointerException.class);
        }
    }
}
