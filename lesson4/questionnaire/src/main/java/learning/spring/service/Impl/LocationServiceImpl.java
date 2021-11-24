package learning.spring.service.Impl;

import learning.spring.config.QuestionsConfig;
import learning.spring.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocationServiceImpl implements LocationService {
    private final QuestionsConfig questionsConfig;

    public LocationServiceImpl(QuestionsConfig questionsConfig) {
        this.questionsConfig = questionsConfig;
    }

    public String getFilePath() {
        return questionsConfig.getPath() +
                questionsConfig.getTemplate() +
                (Locale.getDefault().equals(Locale.ROOT) ? "" : "_" + Locale.getDefault()) +
                "." +
                questionsConfig.getExtension();
    }
}
