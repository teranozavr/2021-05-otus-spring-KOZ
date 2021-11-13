package learning.spring.exceptions;

public class AnswerProcessingException extends RuntimeException {
    public AnswerProcessingException(Throwable cause) {
        super(cause);
    }
}
