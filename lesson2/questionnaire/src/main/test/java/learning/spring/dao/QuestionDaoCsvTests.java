package learning.spring.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionDaoCsvTests {
    private QuestionDaoCsv questionDao;
    private static final String location = "questions.csv";
    private static final String wrongLocation = "wrong.csv";

    @Test
    public void questionDaoImplConstructorTest(){
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
