package learning.spring.domain;

public class Answer {
    private final String answerText;

    private final Boolean isRight;

    private final Integer answerNumber;

    public Answer(String answerText, Boolean isRight, Integer answerNumber) {
        this.answerText = answerText;
        this.isRight = isRight;
        this.answerNumber = answerNumber;
    }

    public String getAnswerText(){
        return answerText;
    }

    public boolean isRight(){
        return isRight;
    }

    public Integer getAnswerNumber() {
        return answerNumber;
    }
}
