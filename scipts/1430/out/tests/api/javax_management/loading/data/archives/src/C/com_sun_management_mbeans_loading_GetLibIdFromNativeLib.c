/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>
#include "jckjni.h"
#include "jmx_libid.h"

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_sun_management_mbeans_loading_GetLibIdFromNativeLib
 * Method:    getLibId
 * Signature: ()I;
 */
JNIEXPORT jint JNICALL Java_com_sun_management_mbeans_loading_GetLibIdFromNativeLib_getLibId(JNIEnv * env, jobject obj) {
	return JMX_LIB_ID;
}

#ifdef __cplusplus
}
#endif
