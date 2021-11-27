package learning.spring.service.Impl;

import learning.spring.domain.ExamResult;
import learning.spring.domain.Question;
import learning.spring.domain.Student;
import learning.spring.service.ExceptionPrinterService;
import learning.spring.service.MessagePrintService;
import learning.spring.service.QuestionPrinterService;
import learning.spring.service.UniversalPrintService;
import org.springframework.stereotype.Service;

@Service
public class UniversalPrintServiceImpl implements UniversalPrintService {
    private final ExceptionPrinterService exceptionPrinterService;
    private final QuestionPrinterService questionPrinterService;
    private final MessagePrintService messagePrintService;

    public UniversalPrintServiceImpl(ExceptionPrinterService exceptionPrinterService, QuestionPrinterService questionPrinterService, MessagePrintService messagePrintService) {
        this.exceptionPrinterService = exceptionPrinterService;
        this.questionPrinterService = questionPrinterService;
        this.messagePrintService = messagePrintService;
    }

    public void printException(Exception e){
        exceptionPrinterService.printException(e);
    }

    public void printQuestion(Question question){
        questionPrinterService.printQuestion(question);
    }

    public void printMessage(String message) {
        messagePrintService.printMessage(message);
    }

    public void printResultMessage(Student student, ExamResult examResult){
        messagePrintService.printResultMessage(student, examResult);
    }
}
