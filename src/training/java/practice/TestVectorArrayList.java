package training.java.practice;

import java.util.ArrayList;
import java.util.Vector;

public class TestVectorArrayList extends Thread{
    public void run() {
        ArrayList<String> arrayList = new ArrayList<>();
        Vector<String> vector = new Vector<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        System.out.println(arrayList);
        vector.add("a");
        vector.add("b");
        vector.add("c");
        vector.add("d");
        System.out.println(vector);
    }

    public static void main(String[] args) {
        TestVectorArrayList t1 = new TestVectorArrayList();
        TestVectorArrayList t2 = new TestVectorArrayList();

        t1.start();
        t2.start();
    }
}
