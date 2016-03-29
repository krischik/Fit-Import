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
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 */
public class GoogleFitHandler :
   android.os.Handler ()
{
   companion object
   {
      /**
       * Logging tag
       */
      private val TAG = com.krischik.Log.getLogTag (GoogleFitHandler::class.java)
   } // companion object

   public enum class Type
   {
      Withings,
      Ketfit
   }

   public class Command(
      val Type: Type,
      val File: java.io.File,
      val Messages: android.widget.TextView,
      val GoogleFit: GoogleFit
   )

   /**
    *
    * the import ketfit button has been clicked.
    */
   @hugo.weaving.DebugLog
   private fun doKetfit(Command: Command)
   {

      assert (Command.File.exists (), { "Should have been checked by the caller" })

      Command.Messages.append ("Start Import\n");

      val records = ReadKetfit(Command.File)

      Read_Records@ while (true)
      {
	 val record = records.read() ?: break@Read_Records

	 // if

	 com.krischik.Log.v(TAG, "Read Record: %1\$s", record)

	 try
	 {
	    Command.GoogleFit.insertTraining(record)
	 }
	 catch (exception: Exception)
	 {
	    Command.Messages.append(exception.message + '\n')
	    com.krischik.Log.e(TAG, "LOG00060:Insert error!", exception)
	 }
	 // try
      } // while

      return
   } // doKetfitButton

   /**
    *
    * the import withings button has been clicked.
    */
   @hugo.weaving.DebugLog
   private fun doWithings(Command: Command)
   {
      assert (Command.File.exists (), { "Should have been checked by the caller" })

      Command.Messages.append ("Start Import\n");

      val Records = ReadWithings (Command.File)

      Read_Records@ while (true)
      {
	 val record = Records.read() ?: break@Read_Records

	 com.krischik.Log.v(TAG, "Read record: %1\$s", record)

	 try
	 {
	    Command.GoogleFit.insertWeight(record)
	 }
	 catch (exception: Exception)
	 {
	    Command.Messages.append(exception.message + '\n')
	    com.krischik.Log.e(TAG, "LOG00060:Insert error!", exception)
	 } // try
      } // while

      Command.Messages.append ("Finished Import\n");

      return
   } // doWithingsButton

   /**
    * <p>receive and command and execute it.</p>
    */
   @hugo.weaving.DebugLog
   public override fun handleMessage(message: android.os.Message)
   {
      val Info: Any? = message.obj

      if (Info is Command)
      {
	 when (Info.Type)
	 {
	    Type.Ketfit   -> doKetfit (Info)
	    Type.Withings -> doWithings (Info)
	 } // when
      }
      else
      {
	 com.krischik.Log.wtf(TAG, "! Handler command is not of type Command.")
      } // if

      return
   } // handleMessage
} // GoogleFitHandler

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
