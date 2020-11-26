import java.util.Arrays;
import java.util.List;

public class StreamSkip {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "C++", "C#", "PHP", "Python");
        boolean result = list.stream().anyMatch(s -> s.equalsIgnoreCase("Java"));
        System.out.println(result);

        List<Integer> integerList = Arrays.asList(1, 23, 56, 76, 13, 453);
        boolean result2 = integerList.stream().allMatch(s -> s > 1);
        boolean result3 = integerList.stream().noneMatch(s -> s < 1000);
        System.out.println(result2);
        System.out.println(result3);
    }
}
