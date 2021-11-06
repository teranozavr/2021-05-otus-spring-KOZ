package learning.spring.service;

import learning.spring.domain.Exam;
import learning.spring.domain.Question;
import learning.spring.domain.ExamResult;
import learning.spring.exceptions.QuestionProcessingException;

public class ExamServiceImpl implements ExamService{

    private final Exam exam;

    private final ExamResult examResult;

    private final IOService ioServiceConsole;

    private ExceptionPrinterServiceConsole exceptionPrinterServiceConsole;

    private QuestionPrinterServiceConsole questionPrinterServiceConsole;

    public ExamServiceImpl(Exam exam, ExamResult examResult, IOService ioServiceConsole){
        this.exam = exam;
        this.examResult = examResult;
        this.ioServiceConsole = ioServiceConsole;
        this.exceptionPrinterServiceConsole = new ExceptionPrinterServiceConsole(ioServiceConsole);
        this.questionPrinterServiceConsole = new QuestionPrinterServiceConsole(ioServiceConsole);
    }

    @Override
    public void startExam() {
        try {
            for (var question : exam.getQuestionsList()) {
                questionPrinterServiceConsole.printQuestion(question);
                if (askQuestion(question)) {
                    examResult.increaseRightAnswersCount();
                }
            }
            printExamResult();
        }
        catch(Exception e){
            exceptionPrinterServiceConsole.printException(e);
        }
    }

    private boolean askQuestion(Question question) throws QuestionProcessingException {
        try {
            int answerNumber = Integer.parseInt(ioServiceConsole.readString());
            return question.getRightAnswerNumber().equals(answerNumber);
        }
        catch (Exception e) {
            throw new QuestionProcessingException(e.getCause());
        }
    }

    private void printExamResult() {
        if(examResult.getExamResult()) {
            ioServiceConsole.out("Passed");
            return;
        }
        ioServiceConsole.out("Failed");
    }
}
