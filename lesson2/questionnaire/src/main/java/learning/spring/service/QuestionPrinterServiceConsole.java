package learning.spring.service;

import learning.spring.domain.Answer;
import learning.spring.domain.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionPrinterServiceConsole implements QuestionPrinterService {

    private final IOService ioServiceConsole;

    public QuestionPrinterServiceConsole(IOService ioServiceConsole) {
        this.ioServiceConsole = ioServiceConsole;
    }

    public void printQuestion(Question question){
        ioServiceConsole.out(question.getQuestionText());
        for (Answer a : question.getAnswers()
        ) {
            ioServiceConsole.out("-"+a.getAnswerText());
        }
    }
}
