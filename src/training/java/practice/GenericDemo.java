package training.java.practice;

public class GenericDemo {
    public static void main(String[] args) {
        KeyValuePair<String, Integer> keyValuePair = new KeyValuePair<>("Tran Hue Thang", 1995);
        String name = keyValuePair.getKey();
        Integer id = keyValuePair.getValue();
        System.out.println("Name: " + name + ", Id = " + id);
    }
}
