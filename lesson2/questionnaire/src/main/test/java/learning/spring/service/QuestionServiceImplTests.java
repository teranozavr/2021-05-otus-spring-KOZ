package learning.spring.service;

import learning.spring.dao.QuestionDaoCsv;
import learning.spring.domain.Answer;
import learning.spring.domain.Question;
import learning.spring.service.Impl.QuestionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class QuestionServiceImplTests {
    private QuestionServiceImpl questionService;

    @Mock
    QuestionDaoCsv questionDao;

    @BeforeEach
    public void init() throws Exception {
        initMocks(this);
        when(questionDao.getAllQuestions()).thenReturn(getAllQuestions());
        questionService = new QuestionServiceImpl(questionDao);
    }

    @Test
    public void getAllQuestionsTest() throws Exception {
        List<Question> questionList = questionService.getAllQuestions();
        Assertions.assertNotNull(questionList);
        Assertions.assertEquals(2, questionList.size());
        Assertions.assertEquals(2, questionList.get(0).getAnswers().size());
    }

    private List<Question> getAllQuestions(){
        Answer answer1 = new Answer("Text", true, 1);
        Answer answer2 = new Answer("Text2", false, 2);
        Question question1 = new Question("Question text", List.of(answer1, answer2));
        Question question2 = new Question("Question text2", List.of(answer1, answer2));
        return List.of(question1, question2);
    }
}
