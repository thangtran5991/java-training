package training.java.practice;

import java.util.stream.Stream;

public class StreamException {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Java", "C#", "PHP")
                .filter(s -> s.startsWith("J"));
        stream.anyMatch(s -> true);
        stream.noneMatch(s -> true);

    }
}
