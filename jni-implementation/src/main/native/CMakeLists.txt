cmake_minimum_required(VERSION 3.0.2)
project(jni_demo_native)

set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/../../../target/lib)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/../../../target/lib)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/../../../target/lib)

find_package (Threads)
find_package(Java REQUIRED)
FIND_PACKAGE(JNI REQUIRED)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -fopenmp -I\"$ENV{JAVA_HOME}/include\" -I\"$ENV{JAVA_HOME}/include/win32\" -I\"$ENV{JAVA_HOME}/include/linux\"")

add_library(SinusGenerator SHARED net_devops_demo_jni_bindings_SinusGenerator.h net_devops_demo_jni_bindings_SinusGenerator.cpp)
target_link_libraries (SinusGenerator ${CMAKE_THREAD_LIBS_INIT})
SET_TARGET_PROPERTIES(SinusGenerator PROPERTIES PREFIX "")
