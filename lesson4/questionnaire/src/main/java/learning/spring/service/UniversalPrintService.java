package learning.spring.service;

import learning.spring.domain.ExamResult;
import learning.spring.domain.Question;
import learning.spring.domain.Student;

public interface UniversalPrintService {
    void printException(Exception e);
    void printQuestion(Question question);
    void printMessage(String message);
    void printResultMessage(Student student, ExamResult examResult);
}
