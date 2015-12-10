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

import com.krischik.Second
import com.krischik.Minutes

/**
 * Created by krma1 on 08.12.15.
 * @author martin
 * @version 1.0
 * @since 1.0
 */
public class GoogleFIT_Test :
   android.test.ActivityInstrumentationTestCase2<MainActivity_>(MainActivity_::class.java)
{
   /**
    * Logging tag
    */
   private val TAG = GoogleFIT_Test::class.qualifiedName
   /**
    * Activity to test.
    */
   private var Activity: java.lang.ref.WeakReference<MainActivity>? = null
   /**
    * Instrumentation to control the test.
    */
   private var Instrument: android.app.Instrumentation? = null
   /**
    * Robotium helper instance
    */
   private var Solo: com.robotium.solo.Solo? = null

   /**
    * Randim number generator
    */
   private val Random = java.util.Random (java.util.Date ().time)

   /**
    * get the activity and the text view to test
    *
    * @see android.test.ActivityInstrumentationTestCase2#setUp ()
    */
   protected override fun setUp()
   {
      android.util.Log.d (TAG, "+ setUp")

      super.setUp ()

      Instrument = getInstrumentation ()
      Activity = java.lang.ref.WeakReference (getActivity ())

      Solo = com.robotium.solo.Solo (Instrument, Activity?.get ())

      android.util.Log.d (TAG, "> Instrument =" + Instrument)
      android.util.Log.d (TAG, "> Activity   =" + Activity?.get ())

      Solo?.sleep (Second (0.5f))

      android.util.Log.d (TAG, "- setUp")
   } // setUp

   /**
    * end the activity and free memory
    *
    * @see android.test.ActivityInstrumentationTestCase2#tearDown()
    */
   protected override fun tearDown()
   {
      android.util.Log.d (TAG, "+ tearDown")

      Activity?.clear ()
      Activity = null
      Instrument = null

      super.tearDown ()

      android.util.Log.d (TAG, "- tearDown")
   } // tearDown

   /**
    * Test opening the main activity (smoke test / release compatible version)
    */
   @android.test.suitebuilder.annotation.Smoke
   @android.test.suitebuilder.annotation.SmallTest
   fun test_00_Insert_Weight()
   {
      android.util.Log.d (TAG, "+ test_00_Insert_Weight")

      val activity = Activity?.get()

      com.krischik.test.Utilities.Async_Assert_True (
         Message = { "Application should connect to Google Fit" },
         Condition = { activity?.isConnected ?: false },
         Timeout =  Second(30.0f).toLong())

      val Weight = 80.0f + (Random.nextFloat() * 10.0f - 5.0f)
      val Withings = com.krischik.fit_import.Withings (
         /* Time    => */java.util.Date (),
         /* Weight  => */Weight,
         /* Fat     => */20.0f,
         /* No_Fat  => */Weight - 20.0f,
         /* Comment => */"Withings")
      val Google_Fit = activity?.getGoogle_Fit()
      Google_Fit?.Insert_Weight (Withings)

      android.util.Log.d (TAG, "- test_00_Insert_Weight")
   } // test_00_Insert_Weight
   /**
    * Test opening the main activity (smoke test / release compatible version)
    */
   @android.test.suitebuilder.annotation.Smoke
   @android.test.suitebuilder.annotation.SmallTest
   fun test_01_Insert_Training()
   {
      android.util.Log.d (TAG, "+ test_01_Insert_Training")

      val activity = Activity?.get()

      com.krischik.test.Utilities.Async_Assert_True (
         Message = { "Application should connect to Google Fit" },
         Condition = { activity?.isConnected ?: false },
         Timeout =  Second(30.0f).toLong())

      val Puls = 140 + (Random.nextInt (20) - 10)
      val End = java.util.Date ()
      val Start = java.util.Date (End.time - Minutes(30.0f))
      val Ketfit = Ketfit (
         /* Start  => */Start,
         /* End    => */End,
         /* Watt   => */130,
         /* Puls   => */Puls,
         /* Umin   => */70,
         /* kCal   => */500,
         /* km     => */6,
         /* ω      => */0)
      val Google_Fit = activity?.getGoogle_Fit()
      Google_Fit?.Insert_Training (Ketfit);

      android.util.Log.d (TAG, "- test_01_Insert_Training")
   } // test_01_Insert_Training
} // GoogleFIT_Test

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
