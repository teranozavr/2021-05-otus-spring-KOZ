package learning.spring.service.Impl;

import learning.spring.domain.Exam;
import learning.spring.domain.Question;
import learning.spring.domain.ExamResult;
import learning.spring.exceptions.AnswerProcessingException;
import learning.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    private final IOService ioService;

    private final ExceptionProcessor exceptionProcessor;

    private final QuestionPrinterService questionPrinterService;

    private final QuestionService questionService;

    private final int rightAnswersLimit;

    @Autowired
    public ExamServiceImpl(QuestionService questionService, IOService ioService, ExceptionProcessor exceptionProcessor, QuestionPrinterService questionPrinterService, @Value("#{new Integer(${right.count})}") int rightAnswersLimit) {
        this.questionService = questionService;
        this.ioService = ioService;
        this.exceptionProcessor = exceptionProcessor;
        this.questionPrinterService = questionPrinterService;
        this.rightAnswersLimit = rightAnswersLimit;
    }

    @Override
    public void startExam() {
        try {
            Exam exam = new Exam(questionService.getAllQuestions());
            ExamResult examResult = new ExamResult(rightAnswersLimit);
            for (var question : exam.getQuestionsList()) {
                questionPrinterService.printQuestion(question);
                if (askQuestion(question)) {
                    examResult.increaseRightAnswersCount();
                }
            }
            printExamResult(examResult);
        }
        catch(Exception e){
            exceptionProcessor.processException(e);
        }
    }

    private boolean askQuestion(Question question) throws AnswerProcessingException {
        try {
            int answerNumber = Integer.parseInt(ioService.readString());
            return question.getRightAnswerNumber().equals(answerNumber);
        }
        catch (Exception e) {
            throw new AnswerProcessingException(e.getCause());
        }
    }

    private void printExamResult(ExamResult examResult) {
        if(examResult.getExamResult()) {
            ioService.out("Passed");
            return;
        }
        ioService.out("Failed");
    }
}
