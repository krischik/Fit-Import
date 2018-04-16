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

import org.exparity.hamcrest.date.DateMatchers.sameInstant
import org.exparity.hamcrest.date.Months
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsNull.notNullValue
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * <p>
 *     Read ketfit file.
 * </p>
 *
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 */
public class ReadKetfit_Test : org.jetbrains.spek.api.Spek(
{
   /**
    * <p>logging tag</p>
    */
   val TAG = ReadKetfit_Test::class.qualifiedName
   /**
    * <p>logger</p>
    */
   val logger = java.util.logging.Logger.getLogger (TAG);

      Utilities.Init_Logger ()

      given ("a stream with header")
      {
	 val testData = ReadKetfit_Test::class.java.getResourceAsStream("/ketfit.csv")

	 on ("opening and closing the file")
	 {
	    val test = ReadKetfit (testData)

	    it ("should cause no error")
	    {
	       assertThat(test, notNullValue())
	    } // it

	    test.close ()
	 } // on
      } // given

      given ("a stream with test data")
      {
	 val testData = ReadKetfit_Test::class.java.getResourceAsStream("/ketfit.csv")
	 val test = ReadKetfit (testData)

	 on ("reading first data")
	 {
	    val info = test.read();

	    it ("should return the expected value")
	    {
	       assertThat(info, notNullValue())
	       assertThat(info?.start, sameInstant (2014, Months.FEBRUARY, 2, 18, 42, 0, 0))
	       assertThat(info?.end, sameInstant (2014, Months.FEBRUARY, 2, 19, 22, 0, 0))
	       assertThat(info?.puls, equalTo(118))
	       assertThat(info?.uMin, equalTo(48))
	       assertThat(info?.kCal, equalTo(294))
	       assertThat(info?.watt, equalTo(88))
	       assertThat(info?.km, equalTo(6))
	       assertThat(info?.ω, equalTo(0))
	    } // it
	 } // on
	 on ("reading second data")
	 {
	    val info = test.read();

	    it ("should return the expected value")
	    {
	       assertThat(info, notNullValue())
	       assertThat(info?.start, sameInstant (2014, Months.FEBRUARY, 3, 18, 29, 0, 0))
	       assertThat(info?.end, sameInstant (2014, Months.FEBRUARY, 3, 19, 9, 0, 0))
	       assertThat(info?.puls, equalTo(94))
	       assertThat(info?.uMin, equalTo(61))
	       assertThat(info?.kCal, equalTo(294))
	       assertThat(info?.watt, equalTo(88))
	       assertThat(info?.km, equalTo(6))
	       assertThat(info?.ω, equalTo(0))
	    } // it
	 } // on
	 on ("closing the data stream")
	 {
	    test.close ()
	 } // on
      } // given
      given ("a stream with 13 rows of test data")
      {
	 val testData = ReadKetfit_Test::class.java.getResourceAsStream("/ketfit.csv")
	 on ("reading all data")
	 {
	    val test = ReadKetfit (testData)
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
	       assertThat(recordCount, equalTo(13))
	    } // it

	    test.close ()
	 } // on
      } // given
}) // ReadKetfit_Test

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
