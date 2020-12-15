package training.java.practice;

public class FunctionalInterfaceDemo {
    interface FunctionalInterface1 {

    }
    @FunctionalInterface
    public interface Add extends FunctionalInterface1{
        void doSomething();
        default void defaultMethod() {

        };
        static void staticMethod() {

        }

    }
}
