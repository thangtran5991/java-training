package training.java.practice;

public class TestJoinThread {
    public static void main(String[] args) {
        JoinThread thread1 = new JoinThread();
        thread1.setName("Thread 1");
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JoinThread thread2 = new JoinThread();
        thread2.setName("Thread 2");
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JoinThread thread3 = new JoinThread();
        thread3.setName("Thread 3");
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
