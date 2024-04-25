javac -h . HelloWorldJNI.java
gcc -c -g -I"C:\Program Files\Java\jdk-20\include" -I"C:\Program Files\Java\jdk-20\include\win32" HelloWorldJNI.c -o HelloWorldJNI.o
gcc -g -shared -o native.dll HelloWorldJNI.o -Wl,--add-stdcall-alias
java -cp . -Djava.library.path="D:\Study\Java Optimization\Lab 3\src" HelloWorldJNI