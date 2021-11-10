package learning.spring.service.Impl;

import learning.spring.service.IOService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Component
public class IOServiceImpl implements IOService {
    private final PrintStream out;
    private final Scanner sc;


    public IOServiceImpl(@Value("#{T(java.lang.System).out}") PrintStream out,
                         @Value("#{T(java.lang.System).in}") InputStream in) {
        this.out = out;
        this.sc = new Scanner(in);
    }

    @Override
    public void out(String message) {
        out.println(message);
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }
}
