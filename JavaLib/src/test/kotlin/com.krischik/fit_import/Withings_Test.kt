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
 * <p>
 *     Read ketfit file.
 * </p>
 *
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 */
public class Withings_Test : org.jetbrains.spek.api.Spek()
{
   /**
    * <p>logging tag</p>
    */
   private val TAG = ReadKetfit_Test::class.qualifiedName
   /**
    * <p>logger</p>
    */
   private val logger = java.util.logging.Logger.getLogger (TAG);

   init
   {
      Utilities.Init_Logger ()

      given ("A Withings instance")
      {
	 val withings = Withings (
	    /* Time    => */java.util.Date (),
	    /* weight  => */100.0f,
	    /* Fat     => */20.0f,
	    /* No_Fat  => */80.0f - 20.0f,
	    /* Comment => */"Withings_Test")
	 on ("getting the distance in m")
	 {
	    val test = withings.fatPercentage

	    it ("should be 20%")
	    {
	       assertThat(test, equalTo(20.0f))
	    } // it
	 } // on
      } // given
   } // init
} // ReadKetfit_Test

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
