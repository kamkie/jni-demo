package net.devops.demo.jni.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JniMain {

    public static void main(String[] args) throws Exception {
        final SinusGeneratorDemo sinusGeneratorDemo = new SinusGeneratorDemo();

        for (int i = 0; i < 10; i++) {
            log.info("loop {} \n\n\n", i);
            sinusGeneratorDemo.testCppSequential();
            sinusGeneratorDemo.testCppOpenMp();
            sinusGeneratorDemo.testJavaMath();
            sinusGeneratorDemo.testJavaFastMath();

            Thread.sleep(100);
        }
        sinusGeneratorDemo.shutdown();

        final MassiveComputationDemo massiveComputationDemo = new MassiveComputationDemo();
        for (int i = 0; i < 10; i++) {
            log.info("loop {} \n\n\n", i);
            massiveComputationDemo.testCppSequential();
            massiveComputationDemo.testCppOpenMp();
            massiveComputationDemo.testJavaMath();
            massiveComputationDemo.testJavaFastMath();

            Thread.sleep(100);
        }
        massiveComputationDemo.shutdown();
    }

}
