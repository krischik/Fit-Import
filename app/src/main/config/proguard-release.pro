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

-dontwarn com.google.android.**

-keepattributes *Annotation*
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
-keep class android.support.test.runner.MultiDexTestRunner	{ *; }
-keep class com.android.test.runner.MultiDexTestRunner		{ *; }

########## Projekt ##########################################################

-keep class com.krischik.fit_import.GoogleFit 			{ *; }
-keep class com.krischik.fit_import.MainFragment_ 	        { *; }

# vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 noexpandtab :
# vim: set textwidth=0 filetype=cfg foldmethod=marker nospell :
