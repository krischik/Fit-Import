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
 * initialize the logger framework. Do not use on Android as you won't have logging.properties there.
 */
fun Init_Logger()
{
    java.io.File (" build/test-results").mkdirs()

    val manager = java.util.logging.LogManager.getLogManager ()
    val properties = ReadKetfit_Test::class.java getResourceAsStream "/logging.properties"

    // Read log properties so that the class which we test can write to a log
    // file
    manager readConfiguration properties
} // Init_Logger

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=marker spell spelllang=en_gb :
