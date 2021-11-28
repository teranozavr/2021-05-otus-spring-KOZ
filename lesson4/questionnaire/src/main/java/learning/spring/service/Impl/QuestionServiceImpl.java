package learning.spring.service.Impl;

import learning.spring.dao.QuestionDao;
import learning.spring.domain.*;
import learning.spring.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.questionDao = dao;
    }

    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

}
