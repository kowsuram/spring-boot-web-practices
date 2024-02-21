package io.kowsu.threads;

import java.util.concurrent.locks.ReentrantLock;

/*
    @created February/06/2024 - 5:57 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class Sender {

    ReentrantLock lock = new ReentrantLock();

    public void send(String message) {
        lock.lock();
        System.out.printf("Received message %s \n", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            lock.unlock();
            throw new RuntimeException(e);
        }
        lock.unlock();
    }
}
