package net.devops.demo.jni.bindings;

import org.apache.commons.math3.util.FastMath;

public class MassiveComputation {

    static {
        NativeLibUtils.loadLibFromJar("MassiveComputation");
    }

    public native double[] cppOpenMp(double[] input);

    public native double[] cppSequential(double[] input);

    public double[] javaMath(double[] input) {
        int length = input.length;
        double[] result = new double[length];

        for (int i = 0; i < length; i++) {
            double temp1 = Math.pow(input[i], 0.66);
            double temp2 = 0.125 * 0.408 * (temp1 / (0.44 + temp1));
            double temp3 = Math.exp(-4860746 * temp1 * temp1 * 0.000001 * 0.000001 / Math.pow(2.23, temp2 / 10));
            double temp4 = 0.0000005237 * Math.pow(2.03, temp2 / 10);
            double temp5 = (1 - Math.exp(-temp2 * 0.000001 / temp4)) *
                    temp4 / (temp3 * 0.000001);
            double temp6 = temp5 * input[i] / input[i] * temp1 * temp3;
            double temp7 = temp5 * input[i] * (0.000001) * 12 * 1000;
            double temp8 = 0.00712 * Math.pow(temp5, -0.402) +
                    1 * 0.408 * (1 - temp6 / (0.44 + temp6)) * input[i] / input[i] * temp7 * (1 - temp7);
            result[i] = temp8 * 2;
        }

        return result;
    }

    public double[] javaFastMath(double[] input) {
        int length = input.length;
        double[] result = new double[length];

        for (int i = 0; i < length; i++) {
            double temp1 = FastMath.pow(input[i], 0.66);
            double temp2 = 0.125 * 0.408 * (temp1 / (0.44 + temp1));
            double temp3 = FastMath.exp(-4860746 * temp1 * temp1 * 0.000001 * 0.000001 / FastMath.pow(2.23, temp2 / 10));
            double temp4 = 0.0000005237 * FastMath.pow(2.03, temp2 / 10);
            double temp5 = (1 - FastMath.exp(-temp2 * 0.000001 / temp4)) *
                    temp4 / (temp3 * 0.000001);
            double temp6 = temp5 * input[i] / input[i] * temp1 * temp3;
            double temp7 = temp5 * input[i] * (0.000001) * 12 * 1000;
            double temp8 = 0.00712 * FastMath.pow(temp5, -0.402) +
                    1 * 0.408 * (1 - temp6 / (0.44 + temp6)) * input[i] / input[i] * temp7 * (1 - temp7);
            result[i] = temp8 * 2;
        }

        return result;
    }

}
