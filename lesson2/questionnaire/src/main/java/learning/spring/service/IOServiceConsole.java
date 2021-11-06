package learning.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceConsole implements IOService {
    private final PrintStream out;
    private final Scanner sc;


    public IOServiceConsole(@Value("#{T(java.lang.System).out}") PrintStream out,
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
