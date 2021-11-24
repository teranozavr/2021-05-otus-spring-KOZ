import learning.spring.Questionnaire;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Questionnaire.class)
public class QuestionnaireTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void shouldGetMessageReurnQuestionsRuRuWhenLocaleIsRu() {
        assertEquals("data/questions_ru_RU.csv", messageSource.getMessage("location", null, Locale.forLanguageTag("ru-RU")));
    }

    @Test
    void shouldGetMessageReurnQuestionsWhenLocaleIsEn() {
        assertEquals("data/questions.csv", messageSource.getMessage("location", null, Locale.ROOT));
    }
}
