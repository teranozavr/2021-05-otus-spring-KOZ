package learning.spring.service;

import learning.spring.dao.QuestionDao;
import learning.spring.domain.*;

import java.util.List;

import static learning.spring.helpers.QuestionPrinter.printQuestionarie;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.questionDao = dao;
    }

    public List<Question> getAllQuestions(){
        return questionDao.getAll();
    }

    public void printAllQuestions(){
        printQuestionarie(getAllQuestions());
    }

}
