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
            questionDao = new QuestionDaoCsv();
            assertEquals(ArrayList.class, questionDao.getAllQuestions(location).getClass());
            assertEquals(Question.class, questionDao.getAllQuestions(location).get(0).getClass());
        }

        @Test
        void shouldReturnFiveQuestionsWhenCallGetAllQuestionsWithRightFilePath() {
            questionDao = new QuestionDaoCsv();
            assertEquals(5, questionDao.getAllQuestions(location).size());
        }
        @Test
        void shouldThrowQuestionReadingExceptionWhenCallGetAllQuestionsWithWrongFilePath(){
            questionDao = new QuestionDaoCsv();

            Throwable throwable = assertThrows(Throwable.class,
                    () -> questionDao.getAllQuestions(wrongLocation));

            assertEquals(QuestionReadingException.class, throwable.getClass());
        }
    }
}
