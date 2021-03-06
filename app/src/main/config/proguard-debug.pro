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

-dontnote
-dontobfuscate
-dontskipnonpubliclibraryclasses
-dontusemixedcaseclassnames
-ignorewarnings
-printmapping map.txt
-printseeds seed.txt

########## Optimize #########################################################

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

########## Android ##########################################################

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.FragmentActivity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.support.v4.app.FragmentActivity
-keep public class * extends android.support.v7.app.ActionBarActivity
-keep public class * extends android.widget.TabHost.TabContentFactory

########## Projekt ##########################################################

-keep class com.krischik.fit_import.GoogleFit 			{ *; }
-keep class com.krischik.fit_import.MainFragment_ 	        { *; }

########## Android Test #####################################################

-keepclassmembers public class ** {
   public *** Test_* ();
   public *** Test_* (...);
   public *** test_* ();
   public *** test_* (...);

   static *** *aroundBody* (...);
}

-keep @com.krischik.TestOnly class *

-keepclassmember public class * {
  @com.krischik.TestOnly *;
}

# vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 noexpandtab :
# vim: set textwidth=0 filetype=cfg foldmethod=marker nospell :
