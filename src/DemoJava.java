public class DemoJava {
    public static void main(String[] args) {
            String input = "Dimage Share";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(input);
            stringBuilder.reverse();
            for (int i = 0; i < stringBuilder.length(); i++) {
                System.out.print(stringBuilder.charAt(i));
            }

    }
}
