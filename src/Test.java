import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Test t1 = new Test();
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("abc");
        arrayList.add("bcd");
        arrayList.add("bbb");
        arrayList.add("ace");
        arrayList.add("snb");
        arrayList.add("aaaa");
        arrayList.add("bbbbb");
        arrayList.add("eeee");
        t1.groupString(arrayList);
    }

    public static List<String> groupString(List<String> input) {
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<String> arrayList3 = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            switch (input.get(i).charAt(0)) {
                case 'a':
                    arrayList1.add(input.get(i));
                    break;
                case 'b':
                    arrayList2.add(input.get(i));
                    break;
                default:
                    arrayList3.add(input.get(i));
                    break;
            }
        }
        System.out.print(arrayList1);
        System.out.print(arrayList2);
        System.out.print(arrayList3);

        return input;
    }
}