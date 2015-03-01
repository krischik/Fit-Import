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
 * <p>
 * </p>
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 */
class Main_Activity_Test : android.test.ActivityInstrumentationTestCase2<MainActivity_>(javaClass<MainActivity_>())
{
   /**
    * Logging tag
    */
   private val TAG = javaClass<Main_Activity_Test>().getName()
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
   [android.test.suitebuilder.annotation.Smoke]
   [android.test.suitebuilder.annotation.SmallTest]
   fun test_00_Open()
   {
      android.util.Log.d (TAG, "+ test_00_Open")

      Solo?.sleep (Second (0.5f))

      android.util.Log.d (TAG, "- test_00_Open")
   } // test_00_Open
}

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
