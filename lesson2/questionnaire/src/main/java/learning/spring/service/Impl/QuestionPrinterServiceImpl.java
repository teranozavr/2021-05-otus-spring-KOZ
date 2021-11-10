package learning.spring.service.Impl;

import learning.spring.domain.Answer;
import learning.spring.domain.Question;
import learning.spring.service.IOService;
import learning.spring.service.QuestionPrinterService;
import org.springframework.stereotype.Component;

@Component
public class QuestionPrinterServiceImpl implements QuestionPrinterService {

    private final IOService ioService;

    public QuestionPrinterServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    public void printQuestion(Question question){
        ioService.out(question.getQuestionText());
        for (Answer a : question.getAnswers()
        ) {
            ioService.out("-"+a.getAnswerText());
        }
    }
}
