package learning.spring.service.Impl;

import learning.spring.dao.QuestionDao;
import learning.spring.domain.*;
import learning.spring.service.QuestionService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final MessageSource messageSource;

    public QuestionServiceImpl(QuestionDao dao, MessageSource messageSource) {
        this.questionDao = dao;
        this.messageSource = messageSource;
    }

    public List<Question> getAllQuestions() {
        String location = messageSource.getMessage("location", null, Locale.getDefault());
        return questionDao.getAllQuestions(location);
    }

}
