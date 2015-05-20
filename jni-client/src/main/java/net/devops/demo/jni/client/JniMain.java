package net.devops.demo.jni.client;

import lombok.extern.slf4j.Slf4j;
import net.devops.demo.jni.bindings.HelloWorldJni;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JniMain {

    public static void main(String[] args) throws Exception {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(500);
        ExecutorService executorService = new ThreadPoolExecutor(50, 100, 100, TimeUnit.SECONDS, workQueue);

        testParallel(executorService);
        testArray(executorService);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

    private static void testArray(ExecutorService executorService) {
        for (int i = 0; i < 1; i++) {
            final int loop = i;
            executorService.execute(() -> {
                int[] input = new int[1000000];
                for (int j = 0; j < input.length; j++) {
                    input[j] = j;
                }
                long start = System.currentTimeMillis();
                double[] output = new HelloWorldJni().array(input);
                long time = System.currentTimeMillis() - start;
                log.info("array: i: {}\ttime: {}", loop, time);
            });
        }
    }

    private static void testParallel(ExecutorService executorService) {
        for (int i = 0; i < 10; i++) {
            final int loop = i;
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                double ratio = new HelloWorldJni().parallel();
                long time = System.currentTimeMillis() - start;
                log.info("parallel: i: {}\ttime speedup: {}\ttime: {}", loop, ratio, time);
            });
        }
    }
}
