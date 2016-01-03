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
public class Ketfit_Test : org.jetbrains.spek.api.Spek()
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

      given ("A Ketfit instance")
      {
	 val ketfit = Ketfit (
	    /* start  => */java.util.Date (),
	    /* end    => */java.util.Date (),
	    /* Watt   => */130,
	    /* puls   => */160,
	    /* Umin   => */70,
	    /* kCal   => */500,
	    /* km     => */3,
	    /* ω      => */0)

	 on ("getting the distance in m")
	 {
	    val test = ketfit.getMeter()

	    it ("should be 1000 times the distance in km")
	    {
	       assertThat(test, equalTo(3000.0f))
	    } // it
	 } // on
      } // given
   } // init
} // ReadKetfit_Test

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
