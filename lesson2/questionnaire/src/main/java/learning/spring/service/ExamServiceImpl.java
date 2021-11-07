package learning.spring.service;

import learning.spring.domain.Exam;
import learning.spring.domain.Question;
import learning.spring.domain.ExamResult;
import learning.spring.exceptions.QuestionProcessingException;

public class ExamServiceImpl implements ExamService{

    private final Exam exam;

    private final ExamResult examResult;

    private final IOService ioService;

    private final ExceptionPrinterService exceptionPrinterService;

    private final QuestionPrinterService questionPrinterService;

    public ExamServiceImpl(QuestionService questionService, IOService ioService, ExceptionPrinterService exceptionPrinterService, QuestionPrinterService questionPrinterService){
        this.exam = new Exam(questionService.getAllQuestions());
        this.examResult = new ExamResult();
        this.ioService = ioService;
        this.exceptionPrinterService = exceptionPrinterService;
        this.questionPrinterService = questionPrinterService;
    }

    @Override
    public void startExam() {
        try {
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

    private boolean askQuestion(Question question) throws QuestionProcessingException {
        try {
            int answerNumber = Integer.parseInt(ioService.readString());
            return question.getRightAnswerNumber().equals(answerNumber);
        }
        catch (Exception e) {
            throw new QuestionProcessingException(e.getCause());
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
