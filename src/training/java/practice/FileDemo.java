package training.java.practice;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Thang-Tran\\Downloads\\file\\filename.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                System.out.println("Absolute path: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exist");
            }
        } catch (IOException e) {
            System.out.println("Have an error occurred");
            e.printStackTrace();
        }
    }
}
