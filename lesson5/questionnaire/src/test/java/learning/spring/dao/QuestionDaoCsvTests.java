package learning.spring.dao;

import learning.spring.commands.Commands;
import learning.spring.config.QuestionsConfig;
import learning.spring.domain.Question;
import learning.spring.exceptions.QuestionReadingException;
import learning.spring.service.Impl.ExamServiceImpl;
import learning.spring.service.Impl.QuestionServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class QuestionDaoCsvTests {

    @Autowired
    private QuestionDaoCsv questionDao;

    @Autowired
    private QuestionDaoCsv questionDaoWrongLocation;

    @MockBean
    private QuestionsConfig questionsConfig;

    @MockBean
    private Commands commands;

    @MockBean
    private ExamServiceImpl examService;

    @MockBean
    private QuestionServiceImpl questionService;

    @MockBean
    private Shell shell;

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

            Throwable throwable = assertThrows(Throwable.class,
                    () -> questionDaoWrongLocation.getAllQuestions());

            assertEquals(QuestionReadingException.class, throwable.getClass());
        }
    }
}
