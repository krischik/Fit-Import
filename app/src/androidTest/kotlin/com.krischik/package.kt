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
package com.krischik

/**
 * make sleep command more readable
 * @param Second time in seconds
 * @return time in milli seconds
 */
@suppress("NOTHING_TO_INLINE")
@inline
fun Second (Second: Float): Int = (Second * 1000).toInt()

/**
 * make sleep command more readable
 * @param Minutes time in minutes
 * @return time in milli seconds
 */
@suppress("NOTHING_TO_INLINE")
@inline
fun Minutes (Minutes: Float) :Int = (Minutes * 1000 * 60).toInt()


// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=marker spell spelllang=en_gb :
