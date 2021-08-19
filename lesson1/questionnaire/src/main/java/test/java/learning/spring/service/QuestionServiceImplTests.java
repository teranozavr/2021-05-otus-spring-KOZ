package learning.spring.service;

import learning.spring.dao.QuestionDaoImpl;
import learning.spring.domain.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QuestionServiceImplTests {
    private static final String LOCATION = "questions.csv";
    private QuestionServiceImpl questionService;
    private QuestionDaoImpl questionDao;

    @BeforeEach
    public void init(){
        questionDao = new QuestionDaoImpl(LOCATION);
        questionService = new QuestionServiceImpl(questionDao);
    }

    @Test
    public void getAllQuestionsTest(){
        List<Question> questionList = questionService.getAllQuestions();
        Assertions.assertNotNull(questionList);
        Assertions.assertEquals(5, questionList.size());
        Assertions.assertEquals(4, questionList.get(0).getAnswers().size());
    }
}
