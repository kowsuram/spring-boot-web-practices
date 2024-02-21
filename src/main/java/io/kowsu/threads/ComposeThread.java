package io.kowsu.threads;

import java.util.concurrent.Semaphore;

/*
    @created February/06/2024 - 5:58 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class ComposeThread implements Runnable {

    private String message;
    private Sender sender;
    Semaphore semaphore = new Semaphore(4);

    public ComposeThread(String message, Sender sender) {
        this.message = message;
        this.sender = sender;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("Enter " + Thread.currentThread().getName());
            sender.send(this.message);
            System.out.println("Exit " + Thread.currentThread().getName());
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
