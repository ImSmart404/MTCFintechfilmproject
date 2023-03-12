package com.example.filmproject;

import com.example.filmproject.service.impl.Counter;

public class CounterThread implements Runnable{

    private Counter counter;
    private int increments;

    public CounterThread(Counter counter, int increments) {
        this.counter = counter;
        this.increments = increments;
    }

    @Override
    public void run() {
        for (int i = 0; i < increments; i++) {
            counter.increment();
        }
    }
}
