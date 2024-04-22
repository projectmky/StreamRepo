package com.scytalys;

import java.util.concurrent.Callable;

public class Worker implements Runnable {
    private final int id;

    public Worker(int id) {
        this.id = id;
    }

    public void work() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println("Thread: " + id + " has Started Working");
        work();
        System.out.println("Thread: " + id + " has finished working.");
    }

    public String doFutureWork(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Future work form id: "+id;

    }
}
