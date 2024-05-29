-injars myjar.jar
-outjars keepmethodsbysignature.jar

-libraryjars  <java.home>/jmods/java.base.jmod(!**.jar;!module-info.class)

-keep public class Main {
    public static void main(...);
}

-keepclasseswithmembers public class * {
    public void *(java.util.List);
}