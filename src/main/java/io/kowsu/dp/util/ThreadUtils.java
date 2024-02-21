package io.kowsu.dp.util;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
    @created January/29/2024 - 1:30 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class ThreadUtils {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);

    public static boolean executeOne(Runnable runnable) {
        Future<?> submit = EXECUTOR_SERVICE.submit(runnable);
        return submit.isDone();
    }

    public static boolean executeNGetResult(Runnable runnable, Object result) {
        Future<Object> submit = EXECUTOR_SERVICE.submit(runnable, result);
        return submit.isDone();
    }

    public static boolean executeAll(List<Callable<String>> callables) throws InterruptedException {
        List<Future<String>> futures = EXECUTOR_SERVICE.invokeAll(callables);
        return futures.stream().anyMatch(f -> !f.isDone());
    }


}
