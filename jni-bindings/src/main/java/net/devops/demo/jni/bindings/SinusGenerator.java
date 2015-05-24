package net.devops.demo.jni.bindings;

import org.apache.commons.math3.util.FastMath;

public class SinusGenerator {

    private double twoPi = FastMath.PI * 2;

    static {
        NativeLibUtils.loadLibFromJar("SinusGenerator");
    }

    public native double[] cppOpenMp(int size);

    public native double[] cppSequential(int size);

    public double[] javaMath(int size) {
        double[] result = new double[size];

        for (int i = 0; i < size; i++) {
            result[i] = Math.sin(twoPi * i / size);
        }

        return result;
    }

    public double[] javaFastMath(int size) {
        double[] result = new double[size];

        for (int i = 0; i < size; i++) {
            result[i] = FastMath.sin(twoPi * i / size);
        }

        return result;
    }

}
