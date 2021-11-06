package learning.spring.exceptions;

import java.io.IOException;

public class AnswerProcessingException extends IOException {
    public AnswerProcessingException(Throwable cause) {
        super(cause);
    }
}
