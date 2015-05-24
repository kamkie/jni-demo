package net.devops.demo.jni.client;

import lombok.extern.slf4j.Slf4j;
import net.devops.demo.jni.bindings.MassiveComputation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class MassiveComputationDemo {

    public static final int DATA_SIZE = 1_000_000;
    public static final int NUMBER_OF_ITERATIONS = 10;
    private final MassiveComputation massiveComputation = new MassiveComputation();
    private final ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(500);
    private final ExecutorService executorService = new ThreadPoolExecutor(50, 100, 100, TimeUnit.SECONDS, workQueue);
    private final double[] data;

    public MassiveComputationDemo() throws Exception {
        data = new double[DATA_SIZE];
        Future<?> future = executorService.submit(() -> {
            massiveComputation.cppOpenMp(data);
            massiveComputation.cppSequential(data);
            massiveComputation.javaMath(data);
            massiveComputation.javaFastMath(data);
        });
        future.get();
    }

    public void testCppOpenMp() throws Exception {
        long startTime = System.currentTimeMillis();
        log.debug("testCppOpenMp: start time= {}", startTime);

        List<Future> futures = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int loop = i;
            Future<?> submit = executorService.submit(() -> {
                long start = System.currentTimeMillis();
                double[] output = massiveComputation.cppOpenMp(data);
                long time = System.currentTimeMillis() - start;
                log.info("testCppOpenMp: i: {}\ttime: {}", loop, time);
            });
            futures.add(submit);
        }

        for (Future future : futures) {
            future.get();
        }

        long stopTime = System.currentTimeMillis();
        log.debug("testCppOpenMp: stop time={}", stopTime);
        log.info("testCppOpenMp: time={}\n", stopTime - startTime);
    }

    public void testCppSequential() throws Exception {
        long startTime = System.currentTimeMillis();
        log.debug("testCppSequential: start time= {}", startTime);

        List<Future> futures = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int loop = i;
            Future<?> submit = executorService.submit(() -> {
                long start = System.currentTimeMillis();
                double[] output = massiveComputation.cppSequential(data);
                long time = System.currentTimeMillis() - start;
                log.info("testCppSequential: i: {}\ttime: {}", loop, time);
            });
            futures.add(submit);
        }

        for (Future future : futures) {
            future.get();
        }

        long stopTime = System.currentTimeMillis();
        log.debug("testCppSequential: stop time={}", stopTime);
        log.info("testCppSequential: time={}\n", stopTime - startTime);
    }

    public void testJavaMath() throws Exception {
        long startTime = System.currentTimeMillis();
        log.debug("testJavaMath: start time= {}", startTime);

        List<Future> futures = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int loop = i;
            Future<?> submit = executorService.submit(() -> {
                long start = System.currentTimeMillis();
                double[] output = massiveComputation.javaMath(data);
                long time = System.currentTimeMillis() - start;
                log.info("testJavaMath: i: {}\ttime: {}", loop, time);
            });
            futures.add(submit);
        }

        for (Future future : futures) {
            future.get();
        }

        long stopTime = System.currentTimeMillis();
        log.debug("testJavaMath: stop time={}", stopTime);
        log.info("testJavaMath: time={}\n", stopTime - startTime);
    }

    public void testJavaFastMath() throws Exception {
        long startTime = System.currentTimeMillis();
        log.debug("testJavaFastMath: start time= {}", startTime);

        List<Future> futures = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int loop = i;
            Future<?> submit = executorService.submit(() -> {
                long start = System.currentTimeMillis();
                double[] output = massiveComputation.javaFastMath(data);
                long time = System.currentTimeMillis() - start;
                log.info("testJavaFastMath: i: {}\ttime: {}", loop, time);
            });
            futures.add(submit);
        }

        for (Future future : futures) {
            future.get();
        }

        long stopTime = System.currentTimeMillis();
        log.debug("testJavaFastMath: stop time={}", stopTime);
        log.info("testJavaFastMath: time={}\n", stopTime - startTime);
    }

    public void shutdown() throws Exception {
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

}
