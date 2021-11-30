package learning.spring.dao;

import learning.spring.commands.Commands;
import learning.spring.config.QuestionsConfig;
import learning.spring.domain.Question;
import learning.spring.exceptions.QuestionReadingException;
import learning.spring.service.Impl.ExamServiceImpl;
import learning.spring.service.Impl.QuestionServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class QuestionDaoCsvTests {

    private static final String LOCATION = "questions.csv";
    private static final String WRONG_LOCATION = "wrong.csv";

    @Autowired
    private QuestionDaoCsv questionDao;

    @Autowired
    private QuestionsConfig questionsConfig;

    @MockBean
    private Commands commands;

    @MockBean
    private ExamServiceImpl examService;

    @MockBean
    private QuestionServiceImpl questionService;

    @MockBean
    private Shell shell;

    @BeforeEach
    void init() {
        openMocks(this);
    }

    @AfterEach
    void reset(){
        ReflectionTestUtils.setField(questionDao, "location", LOCATION);
    }

    @Nested
    class GetAllQuestionsTests {
        @Test
        void shouldReturnQuestionListWhenCallGetAllQuestionsWirhRightFilePath() {
            assertEquals(ArrayList.class, questionDao.getAllQuestions().getClass());
            assertEquals(Question.class, questionDao.getAllQuestions().get(0).getClass());
        }

        @Test
        void shouldReturnFiveQuestionsWhenCallGetAllQuestionsWithRightFilePath() {
            assertEquals(5, questionDao.getAllQuestions().size());
        }

        @Test
        void shouldThrowQuestionReadingExceptionWhenCallGetAllQuestionsWithWrongFilePath(){
            ReflectionTestUtils.setField(questionDao, "location", WRONG_LOCATION);

            Throwable throwable = assertThrows(Throwable.class,
                    () -> questionDao.getAllQuestions());

            assertEquals(QuestionReadingException.class, throwable.getClass());
        }
    }
}
