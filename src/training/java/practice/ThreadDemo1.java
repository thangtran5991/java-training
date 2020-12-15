package training.java.practice;

public class ThreadDemo1 extends Thread{
    private SynchronizedDemo synchronizedDemo;
    private String threadName;
    public ThreadDemo1(SynchronizedDemo synchronizedDemo, String threadName) {
        this.synchronizedDemo = synchronizedDemo;
        this.threadName = threadName;
    }
    public void run() {
        synchronizedDemo.printData(threadName);
    }
}
