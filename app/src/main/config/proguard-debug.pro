# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /opt/local/share/java/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes *Annotation*

-keep public class com.krischik.fit_import.GoogleFit { *; }

########## Android Test #########################################################

-keepclassmembers public class ** {
   public *** Test_* ();
   public *** Test_* (...);
   public *** test_* ();
   public *** test_* (...);
}

-keep @com.krischik.TestOnly class *

-keepclassmember public class * {
  @com.krischik.TestOnly *;
}

# vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 noexpandtab :
# vim: set textwidth=0 filetype=cfg foldmethod=marker nospell :