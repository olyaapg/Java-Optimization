javac -h . Main.java

gcc -c -g -I"C:\Program Files\Java\jdk-20\include" -I"C:\Program Files\Java\jdk-20\include\win32" Matrix.c -o Matrix.o
gcc -g -shared -o nativeLib.dll Matrix.o -Wl,--add-stdcall-alias
java -cp . -Djava.library.path="D:\Study\Java Optimization\Lab 4\src" Main

gcc -c -g -O1 -I"C:\Program Files\Java\jdk-20\include" -I"C:\Program Files\Java\jdk-20\include\win32" Matrix.c -o Matrix.o
gcc -g -shared -o nativeLib.dll Matrix.o -Wl,--add-stdcall-alias
java -cp . -Djava.library.path="D:\Study\Java Optimization\Lab 4\src" Main

gcc -c -g -O2 -I"C:\Program Files\Java\jdk-20\include" -I"C:\Program Files\Java\jdk-20\include\win32" Matrix.c -o Matrix.o
gcc -g -shared -o nativeLib.dll Matrix.o -Wl,--add-stdcall-alias
java -cp . -Djava.library.path="D:\Study\Java Optimization\Lab 4\src" Main

gcc -c -g -O3 -I"C:\Program Files\Java\jdk-20\include" -I"C:\Program Files\Java\jdk-20\include\win32" Matrix.c -o Matrix.o
gcc -g -shared -o nativeLib.dll Matrix.o -Wl,--add-stdcall-alias
java -cp . -Djava.library.path="D:\Study\Java Optimization\Lab 4\src" Main

gcc -c -g -Ofast -I"C:\Program Files\Java\jdk-20\include" -I"C:\Program Files\Java\jdk-20\include\win32" Matrix.c -o Matrix.o
gcc -g -shared -o nativeLib.dll Matrix.o -Wl,--add-stdcall-alias
java -cp . -Djava.library.path="D:\Study\Java Optimization\Lab 4\src" Main