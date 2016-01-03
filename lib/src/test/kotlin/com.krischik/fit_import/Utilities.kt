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
import org.hamcrest.core.IsNull.notNullValue

/**
 * Demonstration of the activity test
 *
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0 $Revision: 6780 $
 * @since 1.0
 */
object Utilities
{
   /**
    * Logging tag
    */
   val TAG = Utilities::class.qualifiedName
   /**
    * log instance
    */
   val logger = Logger.getLogger (TAG)

   /**
    * initialize the logger framework. Do not use on Android as you won't have logging.properties there.
    */
   fun Init_Logger()
   {
      java.io.File ("build/test-results").mkdirs()

      val manager = java.util.logging.LogManager.getLogManager ()
      val properties = PackageKt::class.java.getResourceAsStream("/logging.properties")

      assertThat(properties, notNullValue ())

      // Read log properties so that the class which we test can write to a log
      // file
      manager.readConfiguration (properties)
   } // Init_Logger
} // Utilities

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=marker spell spelllang=en_gb :
