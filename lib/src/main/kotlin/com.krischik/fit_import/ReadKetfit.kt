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

import java.util.Calendar

/**
 * <p>
 *     Read ketfit file.
 * </p>
 *
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 */
public class ReadKetfit(val dataStream: java.io.InputStream) : AutoCloseable
{
   companion object
   {
      /**
       * <p>logging tag</p>
       */
      private val TAG = ReadKetfit::class.java.name
      /**
       * <p>logger</p>
       */
      private val logger = java.util.logging.Logger.getLogger (TAG);
      /**
       * <p>format of the start date<p>
       */
      private val Date_Format = java.text.SimpleDateFormat("dd.MM.yyyy");
      /**
       * <p>format of the start time. Strangely the start time has no seconds</p>
       */
      private val Time_Format = java.text.SimpleDateFormat("HH:mm");
      /**
       * <p>format of the duration. which has seconds</p>
       */
      private val Duration_Format = java.text.SimpleDateFormat("HH:mm:ss");
   } // object

   /**
    * <p>convenience constructor creating an reader from an file instance.
    */
   public constructor(file: java.io.File) :
   this(java.io.FileInputStream (file))
   {
      return
   }

   /**
    * <p>stream to read from</p>
    */
   val inputStream = java.io.InputStreamReader (dataStream);
   /**
    * <p>buffer to read lines from</p>
    */
   val reader = java.io.BufferedReader (inputStream);

   init
   {
      logger.entering(TAG, "ReadKetfit", dataStream)

      // Skip the fist 2 line. They are just the header.

      reader.readLine()
      reader.readLine()

      logger.exiting(TAG, "ReadKetfit")
   }

   /**
    * <p>read new dataset</p>
    *
    * @return null when no more records are availabie.
    */
   fun read(): Ketfit?
   {
      logger.entering(TAG, "read")

      val line = reader.readLine()

      val retval: Ketfit? = if (line != null)
      {
	 val fields = line.split(';')

	 val date = java.util.GregorianCalendar();
	 val time = java.util.GregorianCalendar();
	 val duration = java.util.GregorianCalendar();

	 date.time = Date_Format.parse(fields[0])
	 time.time = Time_Format.parse(fields[1])
	 duration.time = Duration_Format.parse(fields[2])

	 val start = java.util.GregorianCalendar (
	    /* year         => */date.get(Calendar.YEAR),
	    /* month        => */date.get(Calendar.MONTH),
	    /* dayOfMonth   => */date.get(Calendar.DAY_OF_MONTH),
	    /* hourOfDay    => */time.get(Calendar.HOUR_OF_DAY),
	    /* minute       => */time.get(Calendar.MINUTE),
	    /* second       => */time.get(Calendar.SECOND));
	 val end = start.clone() as java.util.GregorianCalendar

	 end.add (Calendar.HOUR, duration.get(Calendar.HOUR));
	 end.add (Calendar.MINUTE, duration.get(Calendar.MINUTE));
	 end.add (Calendar.SECOND, duration.get(Calendar.SECOND));

	 val watt = java.lang.Integer.parseInt (fields [3]);
	 val puls = java.lang.Integer.parseInt (fields [4]);
	 val uMin = java.lang.Integer.parseInt (fields [5]);
	 val kCal = java.lang.Integer.parseInt (fields [6]);
	 val km = java.lang.Integer.parseInt (fields [7]);

	 Ketfit(
	    start = start.time,
	    end = end.time,
	    watt = watt,
	    puls = puls,
	    uMin = uMin,
	    kCal = kCal,
	    km = km,
	    ω = 0)
      }
      else
      {
	 null
      } // if

      logger.exiting(TAG, "read", retval)
      return retval
   } // read

   /**
    * <p>close stream</p>
    */
   override fun close()
   {
      logger.entering(TAG, "close")

      reader.close ()
      inputStream.close ()

      logger.exiting(TAG, "close")
      return
   } // close
} // ReadKetfit

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=marker spell spelllang=en_gb :
