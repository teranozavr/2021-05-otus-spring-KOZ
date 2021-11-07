package learning.spring.domain;

public class ExamResult {

    private final int rightAnswersLimit;
    private int rightAnswerCount = 0;

    public ExamResult(int rightAnswersLimit) {
        this.rightAnswersLimit = rightAnswersLimit;
    }

    public void increaseRightAnswersCount(){
        rightAnswerCount++;
    }

    public boolean getExamResult() {
        return rightAnswerCount>=rightAnswersLimit;
    }
}
