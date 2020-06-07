# +-------------------------------------------------------------------------------------------------
# | Test
# +-------------------------------------------------------------------------------------------------

-dontobfuscate
-ignorewarnings

-dontnote

-keepattributes *Annotation*

-keep class org.hamcrest.** {
   *;
}
-dontnote junit.framework.**
-dontnote junit.runner.**

-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn com.squareup.javawriter.JavaWriter
# Uncomment this if you use Mockito
#-dontwarn org.mockito.**

-keep class junit.** { *; }
-dontwarn junit.**

-keep class sun.misc.** { *; }
-dontwarn sun.misc.**

-keep class org.xmlpull.** { *; }
-dontwarn org.xmlpull.**