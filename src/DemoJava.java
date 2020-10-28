public class DemoJava {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("Dimage Share");
        char[] chars = new char[12];
        stringBuilder.getChars(0, 12, chars, 0);
        for (int i = chars.length - 1; i >= 0; i--) {
            System.out.print(chars[i]);
        }
    }
}
