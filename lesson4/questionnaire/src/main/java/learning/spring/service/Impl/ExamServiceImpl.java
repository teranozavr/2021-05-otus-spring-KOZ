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

    private final StudentIoService studentIoService;

    private final QuestionService questionService;

    private final int rightAnswersLimit;

    private final UniversalPrintService printService;

    @Autowired
    public ExamServiceImpl(QuestionService questionService, @Value("#{new Integer(${right.count})}") int rightAnswersLimit, IOService ioService, StudentIoService studentIoService, UniversalPrintService printService) {
        this.questionService = questionService;
        this.ioService = ioService;
        this.printService = printService;
        this.rightAnswersLimit = rightAnswersLimit;
        this.studentIoService = studentIoService;
    }

    @Override
    public void startExam() {
        try {
            Student student = studentIoService.getStudent();
            Exam exam = new Exam(questionService.getAllQuestions());
            ExamResult examResult = new ExamResult(rightAnswersLimit);

            for (var question : exam.getQuestionsList()) {
                printService.printQuestion(question);
                if (askQuestion(question)) {
                    examResult.increaseRightAnswersCount();
                }
            }
            printExamResult(student, examResult);
        }
        catch(Exception e){
            printService.printException(e);
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
        printService.printResultMessage(student, examResult);
    }
}
