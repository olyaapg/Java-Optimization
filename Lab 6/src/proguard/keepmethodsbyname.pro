-injars myjar.jar
-outjars keepmethodsbyname.jar

-libraryjars  <java.home>/jmods/java.base.jmod(!**.jar;!module-info.class)

-keep public class Main {
    public static void main(java.lang.String[]);
}

# -keepclassmembers public class * {
-keepclasseswithmembers public class * {
    *** fifthMethod(...);
}

-keepclassmembers public class * {
    *** sort(...);
}
