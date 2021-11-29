package learning.spring.service.Impl;

import learning.spring.domain.Student;
import learning.spring.service.IOService;
import learning.spring.service.StudentIoService;
import learning.spring.service.UniversalPrintService;
import org.springframework.stereotype.Service;

@Service
public class StudentIoServiceImpl implements StudentIoService {
    private final IOService ioService;
    private final UniversalPrintService printService;

    public StudentIoServiceImpl(IOService ioService, UniversalPrintService printService) {
        this.ioService = ioService;
        this.printService = printService;
    }

    public Student getStudent(){
        Student student = new Student();
        printService.printMessage("insertName");
        student.setName(ioService.readString());
        printService.printMessage("insertSurname");
        student.setSurname(ioService.readString());
        return student;
    }
}
