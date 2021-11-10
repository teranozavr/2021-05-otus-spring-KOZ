package learning.spring.dao;
import learning.spring.domain.*;
import learning.spring.exceptions.QuestionProcessingException;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions() throws QuestionProcessingException;
}
