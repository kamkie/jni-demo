cmake_minimum_required(VERSION 3.2)
project(jni_demo_native)

set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/../../../target)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/../../../target)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/../../../target)

find_package (Threads)
find_package(Java REQUIRED)
FIND_PACKAGE(JNI REQUIRED)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -fopenmp -Wl,--kill-at -I\"C:/Program Files/Java/jdk1.8.0_45/include\" -I\"C:/Program Files/Java/jdk1.8.0_45/include/win32\"")

set(SOURCE_FILES net_devops_demo_jni_bindings_HelloWorldJni.cpp)

add_library(HelloWorldJni SHARED net_devops_demo_jni_bindings_HelloWorldJni.h net_devops_demo_jni_bindings_HelloWorldJni.cpp)
target_link_libraries (HelloWorldJni ${CMAKE_THREAD_LIBS_INIT})