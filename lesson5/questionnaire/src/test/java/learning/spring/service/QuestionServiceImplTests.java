package learning.spring.service;

import learning.spring.commands.Commands;
import learning.spring.config.QuestionsConfig;
import learning.spring.dao.QuestionDaoCsv;
import learning.spring.domain.Answer;
import learning.spring.domain.Question;
import learning.spring.service.Impl.ExamServiceImpl;
import learning.spring.service.Impl.QuestionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Shell;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class QuestionServiceImplTests {

    @Configuration
    @ComponentScan(basePackages = "learning.spring.service")
    static class TestConfiguration {
    }

    @MockBean
    private QuestionDaoCsv questionDao;

    @MockBean
    private QuestionsConfig questionsConfig;

    @MockBean
    private Commands commands;

    @MockBean
    private ExamServiceImpl examService;

    @Autowired
    private QuestionServiceImpl questionService;

    @MockBean
    private Shell shell;

    @MockBean
    ExceptionPrinterService exceptionPrinterService;

    @MockBean
    MessagePrintService messagePrintService;

    @MockBean
    MessageService messageService;

    @BeforeEach
    public void init() {
        openMocks(this);
        given(questionDao.getAllQuestions()).willReturn(getAllQuestions());
    }

    @Test
    public void getAllQuestionsTest() {
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
