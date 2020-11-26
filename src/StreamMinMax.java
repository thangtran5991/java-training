import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StreamMinMax {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 4, 56, 6, 67, 677);
        long count = list.stream().count();
        Optional<Integer> max = list.stream().max(Comparator.comparing(Integer::valueOf));
        System.out.println(count);
        System.out.println(max);
    }
}
