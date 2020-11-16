public class EqualDemo {
    public static void main(String[] args) {
        int a = 3;
        int b = 3;
        System.out.println("a == b: " + (a==b));

        Integer c = new Integer(9);
        Integer d = new Integer(9);
        System.out.println("c == d: " + (c == d));

        String e = "abc";
        String f = "abc";
        System.out.println("e == f: " + e.equals(f));

        String g = new String("abc");
        String h = new String("abc");
        System.out.println("g == h: " + g.equals(h));
    }
}
