package learning.spring.service.Impl;

import learning.spring.exceptions.AnswerProcessingException;
import learning.spring.exceptions.QuestionProcessingException;
import learning.spring.service.ExceptionPrinterService;
import learning.spring.service.IOService;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class ExceptionPrinterServiceImpl implements ExceptionPrinterService {

    private final IOService ioService;

    public ExceptionPrinterServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    public void printException(Exception e){
        if (e.getClass().equals(QuestionProcessingException.class)){
            ioService.out("Error when question processing.");
            return;
        }
        if (e.getClass().equals(AnswerProcessingException.class)){
            ioService.out("Error when answer read. Try again!");
            return;
        }
        if (e.getClass().equals(FileNotFoundException.class)){
            ioService.out("File not found "+e.getMessage());
            return;
        }
        ioService.out("Unexpected error!");
    }
}
