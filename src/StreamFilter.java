import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamFilter {
    public static void main(String[] args) {
        // Su dung filter de loc cac so chia het cho 2
        List<Integer> list = Arrays.asList(1, 4, 56, 9, 18, 90, 116);
        list.stream()
                .filter(t -> t % 3 == 0)
                .forEach(System.out::println);
    }
}
