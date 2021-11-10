package learning.spring.service.Impl;

import learning.spring.dao.QuestionDao;
import learning.spring.domain.*;
import learning.spring.service.QuestionService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.questionDao = dao;
    }

    public List<Question> getAllQuestions() throws Exception {
        return questionDao.getAllQuestions();
    }

}
