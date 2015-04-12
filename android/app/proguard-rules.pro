-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keep class retrofit.** { *; }
-keep class package.with.model.classes.** { *; }
-keepclassmembernames interface * {
    @retrofit.http.* <methods>;
}

-dontwarn rx.**

-dontwarn okio.**

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn retrofit.appengine.UrlFetchClient

-keepattributes Signature
-keep class com.example.sampleapplication.model.** { *; }