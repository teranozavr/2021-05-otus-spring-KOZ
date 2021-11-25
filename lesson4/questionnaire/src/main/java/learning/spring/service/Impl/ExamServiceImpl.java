package learning.spring.service.Impl;

import learning.spring.domain.Exam;
import learning.spring.domain.Question;
import learning.spring.domain.ExamResult;
import learning.spring.domain.Student;
import learning.spring.exceptions.AnswerProcessingException;
import learning.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    private final IOService ioService;

    private final ExceptionPrinterService exceptionPrinterService;

    private final QuestionPrinterService questionPrinterService;

    private final QuestionService questionService;

    private final int rightAnswersLimit;

    private final MessageService messageService;

    @Autowired
    public ExamServiceImpl(QuestionService questionService, IOService ioService, ExceptionPrinterService exceptionPrinterService, QuestionPrinterService questionPrinterService, @Value("#{new Integer(${right.count})}") int rightAnswersLimit, MessageService messageService) {
        this.questionService = questionService;
        this.ioService = ioService;
        this.exceptionPrinterService = exceptionPrinterService;
        this.questionPrinterService = questionPrinterService;
        this.rightAnswersLimit = rightAnswersLimit;
        this.messageService = messageService;
    }

    @Override
    public void startExam() {
        try {
            Student student = getStudent();
            Exam exam = new Exam(questionService.getAllQuestions());
            ExamResult examResult = new ExamResult(rightAnswersLimit);

            for (var question : exam.getQuestionsList()) {
                questionPrinterService.printQuestion(question);
                if (askQuestion(question)) {
                    examResult.increaseRightAnswersCount();
                }
            }
            printExamResult(student, examResult);
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

    private void printExamResult(Student student, ExamResult examResult) {
        String resultStr = messageService.getMessage(examResult.getExamResult()? "testResultOk": "testResultFail");
        ioService.out(String.format(resultStr, student));
    }

    private Student getStudent(){
        Student student = new Student();
        ioService.out(messageService.getMessage("insertName"));
        student.setName(ioService.readString());
        ioService.out(messageService.getMessage("insertSurname"));
        student.setSurname(ioService.readString());
        return student;
    }
}
