package training.java.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEmail {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        Matcher matcher = pattern.matcher("foobar@gmail.com");
        if (matcher.find()) {
            System.out.println("Correct!");
        } else {
            System.out.println("Email is not in correct format");
        }
    }
}
