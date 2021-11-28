package learning.spring.dao;

import learning.spring.config.QuestionsConfig;
import learning.spring.domain.Question;
import learning.spring.exceptions.QuestionReadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;

public class QuestionDaoCsvTests {
    private QuestionDaoCsv questionDao;
    private static final String LOCATION = "questions.csv";
    private static final String WRONG_LOCATION = "wrong.csv";

    @Mock
    private QuestionsConfig questionsConfig;

    @BeforeEach
    void init() {
        openMocks(this);
        given(questionsConfig.getFilePath()).willReturn(LOCATION);
    }

    @Nested
    class GetAllQuestionsTests {
        @Test
        void shouldReturnQuestionListWhenCallGetAllQuestionsWirhRightFilePath() {
            questionDao = new QuestionDaoCsv(questionsConfig);
            assertEquals(ArrayList.class, questionDao.getAllQuestions().getClass());
            assertEquals(Question.class, questionDao.getAllQuestions().get(0).getClass());
        }

        @Test
        void shouldReturnFiveQuestionsWhenCallGetAllQuestionsWithRightFilePath() {
            questionDao = new QuestionDaoCsv(questionsConfig);
            assertEquals(5, questionDao.getAllQuestions().size());
        }
        @Test
        void shouldThrowQuestionReadingExceptionWhenCallGetAllQuestionsWithWrongFilePath(){
            given(questionsConfig.getFilePath()).willReturn(WRONG_LOCATION);
            questionDao = new QuestionDaoCsv(questionsConfig);

            Throwable throwable = assertThrows(Throwable.class,
                    () -> questionDao.getAllQuestions());

            assertEquals(QuestionReadingException.class, throwable.getClass());
        }
    }
}
