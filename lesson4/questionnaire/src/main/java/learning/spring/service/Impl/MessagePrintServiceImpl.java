package learning.spring.service.Impl;
import learning.spring.domain.ExamResult;
import learning.spring.domain.Student;
import learning.spring.service.IOService;
import learning.spring.service.MessagePrintService;
import learning.spring.service.MessageService;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

@Service
public class MessagePrintServiceImpl implements MessagePrintService {

    private final MessageService messageService;
    private final IOService ioService;

    public MessagePrintServiceImpl(MessageService messageService, IOService ioService) {
        this.messageService = messageService;
        this.ioService = ioService;
    }

    private String getMessage(String message) {
        try {
            return messageService.getMessage(message);
        }
        catch (NoSuchMessageException e) {
            return message;
        }
    }

    public void printMessage(String message) {
            ioService.out(getMessage(message));
    }

    public void printResultMessage(Student student, ExamResult examResult){
        String resultStr = messageService.getMessage(examResult.getExamResult()? "testResultOk": "testResultFail");
        ioService.out(String.format(resultStr, student));
    }
}
