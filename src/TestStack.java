import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int a = stack.pop();
        int b = stack.pop();
        int c = a * b;
        int d = stack.pop();
        int e = c + d;
        System.out.println(e);
    }
}
