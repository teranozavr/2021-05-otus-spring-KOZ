package learning.spring.commands;

import learning.spring.service.ExamService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;



@ShellComponent
public class Commands {
    private final ExamService examService;

    public Commands(ExamService examService) {
        this.examService = examService;
    }

    @ShellMethod("Start exam")
    public void startExam() {
        examService.startExam();
    }
}
