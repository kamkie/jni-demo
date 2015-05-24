package net.devops.demo.jni.client;

import lombok.extern.slf4j.Slf4j;
import net.devops.demo.jni.bindings.SinusGenerator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SinusGeneratorDemo {

    public static final int SIN_SIZE = 1_000_000;
    public static final int NUMBER_OF_ITERATIONS = 10;
    private SinusGenerator sinusGenerator = new SinusGenerator();

    public SinusGeneratorDemo() throws Exception {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(500);
        ExecutorService executorService = new ThreadPoolExecutor(50, 100, 100, TimeUnit.SECONDS, workQueue);
        executorService.execute(() -> {
            sinusGenerator.cppOpenMp(100);
            sinusGenerator.cppSequential(100);
            sinusGenerator.javaMath(100);
            sinusGenerator.javaFastMath(100);
        });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

    public void testCppOpenMp() throws Exception {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(500);
        ExecutorService executorService = new ThreadPoolExecutor(50, 100, 100, TimeUnit.SECONDS, workQueue);
        long startTime = System.currentTimeMillis();
        log.debug("testCppOpenMp: start time= {}", startTime);

        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int loop = i;
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                double[] sinus = sinusGenerator.cppOpenMp(SIN_SIZE);
                long time = System.currentTimeMillis() - start;
                log.info("testCppOpenMp: i: {}\ttime: {}", loop, time);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        long stopTime = System.currentTimeMillis();
        log.debug("testCppOpenMp: stop time={}", stopTime);
        log.info("testCppOpenMp: time={}\n", stopTime - startTime);
    }

    public void testCppSequential() throws Exception {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(500);
        ExecutorService executorService = new ThreadPoolExecutor(50, 100, 100, TimeUnit.SECONDS, workQueue);
        long startTime = System.currentTimeMillis();
        log.debug("testCppSequential: start time= {}", startTime);

        for (int i = 0; i < 10; i++) {
            final int loop = i;
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                double[] sinus = sinusGenerator.cppSequential(SIN_SIZE);
                long time = System.currentTimeMillis() - start;
                log.info("testCppSequential: i: {}\ttime: {}", loop, time);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        long stopTime = System.currentTimeMillis();
        log.debug("testCppSequential: stop time={}", stopTime);
        log.info("testCppSequential: time={}\n", stopTime - startTime);
    }

    public void testJavaMath() throws Exception {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(500);
        ExecutorService executorService = new ThreadPoolExecutor(50, 100, 100, TimeUnit.SECONDS, workQueue);
        long startTime = System.currentTimeMillis();
        log.debug("testJavaMath: start time= {}", startTime);

        for (int i = 0; i < 10; i++) {
            final int loop = i;
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                double[] sinus = sinusGenerator.javaMath(SIN_SIZE);
                long time = System.currentTimeMillis() - start;
                log.info("testJavaMath: i: {}\ttime: {}", loop, time);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        long stopTime = System.currentTimeMillis();
        log.debug("testJavaMath: stop time={}", stopTime);
        log.info("testJavaMath: time={}\n", stopTime - startTime);
    }


    public void testJavaFastMath() throws Exception {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(500);
        ExecutorService executorService = new ThreadPoolExecutor(50, 100, 100, TimeUnit.SECONDS, workQueue);
        long startTime = System.currentTimeMillis();
        log.debug("testJavaFastMath: start time= {}", startTime);

        for (int i = 0; i < 10; i++) {
            final int loop = i;
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                double[] sinus = sinusGenerator.javaFastMath(SIN_SIZE);
                long time = System.currentTimeMillis() - start;
                log.info("testJavaFastMath: i: {}\ttime: {}", loop, time);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        long stopTime = System.currentTimeMillis();
        log.debug("testJavaFastMath: stop time={}", stopTime);
        log.info("testJavaFastMath: time={}\n", stopTime - startTime);
    }
}
