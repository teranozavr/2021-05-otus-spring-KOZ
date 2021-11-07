package learning.spring.domain;

import org.springframework.beans.factory.annotation.Value;

public class ExamResult {

    @Value("#{new Integer(${right.count})}")
    private int rightAnswersLimit;
    private int rightAnswerCount = 0;

    public void increaseRightAnswersCount(){
        rightAnswerCount++;
    }

    public boolean getExamResult() {
        return rightAnswerCount>=rightAnswersLimit;
    }
}
