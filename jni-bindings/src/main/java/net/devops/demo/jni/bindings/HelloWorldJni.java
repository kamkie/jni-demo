package net.devops.demo.jni.bindings;

public class HelloWorldJni {

    static {
        System.loadLibrary("libHelloWorldJni");
    }

    public native double parallel();

    public native double[] array(int[] index);

}
