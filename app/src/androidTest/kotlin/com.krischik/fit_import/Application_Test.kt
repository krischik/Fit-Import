/********************************************************** {{{1 ***********
 *  Copyright © 2015 "Martin Krischik" «krischik@users.sourceforge.net»
 ***************************************************************************
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/
 ********************************************************** }}}1 **********/
package com.krischik.fit_import

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class Application_Test : android.test.ApplicationTestCase<Application_>(Application_::class.java)
{
   companion object
   {
      /**
       * Logging tag
       */
      private val TAG = Application_Test::class.qualifiedName

      fun i(Text: String) = android.util.Log.i (TAG, Text)
   }

   /**
    * Test the zip file name
    */
   @hugo.weaving.DebugLog
   @android.test.suitebuilder.annotation.Smoke
   @android.test.suitebuilder.annotation.SmallTest
   fun test_02_Configuration()
   {
      val API = android.os.Build.VERSION.SDK_INT

      android.util.Log.d (TAG, "+ test_02_Configuration")

      i ("Build Confiuration")
      i ("> Debug                   = " + BuildConfig.DEBUG)

      val context = getContext()
      val resources = context.getResources()
      val Configuration = resources.getConfiguration()

      i ("API                       = " + API)
      i (" 1: fontScale             = " + Configuration.fontScale)
      i (" 1: locale                = " + Configuration.locale)
      i (" 1: mcc                   = " + Configuration.mcc)
      i (" 1: mnc                   = " + Configuration.mnc)
      i (" 1: keyboard              = " + Configuration.keyboard)
      i (" 1: keyboardHidden        = " + Configuration.keyboardHidden)
      i (" 1: touchscreen           = " + Configuration.touchscreen)
      i (" 1: navigation            = " + Configuration.navigation)
      i (" 1: orientation           = " + Configuration.orientation)

      if (API >= android.os.Build.VERSION_CODES.CUPCAKE)
      {
	 i (" 3: hardKeyboardHidden    = " + Configuration.hardKeyboardHidden)
      } // if
      if (API >= android.os.Build.VERSION_CODES.DONUT)
      {
	 i (" 4: screenLayout          = " + Configuration.screenLayout)
      } // if
      if (API >= android.os.Build.VERSION_CODES.ECLAIR)
      {
	 i (" 5: navigationHidden      = " + Configuration.navigationHidden)
      } // if
      if (API >= android.os.Build.VERSION_CODES.FROYO)
      {
	 i (" 8: uiMode                = " + Configuration.uiMode)
      } // if
      if (API >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2)
      {
	 i ("13: screenHeightDp        = " + Configuration.screenHeightDp)
	 i ("13: screenWidthDp         = " + Configuration.screenWidthDp)
	 i ("13: smallestScreenWidthDp = " + Configuration.smallestScreenWidthDp)
      } // if
      if (API >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1)
      {
	 i ("17: densityDpi            = " + Configuration.densityDpi)
      } // if

      android.util.Log.d (TAG, "- test_02_Configuration")
   } // test_02_Configuration
} // Application_Test

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
