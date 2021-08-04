package learning.spring.service;
import learning.spring.domain.*;

import java.io.IOException;
import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions() throws IOException;
}
