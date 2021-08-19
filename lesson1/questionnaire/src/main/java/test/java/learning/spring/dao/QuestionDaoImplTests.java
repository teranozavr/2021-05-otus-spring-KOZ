package learning.spring.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionDaoImplTests {
    private QuestionDaoImpl questionDao;
    private static final String location = "questions.csv";
    private static final String wrongLocation = "wrong.csv";

    @Test
    public void QuestionDaoImplConstructorTest(){
        questionDao = new QuestionDaoImpl(location);
        Assertions.assertEquals(questionDao.getLocation(), location);
    }

    @Test
    public void NullTest(){
        try {
            questionDao = new QuestionDaoImpl(wrongLocation);
        }
        catch (Exception ex){
            Assertions.assertEquals(ex.getClass(), NullPointerException.class);
        }
    }
}
