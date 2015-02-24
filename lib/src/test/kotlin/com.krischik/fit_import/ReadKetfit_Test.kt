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

/**
 * <p>
 *     Read ketfit file.
 * </p>
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 */
public class ReadKetfit_Test : org.jetbrains.spek.api.Spek()
{
    /**
     * <p>logging tag</p>
     */
    private val TAG = javaClass<ReadKetfit_Test>().getName ()
    /**
     * <p>logger</p>
     */
    private val logger = java.util.logging.Logger.getLogger (TAG);

    {
        Init_Logger ()

        given ("a stream with header") {
            val testData = javaClass<ReadKetfit_Test>() getResourceAsStream "/kettfit_01_02_2015-28_02_2015.csv"

            on ("opening and closing the file") {
                val test = ReadKetfit (testData)

                test.close ()
                it ("should cause no error") {
                } // if
            } // on
        } // given

        given ("a stream with test data") {
            val testData = javaClass<ReadKetfit_Test>() getResourceAsStream "/kettfit_01_02_2015-28_02_2015.csv"

            on ("reading first data") {
                val test = ReadKetfit (testData)

                val info = test.read();

                test.close ()
                it ("should return the expexed value") {
                    assertThat(info, notNullValue())
                } // if
            } // on
        } // given
    }
} // ReadKetfit_Test

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
