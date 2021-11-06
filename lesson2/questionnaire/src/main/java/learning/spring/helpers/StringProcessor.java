package learning.spring.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringProcessor {

    public static List<String> getStringList(String str){
        return new ArrayList<>(Arrays.asList(str.split("\n")));
    }
}
