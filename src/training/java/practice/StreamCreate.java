package training.java.practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreate {
    public static void main(String[] args) {
        // Tao Stream cho nhung kieu primitive
        IntStream.range(1, 4).forEach(System.out::println);

        //Tao Stream tu cac cau truc du lieu khac
        String[] languages = {"Java", "C#", "C++"};
        Stream<String> stringStream = Arrays.stream(languages);
        stringStream.forEach(x -> System.out.println(x));

        //Tao Stream sang cac cau truc du lieu khac
        Stream<String> stream = Stream.of("Java", "PHP");
        List<String> languas = stream.collect(Collectors.toList());
        System.out.println(languas);
    }
}
