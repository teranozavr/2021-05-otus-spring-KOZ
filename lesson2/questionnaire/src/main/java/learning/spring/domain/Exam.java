package learning.spring.domain;

import java.util.List;

public class Exam {

    private final List<Question> questionList;

    public Exam(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Question> getQuestionsList() {
        return questionList;
    }
}
