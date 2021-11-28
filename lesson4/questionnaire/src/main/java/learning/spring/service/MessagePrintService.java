package learning.spring.service;

import learning.spring.domain.ExamResult;
import learning.spring.domain.Student;

public interface MessagePrintService {
    void printMessage(String message);
    void printResultMessage(Student student, ExamResult examResult);
}
