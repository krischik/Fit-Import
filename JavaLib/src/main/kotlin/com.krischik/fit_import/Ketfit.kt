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


/**
 * <p>
 * </p>
 *
 * <pre>
 * Datum     ;Zeit ;Dauer    ;Watt;Puls;u/min;kcal;km;°
 * 02.02.2014;18:42;00:40:00 ;88  ;118 ;48   ;294 ;6 ;-;
 * 03.02.2014;18:29;00:40:00 ;88  ;94  ;61   ;294 ;6 ;-;
 * </pre>
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 */

data class Ketfit(
   val start: java.util.Date,
   val end: java.util.Date,
   val watt: Int,
   val puls: Int,
   val uMin: Int,
   val kCal: Int,
   val km: Int,
   val ω: Int)
{
   /**
    * <p>Kettler measures km and GoogleFit uses m</p>
    */
   public val meter: Float
      get() = km.toFloat() * 1000.0f

   /**
    * <p>Steps per minute. One cycle is two steps.</p>
    */
   public val stepMin: Float
      get() = uMin * 2.0f

   /*
    * <p>Google fit stores the steps for cross trainer.
    * First we calculate the total revolutions from the average revolution per minute and the
    * the time of session.    * We consider one revolution to be one step.
    */
   public val steps: Int
      get() = (stepMin * durationInMinutes).toInt ()

   /**
    * <p>Training duration ins minutes</p>
    */
   public val durationInMinutes: Float
      get () = durationInSeconds / 60.0f

   /**
    * <p>Training duration ins seconds</p>
    */
   public val durationInSeconds: Float
      get () = (end.time - start.time).toFloat() / 1000.0f
}
// vim: set nowrap tabstop=8 shiftwidth=4 softtabstop=4 expandtab :
// vim: set textwidth=0 filetype=kotlin foldmethod=marker spell spelllang=en_gb :
