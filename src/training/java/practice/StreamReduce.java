package training.java.practice;

import java.util.Arrays;
import java.util.List;

public class StreamReduce {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(234, 34, 356, 12345);
        int sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }
}
