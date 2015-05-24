package net.devops.demo.jni.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JniMain {

    public static void main(String[] args) throws Exception {
        SinusGeneratorDemo sinusGeneratorDemo = new SinusGeneratorDemo();

        for (int i = 0; i < 1000; i++) {
            log.info("loop {} \n\n\n", i);
            sinusGeneratorDemo.testCppSequential();
            sinusGeneratorDemo.testCppOpenMp();
            sinusGeneratorDemo.testJavaMath();
            sinusGeneratorDemo.testJavaFastMath();

            Thread.sleep(1000);
        }
    }

}
