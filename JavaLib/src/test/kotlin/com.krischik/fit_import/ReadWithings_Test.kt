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

import java.time.Month;

import org.exparity.hamcrest.date.DateMatchers.isInstant
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsNull.notNullValue
import org.hamcrest.number.IsCloseTo.closeTo
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * <p>
 *     Read ketfit file.:
 * </p>
 *
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 */
public class ReadWithings_Test : org.jetbrains.spek.api.Spek(
{
   /**
    * <p>logging tag</p>
    */
   val TAG = ReadWithings_Test::class.qualifiedName
   /**
    * <p>logger</p>
    */
   val logger = java.util.logging.Logger.getLogger (TAG);

      Utilities.Init_Logger ()

      given ("a stream with header")
      {
	 val testData = ReadWithings_Test::class.java.getResourceAsStream("/Withings.csv")

	 on ("opening and closing the file")
	 {
	    val test = ReadWithings (testData)

	    it ("should cause no error")
	    {
	       assertThat(test, notNullValue())
	    } // it

	    test.close ()
	 } // on
      } // given

      given ("a stream with test data")
      {
	 val testData = ReadWithings_Test::class.java.getResourceAsStream("/Withings.csv")

	 on ("reading two records data")
	 {
	    val test = ReadWithings (testData)
	    val record_1 = test.read()

	    it ("should return the expected 1st value")
	    {
	       assertThat(record_1, notNullValue())
	       assertThat(record_1?.time, isInstant (2014, Month.FEBRUARY, 13, 6, 4, 0, 0))
	       assertThat(record_1?.weight?.toDouble(), closeTo(94.34, 0.001))
	       assertThat(record_1?.fat?.toDouble(), closeTo(26.81, 0.001))
	       assertThat(record_1?.noFat?.toDouble(), closeTo(68.43, 0.001))
	       assertThat(record_1?.comment, equalTo(""))
	    } // it

	    val record_2 = test.read()

	    it ("should return the expected 2nd value")
	    {
	       assertThat(record_2, notNullValue())
	       assertThat(record_2?.time, isInstant (2014, Month.DECEMBER, 18, 19, 6, 0, 0))
	       assertThat(record_2?.weight?.toDouble(), closeTo(96.77, 0.001))
	       assertThat(record_2?.fat?.toDouble(), closeTo(0.0, 0.001))
	       assertThat(record_2?.noFat?.toDouble(), closeTo(0.0, 0.001))
	       assertThat(record_2?.comment, equalTo(""))
	    } // it

	    test.close ()
	 } // on
      } // given
      given ("a stream with 2 rows of test data")
      {
	 val testData = ReadWithings_Test::class.java.getResourceAsStream("/Withings.csv")

	 on ("reading all data")
	 {
	    val test = ReadWithings (testData)
	    var recordCount = 0;

	    Read_Lines@ while (true)
	    {
	       val testRecord = test.read()

	       if (testRecord == null) break@Read_Lines

	       recordCount = recordCount + 1;
	       logger.log(java.util.logging.Level.FINE, "Read Record {1}: {2}", arrayOf (recordCount, testRecord))
	    } // when

	    it ("should return the 13 records")
	    {
	       assertThat(recordCount, equalTo(2))
	    } // it
	    test.close ()
	 } // on
      } // given

      // kotlin.test.fail ("unit test where executed")
}) // ReadWithings_Test

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
