public class Test {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer("Java");
        for (int i = 0; i < 100000; i++) {
            stringBuffer.append("Hello");
        }
        System.out.println("Time to concat string of StringBuffer: "
                + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder("Java");
        for (int i = 0; i < 100000; i++) {
            stringBuilder.append("Hello");
        }
        System.out.println("Time to concat string of StringBuilder: "
                + (System.currentTimeMillis() - startTime) + "ms");
    }
}