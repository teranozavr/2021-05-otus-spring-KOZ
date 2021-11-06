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
    public void questionDaoImplConstructorTest() throws FileNotFoundException, QuestionProcessingException {
        questionDao = new QuestionDaoCsv(location);
        Assertions.assertNotNull(questionDao.getAll());
        Assertions.assertEquals(5, questionDao.getAll().size());
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
