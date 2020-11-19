public class ThreadDemoTest {
    public static void main(String[] args) {
        System.out.println("Main thread running...");

        ThreadDemo t1 = new ThreadDemo("Thread-1 HR Database");
        t1.start();

        ThreadDemo t2 = new ThreadDemo("Thread-2 Send Email");
        t2.start();

        System.out.println("==> Main thread stopped!!!");
    }
}
