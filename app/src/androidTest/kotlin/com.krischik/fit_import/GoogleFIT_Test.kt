/********************************************************** {{{1 ***********
 *  Copyright © 2015 … 2016 "Martin Krischik" «krischik@users.sourceforge.net»
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

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo

/**
 * Created by krma1 on 08.12.15.
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 */
public class GoogleFIT_Test :
   android.test.ActivityInstrumentationTestCase2<MainActivity_>(MainActivity_::class.java)
{
   /**
    * Logging tag
    */
   private val TAG = com.krischik.Log.getLogTag(GoogleFIT_Test::class.javaClass);
   /**
    * Randim number generator
    */
   private val Random = java.util.Random (java.util.Date ().time)

   /**
    * Test opening the main activity (smoke test / release compatible version)
    */
   @android.test.suitebuilder.annotation.Smoke
   @android.test.suitebuilder.annotation.SmallTest
   fun test_00_Insert_Weight()
   {
      com.krischik.Log.d (TAG, "+ test_00_Insert_Weight")

      com.krischik.test.Utilities.asyncAssertTrue (
         message = { "Application should connect to Google Fit" },
         condition = { activity?.isConnected ?: false },
         timeout = com.krischik.test.Utilities.seconds(30.0f))

      val weight = 80.0f + (Random.nextFloat() * 20.0f - 10.0f)
      val fat = 20.0f + (Random.nextFloat() * 10.0f - 5.0f)
      val withings = com.krischik.fit_import.Withings (
         /* Time    => */java.util.Date (),
         /* weight  => */weight,
         /* Fat     => */fat,
         /* No_Fat  => */weight - fat,
         /* Comment => */"test_00_Insert_Weight")
      val Google_Fit = activity?.googleFit
      Google_Fit?.insertWeight (withings)

      com.krischik.Log.d (TAG, "- test_00_Insert_Weight")
   } // test_00_Insert_Weight

   /**
    * Test opening the main activity (smoke test / release compatible version)
    */
   @android.test.suitebuilder.annotation.Smoke
   @android.test.suitebuilder.annotation.SmallTest
   fun test_01_Insert_Training()
   {
      com.krischik.Log.d (TAG, "+ test_01_Insert_Training")

      com.krischik.test.Utilities.asyncAssertTrue (
         message = { "Application should connect to Google Fit" },
         condition = { activity?.isConnected ?: false },
         timeout = com.krischik.test.Utilities.seconds(30.0f))

      val puls = 140 + (Random.nextInt (20) - 10)
      val watt = 160 + (Random.nextInt (20) - 10)
      val uMin = 70 + (Random.nextInt (20) - 10)
      val kCal = 500 + (Random.nextInt (100) - 50)
      val km = 6 + (Random.nextInt (3) - 2)
      val end = java.util.Date ()
      val start = java.util.Date (end.time - com.krischik.test.Utilities.minutes(30.0f))
      val ketfit = Ketfit (
         /* start  => */start,
         /* end    => */end,
         /* Watt   => */watt,
         /* puls   => */puls,
         /* Umin   => */uMin,
         /* kCal   => */kCal,
         /* km     => */km,
         /* ω      => */0)
      val Google_Fit = activity?.googleFit
      Google_Fit?.insertTraining (ketfit);

      com.krischik.Log.d (TAG, "- test_01_Insert_Training")
   } // test_01_Insert_Training

   /**
    * Test opening the main activity (smoke test / release compatible version)
    */
   @android.test.suitebuilder.annotation.Smoke
   @android.test.suitebuilder.annotation.SmallTest
   fun test_02_Insert_Weights()
   {
      com.krischik.Log.d (TAG, "+ test_02_Insert_Weights")

      com.krischik.test.Utilities.asyncAssertTrue (
         message = { "Application should connect to Google Fit" },
         condition = { activity?.isConnected ?: false },
         timeout = com.krischik.test.Utilities.seconds(30.0f))

      val Google_Fit = activity?.googleFit
      val testData = GoogleFIT_Test::class.java.getResourceAsStream("/Withings.csv")
      val test = ReadWithings (testData)
      var recordCount = 0;

      Read_Lines@ while (true)
      {
         val testRecord = test.read()

         if (testRecord == null) break@Read_Lines

         recordCount = recordCount + 1;

         com.krischik.Log.v (TAG , "Read Record %1\$d: %2\$s", recordCount, testRecord)

         Google_Fit?.insertWeight (testRecord)
      } // when

      assertThat(recordCount, equalTo(11))
      test.close ()
      com.krischik.Log.d (TAG, "- test_02_Insert_Weights")
   } // test_02_Insert_Weights

   /**
    * Test opening the main activity (smoke test / release compatible version)
    */
   @android.test.suitebuilder.annotation.Smoke
   @android.test.suitebuilder.annotation.SmallTest
   fun test_03_Insert_Trainings()
   {
      com.krischik.Log.d (TAG, "+ test_03_Insert_Trainings")

      com.krischik.test.Utilities.asyncAssertTrue (
         message = { "Application should connect to Google Fit" },
         condition = { activity?.isConnected ?: false },
         timeout = com.krischik.test.Utilities.seconds(30.0f))

      val Google_Fit = activity?.googleFit
      val testData = GoogleFIT_Test::class.java.getResourceAsStream("/ketfit.csv")
      val test = ReadKetfit (testData)
      var recordCount = 0;

      Read_Lines@ while (true)
      {
         val testRecord = test.read()

         if (testRecord == null) break@Read_Lines

         recordCount = recordCount + 1;

         com.krischik.Log.v (TAG , "Read Record %1\$d: %2\$s", recordCount, testRecord)

         Google_Fit?.insertTraining (testRecord);
      } // when

      assertThat(recordCount, equalTo(16))
      test.close ()
      com.krischik.Log.d (TAG, "- test_02_Insert_Trainings")
   } // test_00_Insert_Weight
} // GoogleFIT_Test

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
