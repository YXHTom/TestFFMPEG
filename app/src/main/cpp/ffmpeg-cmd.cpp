#include <jni.h>
#include <string>

#include "android/log.h"



extern "C" {
#include "ffmpeg.h"
#include "libavutil/avutil.h"
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
#include "libavfilter/avfilter.h"
#include <libavcodec/jni.h>
}

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG  , "ffmpeg-lib", __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR  , "ffmpeg-lib", __VA_ARGS__)

extern "C"
JNIEXPORT jint JNICALL
Java_com_yy_linux_FFmpegCmd_run(JNIEnv *env, jclass type,jint cmdLen, jobjectArray cmd) {
    JavaVM *jvm = NULL;
    env->GetJavaVM(&jvm);
    av_jni_set_java_vm(jvm, NULL);


    jstring buf[cmdLen];
    char *argCmd[cmdLen] ;
    for (int i = 0; i < cmdLen; ++i) {
        buf[i] = static_cast<jstring>(env->GetObjectArrayElement(cmd, i));
        char *string = const_cast<char *>(env->GetStringUTFChars(buf[i], JNI_FALSE));
        argCmd[i] = string;
        LOGD("argCmd=%s",argCmd[i]);
    }
    int retCode = run(cmdLen, argCmd);
    LOGD("ffmpeg-invoke: retCode=%d",retCode);

    return retCode;
}

