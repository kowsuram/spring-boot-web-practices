package io.kowsu.dp.creational;

import io.kowsu.dp.util.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/*
    @created January/29/2024 - 1:28 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class SingletonPattern {

    public static void main(String[] args) throws InterruptedException {
        List<Callable<String>> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            persons.add(person);
        }
        boolean b = ThreadUtils.executeAll(persons);
        System.out.println("Result " + b);
    }


}

class Person implements Callable<String> {
    public Person() {
    }

    @Override
    public String call() {
        try {
            System.out.println("Person going to sleep for 1 sec." + Thread.currentThread().getName());
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Person called successfully." + Thread.currentThread().getName());
        return "Success";
    }
}
