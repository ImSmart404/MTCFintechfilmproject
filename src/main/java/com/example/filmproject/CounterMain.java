package com.example.filmproject;

import com.example.filmproject.service.impl.Counter;

public class CounterMain {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        int increments = 1000000;
        CounterThread counterThread1 = new CounterThread(counter, increments);
        CounterThread counterThread2 = new CounterThread(counter, increments);
        CounterThread counterThread3 = new CounterThread(counter, increments);
        Thread thread1 = new Thread(counterThread1);
        Thread thread2 = new Thread(counterThread2);
        Thread thread3 = new Thread(counterThread3);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("Значение счетчика: " + counter.getValue());
    }
}
