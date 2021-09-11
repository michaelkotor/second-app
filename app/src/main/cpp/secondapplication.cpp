// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("secondapplication");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("secondapplication")
//      }
//    }

#include <string>
#include <jni.h>

int countWords(std::string x) {
    int num;
    if(x[0] == ' ') num = 0;
    else num = 1;


    for(unsigned int i = 0; i < x.size(); i++) {
        // if there is a space in the start
        if(x[0] == ' ') continue;

            // second condition makes sure that i don't count 2 spaces as 2 words
        else if(x[i] == ' ' && x[i - 1] != ' ') num++;
    }
    return num;
}

std::string jstring2string(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const auto stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char *)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}


extern "C"
JNIEXPORT jint JNICALL
Java_com_example_secondapplication_TextHandler_countChars(JNIEnv *env, jobject thiz, jstring text) {
    std::string toProcess = jstring2string(env, text);
    int result = 0;
    for(int i = 0; i < toProcess.length(); ++i) {
        if(toProcess[i] != ' ') {
            result++;
        }
    }
    return result;
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_secondapplication_TextHandler_countWords(JNIEnv *env, jobject thiz, jstring text) {
    std::string toWork = jstring2string(env, text);
    return countWords(toWork);
}