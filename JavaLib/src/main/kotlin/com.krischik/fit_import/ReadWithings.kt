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
 *     Read ketfit file.
 * </p>
 *
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 */
public class ReadWithings(val dataStream: java.io.InputStream) : AutoCloseable
{
   companion object
   {
      /**
       * <p>logging tag</p>
       */
      private val TAG = ReadWithings::class.java.name
      /**
       * <p>logger</p>
       */
      private val logger = java.util.logging.Logger.getLogger (TAG);

      private val Date_Format = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
   } // object

   /**
    * <p>convenience constructor creating an reader from an file instance.
    */
   public constructor(file: java.io.File) :
   this(java.io.FileInputStream (file))
   {
      return
   } // constructor

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
      logger.entering(TAG, "ReadWithigns", dataStream)

      // Skip the fist line. They are just the header.

      reader.readLine()

      logger.exiting(TAG, "ReadWithigns")
   } // init

   /**
    * <p>Withings puts some stupid “Uhr” at the end of the string</p>
    */
   private fun truncateDate(inText: String): String
   {
      val length = Date_Format.toPattern().length
      var retval = inText.substring(0, length)

      return retval
   } // truncateDate

   /**
    * <p>Fields which have not be measured are keept empty and the stupid java parser won't parse empty string.</p>
    */
   private fun parseFloat(inText: String): Float
   {
      return if (inText.length > 0)
      {
         java.lang.Float.parseFloat (inText)
      }
      else
      {
         0.0f
      } // if
   } // parseFloat

   /**
    * <p>read new dataset</p>
    *
    * @return null when no more records are availabie.
    */
   fun read(): Withings?
   {
      logger.entering(TAG, "read")

      val line: String? = reader.readLine()
      val retval: Withings? = if (line != null)
      {
         val rawFields = line.split(',')
         val fields = rawFields.map { field ->  field.removeSurrounding ( "\"") }
         val date = Date_Format.parse(truncateDate(fields[0]))
         val weight = parseFloat (fields [1]);
         val fat = parseFloat (fields [2]);
         val noFat = parseFloat (fields [3]);

         Withings(
            time = date,
            weight = weight,
            fat = fat,
            noFat = noFat,
            comment = fields [4])
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
} // ReadWithigns

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=marker spell spelllang=en_gb :
