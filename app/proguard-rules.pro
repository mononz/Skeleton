# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/jaredhall/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html


# +-------------------------------------------------------------------------------------------------
# | Mononz
# +-------------------------------------------------------------------------------------------------

-keep class com.mononz.skeleton.data.request.** { *; }
-dontwarn com.mononz.skeleton.data.request.**

-keep class com.mononz.skeleton.data.response.** { *; }
-dontwarn com.mononz.skeleton.data.response.**

-keep class org.xmlpull.v1.** { *; }


# +-------------------------------------------------------------------------------------------------
# | Coroutines - https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/resources/META-INF/proguard/coroutines.pro
# +-------------------------------------------------------------------------------------------------

# ServiceLoader support
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}

# Most of volatile fields are updated with AFU and should not be mangled
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}


# +-------------------------------------------------------------------------------------------------
# | Crashlytics - https://docs.fabric.io/android/crashlytics/dex-and-proguard.html
# +-------------------------------------------------------------------------------------------------

-assumenosideeffects class android.util.Log {
    public static int d(...);
}

-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**


# +-------------------------------------------------------------------------------------------------
# | FIREBASE - https://github.com/firebase/quickstart-android/blob/master/analytics/app/proguard-rules.pro
# +-------------------------------------------------------------------------------------------------

-keepattributes EnclosingMethod
-keepattributes InnerClasses


# +-------------------------------------------------------------------------------------------------
# | Okhttp - https://github.com/square/okhttp#r8--proguard
# +-------------------------------------------------------------------------------------------------

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform


# +-------------------------------------------------------------------------------------------------
# | Okio - https://github.com/square/okio/#r8--proguard
# +-------------------------------------------------------------------------------------------------

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*


# +-------------------------------------------------------------------------------------------------
# | ANDROID SVG
# +-------------------------------------------------------------------------------------------------

-dontwarn com.caverock.androidsvg.SVGImageView


# +-------------------------------------------------------------------------------------------------
# | GLIDE - https://github.com/bumptech/glide#proguard
# +-------------------------------------------------------------------------------------------------

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


# +-------------------------------------------------------------------------------------------------
# | WEBVIEW
# +-------------------------------------------------------------------------------------------------

#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}