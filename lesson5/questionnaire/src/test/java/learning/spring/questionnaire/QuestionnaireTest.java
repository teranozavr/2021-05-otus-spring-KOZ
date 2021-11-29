package learning.spring.questionnaire;

import learning.spring.commands.Commands;
import learning.spring.config.LocaleConfig;
import learning.spring.config.QuestionsConfig;
import learning.spring.dao.QuestionDaoCsv;
import learning.spring.service.Impl.ExamServiceImpl;
import learning.spring.service.Impl.QuestionServiceImpl;
import learning.spring.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.shell.Shell;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class QuestionnaireTest {

    @Autowired
    private QuestionDaoCsv questionDao;

    @Autowired
    MessageSource messageSource;

    @Autowired
    MessageService messageService;

    @MockBean
    QuestionsConfig questionsConfig;

    @MockBean
    LocaleConfig localeConfig;

    @MockBean
    Commands commands;

    @MockBean
    ExamServiceImpl examService;

    @MockBean
    QuestionServiceImpl questionService;

    @MockBean
    Shell shell;

    @Test
    void shouldGetMessageReturnQuestionsRuRuWhenLocaleIsRu() {
        assertEquals("Введите ваше имя", messageService.getMessage("insertName"));
    }

    @Test
    void shouldGetMessageReurnQuestionsWhenLocaleIsEmpty() {
        given(localeConfig.getLocale()).willReturn(Locale.ROOT);
        ReflectionTestUtils.setField(messageService, "localeConfig", localeConfig);

        assertEquals("Insert your name", messageService.getMessage("insertName"));
    }
}
