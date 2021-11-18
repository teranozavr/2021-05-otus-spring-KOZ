package learning.spring.dao;

import learning.spring.domain.Question;
import learning.spring.exceptions.QuestionReadingException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionDaoCsvTests {
    private QuestionDaoCsv questionDao;
    private static final String location = "questions.csv";
    private static final String wrongLocation = "wrong.csv";

    @Nested
    class GetAllQuestionsTests {
        @Test
        void shouldReturnQuestionListWhenCallGetAllQuestionsWirhRightFilePath() {
            questionDao = new QuestionDaoCsv(location);
            assertEquals(ArrayList.class, questionDao.getAllQuestions().getClass());
            assertEquals(Question.class, questionDao.getAllQuestions().get(0).getClass());
        }

        @Test
        void shouldReturnFiveQuestionsWhenCallGetAllQuestionsWithRightFilePath() {
            questionDao = new QuestionDaoCsv(location);
            assertEquals(5, questionDao.getAllQuestions().size());
        }
        @Test
        void shouldThrowQuestionReadingExceptionWhenCallGetAllQuestionsWithWrongFilePath(){
            questionDao = new QuestionDaoCsv(wrongLocation);

            Throwable throwable = assertThrows(Throwable.class,
                    () -> questionDao.getAllQuestions());

            assertEquals(QuestionReadingException.class, throwable.getClass());
        }
    }
}
