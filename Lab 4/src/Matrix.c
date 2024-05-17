#include <jni.h>
#include "Main.h"
#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int rows;
    int cols;
    int** matrix;
} Matrix;

Matrix* createMatrix(int rows, int cols) {
    Matrix* matrix = (Matrix*)malloc(sizeof(Matrix));
    matrix->rows = rows;
    matrix->cols = cols;
    matrix->matrix = (int**)malloc(rows * sizeof(int*));
    for (int i = 0; i < rows; i++) {
        matrix->matrix[i] = (int*)malloc(cols * sizeof(int));
    }
    return matrix;
}

JNIEXPORT void JNICALL Java_Main_freeMatrixC(JNIEnv *env, jobject obj, jlong ptr) {
    Matrix* matrix = (Matrix*)ptr;
    for (int i = 0; i < matrix->rows; i++) {
        free(matrix->matrix[i]);
    }
    free(matrix->matrix);
}

JNIEXPORT void JNICALL Java_Main_printMatrixC(JNIEnv *env, jobject obj, jlong ptr) {
    Matrix* matrix = (Matrix*)ptr;
    for (int i = 0; i < matrix->rows; i++) {
        for (int j = 0; j < matrix->cols; j++) {
            printf("%d ", matrix->matrix[i][j]);
        }
        printf("\n");
    }
}

JNIEXPORT jlong JNICALL Java_Main_createMatrixC(JNIEnv *env, jobject obj, jobject target) {
    jclass matrixClass = (*env)->FindClass(env, "Matrix");
    jfieldID rowsField = (*env)->GetFieldID(env, matrixClass, "rows", "I");
    jfieldID colsField = (*env)->GetFieldID(env, matrixClass, "cols", "I");
    jint rows = (*env)->GetIntField(env, target, rowsField);
    jint cols = (*env)->GetIntField(env, target, colsField);
    Matrix* matrixC = createMatrix(rows, cols);

    jmethodID getID = (*env)->GetMethodID(env, matrixClass, "get", "(II)I");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            matrixC->matrix[i][j] = (*env)->CallIntMethod(env, target, getID, i, j);
        }
    }
    return (jlong)matrixC;
}

JNIEXPORT jlong JNICALL Java_Main_multiplyC(JNIEnv *env, jobject obj, jlong aPtr, jlong bPtr) {
    Matrix* a = (Matrix*)aPtr;
    Matrix* b = (Matrix*)bPtr;

    if (a->cols != b->rows) {
        printf("Incompatible dimensions\n");
        exit(EXIT_FAILURE);
    }

    Matrix* result = createMatrix(a->rows, b->cols);
    for (int i = 0; i < a->rows; i++) {
        for (int j = 0; j < b->cols; j++) {
            int sum = 0;
            for (int k = 0; k < a->cols; k++) {
                sum += a->matrix[i][k] * b->matrix[k][j];
            }
            result->matrix[i][j] = sum;
        }
    }
    return (jlong)result;
}
