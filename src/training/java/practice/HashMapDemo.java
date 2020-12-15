package training.java.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void countFrequencies(ArrayList<String> list) {
        Map<String, Integer> hashMap = new HashMap<>();
        for (String i : list) {
            Integer j = hashMap.get(i);
            hashMap.put(i, (j == null) ? 1 : j +1);
        }
        for (Map.Entry<String, Integer> val : hashMap.entrySet()) {
            System.out.println(val.getKey() + "|" + val.getValue());
        }
    }
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("abc");
        arrayList.add("bca");
        arrayList.add("abc");
        arrayList.add("abc");
        countFrequencies(arrayList);
    }
}
