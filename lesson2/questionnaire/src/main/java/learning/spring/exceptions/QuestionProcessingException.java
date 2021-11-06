package learning.spring.exceptions;

import java.io.IOException;

public class QuestionProcessingException extends IOException {
    public QuestionProcessingException(Throwable cause) {
        super(cause);
    }
}
