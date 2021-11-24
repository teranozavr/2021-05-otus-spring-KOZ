package learning.spring.service.Impl;

import learning.spring.exceptions.AnswerProcessingException;
import learning.spring.exceptions.QuestionReadingException;
import learning.spring.service.ExceptionPrinterService;
import learning.spring.service.IOService;
import learning.spring.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class ExceptionPrinterServiceImpl implements ExceptionPrinterService {

    private final IOService ioService;
    private final MessageService messageService;

    public ExceptionPrinterServiceImpl(IOService ioService, MessageService messageService) {
        this.ioService = ioService;
        this.messageService = messageService;
    }

    public void printException(Exception e){
        if (e.getClass().equals(QuestionReadingException.class)){
            ioService.out(String.format(messageService.getMessage("questionReadingException"), e.getCause()));
            return;
        }
        if (e.getClass().equals(AnswerProcessingException.class)){
            ioService.out(messageService.getMessage("answerProcessingException"));
            return;
        }
        ioService.out("unexpectedException");
    }
}
