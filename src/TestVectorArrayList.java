import java.util.ArrayList;
import java.util.Vector;

public class TestVectorArrayList {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i);
        }
        System.out.println("Time to add value to ArrayList: " + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i< 1000000; i++) {
            vector.add(i);
        }
        System.out.println("Time to add value to Vector: " + (System.currentTimeMillis() - startTime));
    }
}
