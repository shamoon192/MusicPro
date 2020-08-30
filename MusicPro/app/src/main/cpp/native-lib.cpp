#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_shamoon_musicpro_data_CHandler_getAPIKey(JNIEnv *env, jobject thiz) {
    std::string api_key = "dad31ea71a1ba70ff0ddff8a73cbddfc";
    return env->NewStringUTF(api_key.c_str());
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_shamoon_musicpro_data_CHandler_getSharedSecret(JNIEnv *env, jobject thiz) {
    std::string shared_secret = "5a0fc34c1306926a7ba8e13b192ea715";
    return env->NewStringUTF(shared_secret.c_str());
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_shamoon_musicpro_data_CHandler_getResisteredTo(JNIEnv *env, jobject thiz) {
    std::string registered_to = "shamoon1992";
    return env->NewStringUTF(registered_to.c_str());
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_shamoon_musicpro_data_CHandler_getAPIAppName(JNIEnv *env, jobject thiz) {
    std::string app_name = "MusicPro";
    return env->NewStringUTF(app_name.c_str());
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_shamoon_musicpro_data_CHandler_getAPIURL(JNIEnv *env, jobject thiz) {
    // http://ws.audioscrobbler.com/2.0/
    std::string url = "http://ws.audioscrobbler.com/";
    return env->NewStringUTF(url.c_str());
}