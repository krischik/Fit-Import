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
 * <pre>
 * "Date","Gewicht (kg)","Fettmasse (kg)","Fettfreier Anteil (kg)","Kommentare"
 * "2014-02-13 6:04 Uhr","94.34","26.81","68.43",""
 * "2014-02-12 6:09 Uhr","94.89","27.43","67.36",""
 * </pre>
 * @author martin
 * @version 1.0
 * @since 1.0
 */

data class Withings(
   val Time: java.util.Date,
   val Weight: Float,
   val Fat: Float,
   val No_Fat: Float,
   val Comment: String)

// vim: set nowrap tabstop=8 shiftwidth=4 softtabstop=4 expandtab :
// vim: set textwidth=0 filetype=kotlin foldmethod=marker spell spelllang=en_gb :
