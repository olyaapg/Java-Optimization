#include <jni.h>
#include "HelloWorldJNI.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


// Функция, которая будет бесконечно аллоцировать память
JNIEXPORT void JNICALL Java_HelloWorldJNI_eatMemory(JNIEnv *env, jobject obj) {
    int** arrays = malloc(1000000 * sizeof(int*));
    int i = 0;
    while(1) {
        int* arr = malloc(1000000 * sizeof(int));
        arrays[i] = arr;
        i++;
    }
}

// Функция, которая выделяет 1000000 байт
JNIEXPORT void JNICALL Java_HelloWorldJNI_allocate1KB(JNIEnv *env, jobject obj, jobject target) {
    jclass beanClass = (*env)->FindClass(env, "Bean");
    jfieldID charArrayVar = (*env)->GetFieldID(env, beanClass , "charArrayVar", "[C");

    jcharArray newArray = (*env)->NewCharArray(env, 1000000);

    (*env)->SetObjectField(env, target, charArrayVar, newArray);
}

void divideByZero() {
    double d = 1 / 0;
    printf("%f", d);
}

void someFunc() {
    int v = 1;
    divideByZero();
    //abort();
    //const char *s = NULL;
    //printf( "%c\n", s[0] );
}

// Функция, которая вызывает другие функции и крашится
JNIEXPORT void JNICALL Java_HelloWorldJNI_callMethods(JNIEnv *env, jobject obj) {
    jstring str = (*env)->NewStringUTF(env, "someString");
    jboolean b = JNI_FALSE;
    jobject newBean = Java_HelloWorldJNI_createNewBean(env, obj, str, b);
    Java_HelloWorldJNI_beanToString(env, obj, newBean);
    Java_HelloWorldJNI_allocate1KB(env, obj, newBean);
    divideByZero();
}

// Функция, которая получает строку и возвращает её длину
JNIEXPORT jint JNICALL Java_HelloWorldJNI_getStringLen(JNIEnv *env, jobject obj, jstring str) {
    jclass stringClass = (*env)->FindClass(env, "java/lang/String");
    jmethodID methodID = (*env)->GetMethodID(env, stringClass, "length", "()I");

    return (*env)->CallIntMethod(env, str, methodID);
}

// Функция, которая создает новый Bean и заполняет его поля string и boolVar значениями из аргументов
JNIEXPORT jobject JNICALL Java_HelloWorldJNI_createNewBean(JNIEnv *env, jobject obj, jstring string, jboolean boolean) {
    jclass beanClass = (*env)->FindClass(env, "Bean");
    jobject newBean = (*env)->AllocObject(env, beanClass);

    jfieldID stringField = (*env)->GetFieldID(env, beanClass , "string", "Ljava/lang/String;");
    jfieldID boolVarField = (*env)->GetFieldID(env, beanClass , "boolVar", "Z");

    (*env)->SetObjectField(env, newBean, stringField, string);
    (*env)->SetBooleanField(env, newBean, boolVarField, boolean);

    return newBean;
}

// Функция, которая получает бин и присваивает новое значение в поле бин
JNIEXPORT void JNICALL Java_HelloWorldJNI_setBean(JNIEnv *env, jobject obj, jobject target, jobject newVal) {
    jclass beanClass = (*env)->FindClass(env, "Bean");
    jfieldID stringField = (*env)->GetFieldID(env, beanClass , "bean", "LBean;");
    (*env)->SetObjectField(env, target, stringField, newVal);
}

// Функция, которая вызывает у Bean'а метод toString() и возвращает результат
JNIEXPORT jstring JNICALL Java_HelloWorldJNI_beanToString(JNIEnv *env, jobject obj, jobject target) {
    jclass beanClass = (*env)->FindClass(env, "Bean");
    jmethodID methodID = (*env)->GetMethodID(env, beanClass, "toString", "()Ljava/lang/String;");

    jstring result = (jstring)(*env)->CallObjectMethod(env, target, methodID);
    return result;
}

// Структура, определяющая собаку - количество полных лет и имя
typedef struct {
    int age;
    char* name;
} Dog;

// Функция, которая аллоцирует структуру и возвращает указатель на неё
JNIEXPORT jlong JNICALL Java_HelloWorldJNI_allocateNativeStruct(JNIEnv *env, jobject obj) {
    Dog* dog = (Dog*)malloc(sizeof(Dog));
    dog->age = 5;
    dog->name = strdup("Bobik");
    return (jlong)dog;
}

JNIEXPORT jint JNICALL Java_HelloWorldJNI_getStructValueInt(JNIEnv *env, jobject obj, jlong ptr) {
    Dog* dog = (Dog*)ptr;
    return dog->age;
}

JNIEXPORT jstring JNICALL Java_HelloWorldJNI_getStructValueString(JNIEnv *env, jobject obj, jlong ptr) {
    Dog* dog = (Dog*)ptr;
    jstring str = (*env)->NewStringUTF(env, dog->name);
    return str;
}

JNIEXPORT void JNICALL Java_HelloWorldJNI_freeStruct(JNIEnv *env, jobject obj, jlong ptr) {
    Dog* dog = (Dog*)ptr;
    free(dog->name);
    free(dog);
    printf("The structure has been freed up");
}