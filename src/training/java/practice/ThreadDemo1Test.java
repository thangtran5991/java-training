package training.java.practice;

public class ThreadDemo1Test {
    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        ThreadDemo1 threadDemo1 = new ThreadDemo1(synchronizedDemo, "Thread1");
        ThreadDemo1 threadDemo2 = new ThreadDemo1(synchronizedDemo, "Thread2");
        ThreadDemo1 threadDemo3 = new ThreadDemo1(synchronizedDemo, "Thread3");
        threadDemo1.start();
        threadDemo2.start();
        threadDemo3.start();
    }
}
