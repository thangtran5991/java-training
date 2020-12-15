package training.java.practice;

public class SleepThread extends Thread{
    public void run() {
        for (int i = 1; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        SleepThread s1 = new SleepThread();
        SleepThread s2 = new SleepThread();
        s1.run();
        s2.run();
    }
}
