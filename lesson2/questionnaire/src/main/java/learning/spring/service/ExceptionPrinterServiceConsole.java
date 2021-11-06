package learning.spring.service;

import learning.spring.exceptions.AnswerProcessingException;
import learning.spring.exceptions.QuestionProcessingException;

import java.io.FileNotFoundException;

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
