package learning.spring.service.Impl;

import learning.spring.exceptions.AnswerProcessingException;
import learning.spring.exceptions.QuestionReadingException;
import learning.spring.service.ExceptionPrinterService;
import learning.spring.service.IOService;
import org.springframework.stereotype.Service;

@Service
public class ExceptionPrinterServiceImpl implements ExceptionPrinterService {

    private final IOService ioService;

    public ExceptionPrinterServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    public void printException(Exception e){
        if (e.getClass().equals(QuestionReadingException.class)){
            ioService.out(String.format("Error when question processing. %s", e.getCause()));
            return;
        }
        if (e.getClass().equals(AnswerProcessingException.class)){
            ioService.out("Error when answer read. Try again!");
            return;
        }
        ioService.out("Unexpected error!");
    }
}
