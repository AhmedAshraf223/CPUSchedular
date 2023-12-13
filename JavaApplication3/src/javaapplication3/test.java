package javaapplication3;

import java.util.concurrent.atomic.AtomicInteger;

public class test {
    public static void main(String[] args) {
        AtomicInteger number = new AtomicInteger(0);
        change(number);
        System.out.println(number.get());
    }

    public static void change(AtomicInteger number) {
        number.incrementAndGet(); // This method modifies the AtomicInteger in place
    }
}
