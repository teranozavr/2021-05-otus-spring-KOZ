package learning.spring.dao;
import learning.spring.domain.*;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions(String location);
}
