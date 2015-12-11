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

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.notNullValue
import org.hamcrest.number.IsCloseTo.closeTo
import org.hamcrest.core.IsEqual.equalTo
import org.exparity.hamcrest.date.IsSameInstant.sameInstant
import org.exparity.hamcrest.date.Months

/**
 * <p>
 *     Read ketfit file.:
 * </p>
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 */
public class ReadWithings_Test : org.jetbrains.spek.api.Spek()
{
   /**
    * <p>logging tag</p>
    */
   private val TAG = ReadWithings_Test::class.qualifiedName
   /**
    * <p>logger</p>
    */
   private val logger = java.util.logging.Logger.getLogger (TAG);

   init {
      Init_Logger ()

      given ("a stream with header") {
	 val testData = ReadWithings_Test::class.java.getResourceAsStream("/Withings.csv")

	 on ("opening and closing the file") {
	    val test = ReadWithings (testData)

	    test.close ()
	    it ("should cause no error") {
	    } // if
	 } // on
      } // given

      given ("a stream with test data") {
	 val testData = ReadWithings_Test::class.java.getResourceAsStream("/Withings.csv")
	 val test = ReadWithings (testData)

	 on ("reading 1st data") {
	    val info = test.read();

	    it ("should return the expexed value") {
	       assertThat(info, notNullValue())
	       assertThat(info.time, sameInstant (2014, Months.FEBRUARY, 13, 6, 4, 0, 0))
	       assertThat(info.weight.toDouble(), closeTo(94.34, 0.001))
	       assertThat(info.fat.toDouble(), closeTo(26.81, 0.001))
	       assertThat(info.noFat.toDouble(), closeTo(68.43, 0.001))
	       assertThat(info.comment, equalTo(""))
	    } // if
	 } // on
	 on ("reading 2nd data") {
	    val info = test.read();

	    it ("should return the expexed value") {
	       assertThat(info, notNullValue())
	       assertThat(info.time, sameInstant (2014, Months.DECEMBER, 18, 19, 6, 0, 0))
	       assertThat(info.weight.toDouble(), closeTo(96.77, 0.001))
	       assertThat(info.fat.toDouble(), closeTo(0.0, 0.001))
	       assertThat(info.noFat.toDouble(), closeTo(0.0, 0.001))
	       assertThat(info.comment, equalTo(""))
	    } // if
	 } // on
	 // test.close ()
      } // given
   } // init
} // ReadWithigns_Test

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
