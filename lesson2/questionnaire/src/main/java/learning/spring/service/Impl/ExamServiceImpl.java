package learning.spring.service.Impl;

import learning.spring.domain.Exam;
import learning.spring.domain.Question;
import learning.spring.domain.ExamResult;
import learning.spring.exceptions.AnswerProcessingException;
import learning.spring.service.*;

public class ExamServiceImpl implements ExamService {

    private Exam exam;

    private ExamResult examResult;

    private final IOService ioService;

    private final ExceptionPrinterService exceptionPrinterService;

    private final QuestionPrinterService questionPrinterService;

    private final int rightAnswersLimit;

    private final QuestionService questionService;

    public ExamServiceImpl(QuestionService questionService, IOService ioService, ExceptionPrinterService exceptionPrinterService, QuestionPrinterService questionPrinterService, int rightAnswersLimit) throws Exception {
        this.questionService = questionService;
        this.ioService = ioService;
        this.exceptionPrinterService = exceptionPrinterService;
        this.questionPrinterService = questionPrinterService;
        this.rightAnswersLimit = rightAnswersLimit;
    }

    @Override
    public void startExam() {
        try {
            this.exam = new Exam(questionService.getAllQuestions());
            this.examResult = new ExamResult(rightAnswersLimit);
            for (var question : exam.getQuestionsList()) {
                questionPrinterService.printQuestion(question);
                if (askQuestion(question)) {
                    examResult.increaseRightAnswersCount();
                }
            }
            printExamResult();
        }
        catch(Exception e){
            exceptionPrinterService.printException(e);
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

    private void printExamResult() {
        if(examResult.getExamResult()) {
            ioService.out("Passed");
            return;
        }
        ioService.out("Failed");
    }
}
