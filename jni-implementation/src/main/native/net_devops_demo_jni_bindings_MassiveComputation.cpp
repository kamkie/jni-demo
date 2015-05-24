#define _USE_MATH_DEFINES

#include <iostream>
#include <cmath>
#include <math.h>
#include <time.h>
#include "vector"
#include "net_devops_demo_jni_bindings_MassiveComputation.h"


using namespace std;


#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     net_devops_demo_jni_bindings_MassiveComputation
 * Method:    cppOpenMp
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_net_devops_demo_jni_bindings_MassiveComputation_cppOpenMp
        (JNIEnv *env, jobject that, jdoubleArray array) {
    jsize length = env->GetArrayLength(array);
    vector<double> result((unsigned long) length);

    jdouble *input = env->GetDoubleArrayElements(array, 0);

#pragma omp parallel for
    for (int i = 0; i < length; i++) {
        double temp1 = std::pow(input[i], 0.66);
        double temp2 = 0.125 * 0.408 * (temp1 / (0.44 + temp1));
        double temp3 = std::exp(-4860746 * temp1 * temp1 * 0.000001 * 0.000001 / std::pow(2.23, temp2 / 10));
        double temp4 = 0.0000005237 * std::pow(2.03, temp2 / 10);
        double temp5 = (1 - std::exp(-temp2 * 0.000001 / temp4)) *
                       temp4 / (temp3 * 0.000001);
        double temp6 = temp5 * input[i] / input[i] * temp1 * temp3;
        double temp7 = temp5 * input[i] * (0.000001) * 12 * 1000;
        double temp8 = 0.00712 * std::pow(temp5, -0.402) +
                       1 * 0.408 * (1 - temp6 / (0.44 + temp6)) * input[i] / input[i] * temp7 * (1 - temp7);
        result[i] = temp8 * 2;
    }

    jdoubleArray output = env->NewDoubleArray(length);
    env->SetDoubleArrayRegion(output, 0, length, &result[0]);
    return output;
}
#ifdef __cplusplus
}
#endif

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     net_devops_demo_jni_bindings_MassiveComputation
 * Method:    cppSequential
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_net_devops_demo_jni_bindings_MassiveComputation_cppSequential
        (JNIEnv *env, jobject that, jdoubleArray array) {
    jsize length = env->GetArrayLength(array);
    vector<double> result((unsigned long) length);

    jdouble *input = env->GetDoubleArrayElements(array, 0);

    for (int i = 0; i < length; i++) {
        double temp1 = std::pow(input[i], 0.66);
        double temp2 = 0.125 * 0.408 * (temp1 / (0.44 + temp1));
        double temp3 = std::exp(-4860746 * temp1 * temp1 * 0.000001 * 0.000001 / std::pow(2.23, temp2 / 10));
        double temp4 = 0.0000005237 * std::pow(2.03, temp2 / 10);
        double temp5 = (1 - std::exp(-temp2 * 0.000001 / temp4)) *
                       temp4 / (temp3 * 0.000001);
        double temp6 = temp5 * input[i] / input[i] * temp1 * temp3;
        double temp7 = temp5 * input[i] * (0.000001) * 12 * 1000;
        double temp8 = 0.00712 * std::pow(temp5, -0.402) +
                       1 * 0.408 * (1 - temp6 / (0.44 + temp6)) * input[i] / input[i] * temp7 * (1 - temp7);
        result[i] = temp8 * 2;
    }

    jdoubleArray output = env->NewDoubleArray(length);
    env->SetDoubleArrayRegion(output, 0, length, &result[0]);
    return output;
}

#ifdef __cplusplus
}
#endif