package learning.spring.config;

import learning.spring.dao.QuestionDaoCsv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.BDDMockito.given;

@Configuration
public class TestConfiguration {
    private static final String LOCATION = "questions.csv";
    private static final String WRONG_LOCATION = "wrong.csv";

    @Bean
    public QuestionDaoCsv questionDao(QuestionsConfig questionsConfig) {
        given(questionsConfig.getFilePath()).willReturn(LOCATION);
        return new QuestionDaoCsv(questionsConfig);
    }

    @Bean
    public QuestionDaoCsv questionDaoWrongLocation(QuestionsConfig questionsConfig) {
        given(questionsConfig.getFilePath()).willReturn(WRONG_LOCATION);
        return new QuestionDaoCsv(questionsConfig);
    }
}
