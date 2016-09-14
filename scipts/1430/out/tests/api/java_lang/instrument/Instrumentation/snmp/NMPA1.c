/*
 * @(#)NMPA1.c 08/01/29
 *
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

#include <stdio.h>
#include "jckjni.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL
Java_javasoft_sqe_tests_api_java_lang_instrument_Instrumentation_snmp_NMPA1_nmpa1
(JNIEnv* env, jobject obj) {
    return 1;
}

JNIEXPORT jint JNICALL
Java_javasoft_sqe_tests_api_java_lang_instrument_Instrumentation_snmp_NMPA1_wrappednmpa1
(JNIEnv* env, jobject obj) {
    return 11;
}

#ifdef __cplusplus
}
#endif
