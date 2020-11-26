public class LambdaExpression {
    interface Add {
        int add(int a, int b);
    }

    public static void main(String[] args) {
        Add add1 = (int a, int b) -> (a + b);
        System.out.println(add1.add(29, 34));

        Add add2 = (a, b) -> (a + b);
        System.out.println(add2.add(23, 100));

        Add add3 = (a, b) -> {
            return (a + b);
        };
        System.out.println(add3.add(123, 1234));
    }
}
