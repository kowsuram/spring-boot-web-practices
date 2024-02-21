package io.kowsu;

import io.kowsu.threads.ComposeThread;
import io.kowsu.threads.Sender;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;

/*
    @created January/29/2024 - 1:38 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class MessageRunnerTest {

    Sender sender = new Sender();


    @Test
    public void testMessages() {
        Thread t1 = new Thread(new ComposeThread("Message #1 ", sender));
        Thread t2 = new Thread(new ComposeThread("Message #2 ", sender));
        Thread t3 = new Thread(new ComposeThread("Message #3 ", sender));
        Thread t4 = new Thread(new ComposeThread("Message #4 ", sender));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
