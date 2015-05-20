package net.devops.demo.jni.bindings;

public class HelloWorldJni {

    static {
        NativeLibUtils.loadLibFromJar("HelloWorldJni");
    }

    public native double parallel();

    public native double[] array(int[] index);

}
