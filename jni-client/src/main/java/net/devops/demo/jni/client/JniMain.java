package net.devops.demo.jni.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JniMain {

    public static void main(String[] args) throws Exception {
        final SinusGeneratorDemo sinusGeneratorDemo = new SinusGeneratorDemo();

        for (int i = 0; i < 10; i++) {
            log.info("loop {}", i);
            sinusGeneratorDemo.testCppSequential();
            sinusGeneratorDemo.testCppOpenMp();
            sinusGeneratorDemo.testJavaMath();
            sinusGeneratorDemo.testJavaFastMath();

            log.info("\n\n\n");
            Thread.sleep(1000);
        }
        sinusGeneratorDemo.shutdown();

        final MassiveComputationDemo massiveComputationDemo = new MassiveComputationDemo();
        for (int i = 0; i < 10; i++) {
            log.info("loop {}", i);
            massiveComputationDemo.testCppSequential();
            massiveComputationDemo.testCppOpenMp();
            massiveComputationDemo.testJavaMath();
            massiveComputationDemo.testJavaFastMath();

            log.info("\n\n\n");
            Thread.sleep(1000);
        }
        massiveComputationDemo.shutdown();
    }

}
