#define _USE_MATH_DEFINES

#include <iostream>
#include <cmath>
#include <math.h>
#include <time.h>
#include "vector"
#include "net_devops_demo_jni_bindings_SinusGenerator.h"


using namespace std;

double twoPi = 2 * M_PI;

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     net_devops_demo_jni_bindings_SinusGenerator
 * Method:    cppOpenMp
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_net_devops_demo_jni_bindings_SinusGenerator_cppOpenMp
        (JNIEnv *env, jobject that, jint size) {
    std::vector<double> sinTable(size);

#pragma omp parallel for
    for (int n = 0; n < size; ++n) {
        sinTable[n] = std::sin(twoPi * n / size);
    }

    jdoubleArray output = env->NewDoubleArray(size);
    env->SetDoubleArrayRegion(output, 0, size, &sinTable[0]);
    return output;
}
#ifdef __cplusplus
}
#endif

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     net_devops_demo_jni_bindings_SinusGenerator
 * Method:    cppSequential
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_net_devops_demo_jni_bindings_SinusGenerator_cppSequential
        (JNIEnv *env, jobject that, jint size) {
    std::vector<double> sinTable(size);

    for (int n = 0; n < size; ++n) {
        sinTable[n] = std::sin(twoPi * n / size);
    }

    jdoubleArray output = env->NewDoubleArray(size);
    env->SetDoubleArrayRegion(output, 0, size, &sinTable[0]);
    return output;
}

#ifdef __cplusplus
}
#endif