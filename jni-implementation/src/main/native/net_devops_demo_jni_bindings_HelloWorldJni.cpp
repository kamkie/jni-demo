#define _USE_MATH_DEFINES

#include <iostream>
#include <cmath>
#include <math.h>
#include <time.h>
#include "vector"
#include "net_devops_demo_jni_bindings_HelloWorldJni.h"


using namespace std;

extern "C"
JNIEXPORT jdouble JNICALL Java_net_devops_demo_jni_bindings_HelloWorldJni_parallel
        (JNIEnv *env, jobject) {
    const int size = 10000000;
    std::vector<double> sinTable(size);

    clock_t start;
    double diff = 1;
    double diffParallel = 1;

    start = clock();
    for (int n = 0; n < size; ++n) {
        sinTable[n] = std::sin(2 * M_PI * n / size);
    }
    diff = (clock() - start) / (double) CLOCKS_PER_SEC;

    start = clock();
#pragma omp parallel for
    for (int n = 0; n < size; ++n) {
        sinTable[n] = std::sin(2 * M_PI * n / size);
    }
    diffParallel = (clock() - start) / (double) CLOCKS_PER_SEC;

    cout << "paraller: time: " << diff << "\ttime paraller: " << diffParallel << "\tsppedup ratio " <<
    diff / diffParallel << '\n';

    return diff / diffParallel;
}

extern "C"
JNIEXPORT jdoubleArray JNICALL Java_net_devops_demo_jni_bindings_HelloWorldJni_array(JNIEnv *env, jobject that, jintArray array) {
    jsize length = env->GetArrayLength(array);
    std::vector<double> result((unsigned long) length);

    clock_t start;
    double diff;
    double diffParallel;

    jint *input = env->GetIntArrayElements(array, 0);

    start = clock();
    for (int i = 0; i < length; i++) {
        result[i] = input[i] * 2;
    }
    diff = (clock() - start) / (double) CLOCKS_PER_SEC;

    start = clock();
#pragma omp parallel for
    for (int i = 0; i < length; i++) {
        result[i] = input[i] * 2;
    }
    diffParallel = (clock() - start) / (double) CLOCKS_PER_SEC;

    cout << "array: time: " << diff << "\ttime paraller: " << diffParallel << "\tsppedup ratio " <<
    diff / diffParallel << '\n';

    jdoubleArray output = env->NewDoubleArray(length);
    env->SetDoubleArrayRegion(output, 0, length, &result[0]);
    return output;
}