package learning.spring.domain;

public class Answer {
    private final String answerText;

    public Answer(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerText(){
        return answerText;
    }
}
