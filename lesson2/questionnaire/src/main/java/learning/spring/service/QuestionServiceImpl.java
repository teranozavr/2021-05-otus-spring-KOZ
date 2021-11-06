package learning.spring.service;

import learning.spring.dao.QuestionDao;
import learning.spring.domain.*;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.questionDao = dao;
    }

    public List<Question> getAllQuestions(){
        return questionDao.getAll();
    }

}
