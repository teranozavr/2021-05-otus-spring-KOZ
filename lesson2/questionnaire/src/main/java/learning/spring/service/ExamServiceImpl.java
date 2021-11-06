package learning.spring.service;

import learning.spring.domain.Exam;
import learning.spring.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static learning.spring.helpers.QuestionPrinter.printQuestion;

public class ExamServiceImpl implements ExamService{

    private final int rightCount;

    private final Exam exam;

    public ExamServiceImpl(Exam exam, int rightCount){
        this.exam = exam;
        this.rightCount = rightCount;
    }

    @Override
    public void startExam() throws IOException {

        while(!exam.isFinalQuestion()){
            Question question = exam.getNextQuestion();
            printQuestion(question);
            System.out.println("Ответ: ");

            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            String a = br.readLine();
            int answerNumber = Integer.parseInt(a);

            tryToIncreaseRightAnswerCount(question.getRightAnswerNumber(), answerNumber);
        }
    }

    @Override
    public void printExamResult() {
        if(exam.getRightAnswersCount()>=rightCount) {
            System.out.println("Экзамен сдан");
            return;
        }
        System.out.println("Экзамен не сдан");
    }

    private void tryToIncreaseRightAnswerCount(Integer expextedAnswernumber, Integer actualAnswerNumber) {
        if(expextedAnswernumber.equals(actualAnswerNumber)) {
            exam.increaseRightAnswersCount();
        }
    }
}
