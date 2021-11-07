package learning.spring.service.Impl;

import learning.spring.exceptions.AnswerProcessingException;
import learning.spring.exceptions.QuestionProcessingException;
import learning.spring.service.ExceptionPrinterService;
import learning.spring.service.IOService;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class ExceptionPrinterServiceConsole implements ExceptionPrinterService {

    private final IOService ioServiceConsole;

    public ExceptionPrinterServiceConsole(IOService ioServiceConsole) {
        this.ioServiceConsole = ioServiceConsole;
    }

    public void printException(Exception e){
        if (e.getClass().equals(QuestionProcessingException.class)){
            ioServiceConsole.out("Error when question processing.");
            return;
        }
        if (e.getClass().equals(AnswerProcessingException.class)){
            ioServiceConsole.out("Error when answer read. Try again!");
            return;
        }
        if (e.getClass().equals(FileNotFoundException.class)){
            ioServiceConsole.out("File not found "+e.getMessage());
            return;
        }
        ioServiceConsole.out("Unexpected error!");
    }
}
