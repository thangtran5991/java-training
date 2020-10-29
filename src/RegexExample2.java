public class RegexExample2 {
    public static void main(String[] args) {
        String[] strings = {"", "1603", "1998", "NMD98", "1998d", "nmdse"};
        for (String str: strings) {
            System.out.println("Chuỗi " + str + " có chứa các chữ cái: " + str.matches(".*[a-zA-Z].*"));
        }
        System.out.println("________________");
        for (String str: strings) {
            System.out.println("Chuỗi " + str + " có chứa các chữ số: " +str.matches(".*[0-0].*"));
        }
    }
}
