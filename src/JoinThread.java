public class JoinThread extends Thread{
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + " running.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish " + Thread.currentThread().getName());
    }
}
