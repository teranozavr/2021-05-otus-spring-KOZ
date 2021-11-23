package learning.spring.dao;

import learning.spring.domain.Question;
import learning.spring.exceptions.QuestionReadingException;
import learning.spring.service.LocationService;
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
    private LocationService locationService;

    @BeforeEach
    void init() {
        openMocks(this);
        given(locationService.getLocation()).willReturn(LOCATION);
    }

    @Nested
    class GetAllQuestionsTests {
        @Test
        void shouldReturnQuestionListWhenCallGetAllQuestionsWirhRightFilePath() {
            questionDao = new QuestionDaoCsv(locationService);
            assertEquals(ArrayList.class, questionDao.getAllQuestions().getClass());
            assertEquals(Question.class, questionDao.getAllQuestions().get(0).getClass());
        }

        @Test
        void shouldReturnFiveQuestionsWhenCallGetAllQuestionsWithRightFilePath() {
            questionDao = new QuestionDaoCsv(locationService);
            assertEquals(5, questionDao.getAllQuestions().size());
        }
        @Test
        void shouldThrowQuestionReadingExceptionWhenCallGetAllQuestionsWithWrongFilePath(){
            given(locationService.getLocation()).willReturn(WRONG_LOCATION);
            questionDao = new QuestionDaoCsv(locationService);

            Throwable throwable = assertThrows(Throwable.class,
                    () -> questionDao.getAllQuestions());

            assertEquals(QuestionReadingException.class, throwable.getClass());
        }
    }
}
