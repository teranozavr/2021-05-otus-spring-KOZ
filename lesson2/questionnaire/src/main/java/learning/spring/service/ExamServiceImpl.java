package learning.spring.service;

import learning.spring.domain.Exam;
import learning.spring.domain.Question;
import learning.spring.domain.ExamResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static learning.spring.helpers.QuestionPrinter.printQuestion;

public class ExamServiceImpl implements ExamService{

    private final Exam exam;

    private final ExamResult examResult;

    public ExamServiceImpl(Exam exam, ExamResult examResult){
        this.exam = exam;
        this.examResult = examResult;
    }

    @Override
    public void startExam() throws IOException {
        for (var question: exam.getQuestionsList()) {
            printQuestion(question);
            if (askQuestion(question)) {
                examResult.increaseRightAnswersCount();
            }
        }
    }

    private boolean askQuestion(Question question) throws IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String a = br.readLine();
        int answerNumber = Integer.parseInt(a);
        return question.getRightAnswerNumber().equals(answerNumber);
    }

    @Override
    public void printExamResult() {
        if(examResult.getExamResult()) {
            System.out.println("Экзамен сдан");
            return;
        }
        System.out.println("Экзамен не сдан");
    }
}
