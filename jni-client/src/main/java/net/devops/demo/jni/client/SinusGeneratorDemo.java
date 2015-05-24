package net.devops.demo.jni.client;

import lombok.extern.slf4j.Slf4j;
import net.devops.demo.jni.bindings.SinusGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class SinusGeneratorDemo {

    public static final int SIN_SIZE = 1_000_000;
    public static final int NUMBER_OF_ITERATIONS = 10;
    private final SinusGenerator sinusGenerator = new SinusGenerator();
    private final ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(500);
    private final ExecutorService executorService = new ThreadPoolExecutor(50, 100, 100, TimeUnit.SECONDS, workQueue);

    public SinusGeneratorDemo() throws Exception {
        Future<?> future = executorService.submit(() -> {
            sinusGenerator.cppOpenMp(100);
            sinusGenerator.cppSequential(100);
            sinusGenerator.javaMath(100);
            sinusGenerator.javaFastMath(100);
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
                double[] output = sinusGenerator.cppOpenMp(SIN_SIZE);
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
                double[] output = sinusGenerator.cppSequential(SIN_SIZE);
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
                double[] output = sinusGenerator.javaMath(SIN_SIZE);
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
                double[] output = sinusGenerator.javaFastMath(SIN_SIZE);
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
