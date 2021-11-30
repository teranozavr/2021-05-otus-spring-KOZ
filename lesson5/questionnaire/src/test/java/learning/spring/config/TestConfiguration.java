package learning.spring.config;

import learning.spring.dao.QuestionDaoCsv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public LocaleConfig localeConfig(){
        return new LocaleConfig();
    }

    @Bean
    public QuestionsConfig questionsConfig(LocaleConfig localeConfig) {
        return new QuestionsConfig(localeConfig);
    }

    @Bean
    public QuestionDaoCsv questionDao(QuestionsConfig questionsConfig) {
        return new QuestionDaoCsv(questionsConfig);
    }
}
