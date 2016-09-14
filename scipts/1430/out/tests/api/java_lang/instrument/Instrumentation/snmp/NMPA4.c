/*
 * @(#)NMPA4.c 08/01/29
 *
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

#include <stdio.h>
#include "jckjni.h"

#ifdef __cplusplus
extern "C" {
#endif

#ifndef JNI_ENV_ARG

#ifdef __cplusplus
#define JNI_ENV_ARG(x, y) y
#define JNI_ENV_PTR(x) x
#else
#define JNI_ENV_ARG(x,y) x, y
#define JNI_ENV_PTR(x) (*x)
#endif

#endif

JNIEXPORT jint JNICALL nmpa4(JNIEnv* env, jobject obj) {
    return 4;
}

JNIEXPORT jboolean JNICALL
Java_javasoft_sqe_tests_api_java_lang_instrument_Instrumentation_snmp_NMPA4_regMeth
(JNIEnv* env, jclass cls) {
    JNINativeMethod meth;

    meth.name      = (char*) "nmpa4";
    meth.signature = (char*) "()I";
    meth.fnPtr     = (void *) &nmpa4;

    if (JNI_ENV_PTR(env)->RegisterNatives(JNI_ENV_ARG(env, cls),
            (const JNINativeMethod*) &meth, 1) != 0) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

#ifdef __cplusplus
}
#endif
