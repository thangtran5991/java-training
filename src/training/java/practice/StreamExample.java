package training.java.practice;

import java.util.Arrays;
import java.util.List;

public class StreamExample {
    List<Integer> numbers = Arrays.asList(7, 2, 5, 8, 9, 10, 14);
    public void withStream() {
        long count = numbers.stream().filter(num -> num % 2 == 0).count();
        System.out.println("There are %d elements that are even: " + count);
    }

    public static void main(String[] args) {
        StreamExample streamExample = new StreamExample();
        streamExample.withStream();
    }
}
