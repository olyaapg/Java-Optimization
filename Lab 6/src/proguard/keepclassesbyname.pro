-injars myjar.jar
-outjars keepclassesbyname.jar

-libraryjars  <java.home>/jmods/java.base.jmod(!**.jar;!module-info.class)

-keep public class Main {
    public static void main(java.lang.String[]);
}

-keep public class SortSocks

-keep public class FirstClass

-keepclassmembers class FirstClass {
    *;
}

-keep public class SecondClass
