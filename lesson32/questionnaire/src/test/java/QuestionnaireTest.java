import learning.spring.Questionnaire;
import learning.spring.config.LocaleConfig;
import learning.spring.config.QuestionsConfig;
import learning.spring.dao.QuestionDaoCsv;
import learning.spring.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest(classes = Questionnaire.class)
public class QuestionnaireTest {

    @Autowired
    MessageSource messageSource;

    @Autowired
    MessageService messageService;

    @Autowired
    QuestionsConfig questionsConfig;

    @MockBean
    QuestionDaoCsv questionDaoCsv;

    @MockBean
    LocaleConfig localeConfig;

    @BeforeEach
    void init() {
        openMocks(this);
    }

    @Test
    void shouldGetMessageReurnQuestionsRuRuWhenLocaleIsRu() {
        assertEquals("Введите ваше имя", messageService.getMessage("insertName"));
    }

    @Test
    void shouldGetMessageReurnQuestionsWhenLocaleIsEmpty() {
        given(localeConfig.getLocale()).willReturn(Locale.ROOT);
        ReflectionTestUtils.setField(messageService, "localeConfig", localeConfig);

        assertEquals("Insert your name", messageService.getMessage("insertName"));
    }
}
