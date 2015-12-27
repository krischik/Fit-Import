/************************************************************ {{{1 ***********
 *  Copyright © 2007 … 2016  Martin Krischik
 * ***************************************************************************
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
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * ***************************************************************************
 *  $Author: krischik $
 *  $Revision: 6780 $
 *  $Date: 2015-05-03 21:53:59 +0200 (So, 03 Mai 2015) $
 *  $Id: Utilities.scala 6780 2015-05-03 19:53:59Z krischik $
 *  $HeadURL: svn+ssh://krischik@svn.code.sf.net/p/uiq3/code/trunk/Java/Calculator/Test/src/main/scala/net.sourceforge.uiq3/test/Utilities.scala $
 ************************************************************ }}}1 **********/

package com.krischik.test

import java.util.logging.Logger
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.beans.HasPropertyWithValue.hasProperty
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsNull.notNullValue
import org.hamcrest.collection.IsCollectionWithSize.hasSize

import kotlin.test.fail

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
    * make sleep command more readable
    * @param seconds time in seconds
    * @return time in milli seconds
    */
   public fun seconds(seconds: Float): Long = (seconds * 1000).toLong ()

   /**
    * make sleep command more readable
    * @param minutes time in minutes
    * @return time in milli seconds
    */
   public fun minutes(minutes: Float) :Long = (minutes * 1000 * 60).toLong ()

   /**
    * return an unix timestamp as string
    *
    * @return
    *   string representing the current time as unix timestamp
    */
   public fun unixTimestamp() = java.util.Date ().time / 1000L

   /**
    * initialize the logger framework. Do not use on Android as you won't have logging.properties there.
    */
   public fun initLogger()
   {
      java.io.File ("target/surefire-reports").mkdirs ()

      val manager = java.util.logging.LogManager.getLogManager()
      val properties = javaClass.getResourceAsStream("/logging.properties")

      // Read log properties so that the class which we test can write to a log
      // file
      manager.readConfiguration(properties)
   } // Init_Logger

   /**
    * Asserted the message count. It there are two many or to few messages then
    * output the messages and fail the test.
    *
    * @param expected
          * message count expected
    * @param actual
          * actual messages
    */
   fun assertMessages(
      expected: Int, actual: List <String>)
   {
      if (expected != 0 && actual.isEmpty())
      {
	 fail ("An expected error message was not reported.")
      }
      else if (expected != actual.size)
      {
	 Utilities.logger.log (java.util.logging.Level.INFO, actual.toString())

         assertThat(actual, hasSize (expected))
      } // if
   } // Assert_Messages

   /**
    * <p>a short instance id when toString gived an to extensive result
    * @param Instance instance
    * @return Name and hash code
    */
   public fun instanceId(Instance: Any?) =
      if (Instance == null)
      {
	 "instance is null"
      }
      else
      {
	 Instance.javaClass.name + ":" + Integer.toHexString (Instance.hashCode ())
      }

   /**
    * checks if the condition becomes true within the given time frame
    *
    * @param message
    * Message to show when the assertion fails
    * @param condition
    * Condition to check. Should be pure (free of side effects as it is called mutliple times
    * @param timeout
    * time after which the test will fail (milli sec)
    * @param wait
    * Wait time between tests
    */
//   {
//      () -> "Not true after timeout"
//   }
   public tailrec fun asyncAssertTrue(
      message: () -> String,
      condition: () -> Boolean,
      timeout: Long = seconds (10.0f),
      wait: Long = seconds (0.5f))
   {
      // Utilities.logger.entering (
      // Utilities.TAG, "Async_Assert_True Timeout", Array (Message, Timeout, Wait))

      if (condition ())
      {
	 return
      }
      else if (timeout < 0)
      {
	 fail (message ())
      }
      else
      {
	 Thread.sleep (wait)
	 asyncAssertTrue (message, condition, timeout - wait, wait)
      } // if
   } // Async_Assert_True
   /**
    *
    * checks if the condition becomes false within the given time frame
    *
    * @param message
    * Message to show when the assertion fails
    * @param condition
    * Condition to check. Should be pure (free of side effects as it is called mutliple times
    * @param timeout
    * time after which the test will fail (milli sec)
    * @param wait
    * Wait time between tests
    */
    public fun asyncAssertFalse(
      message: () -> String,
      condition: () -> Boolean,
      timeout: Long = seconds (10.0f),
      wait: Long = seconds (0.5f))
   {
      asyncAssertTrue (message, {!condition ()}, timeout, wait)
   } // Async_Assert_False
   /**
    * test that all elements inside an array are not null
    *
    * @param Message message to display
    * @param testArray array to test
    */
    public fun <T> assertArrayNoneNull(
      testArray: Array  <T>)
   {
      testArray.forEach (
      {
	 Element: T ->
	    assertThat (Element, notNullValue ())
      }) // foreach
   } // Assert_Array_None_Null

   /**
    * Asserted with trace. Just replace the == for a , at test which make problems.
    */
   public fun <Element_Type> assert (value: Array <Element_Type>, expected: Array <Element_Type>)
   {
      if (! (value == expected))
      {
	 // Utilities.logger.log (
	 //   java.util.logging.Level.WARNING,
	 //   "LOG01517" +
	 //      "\nValue    = {0}" +
	 //      "\nExpected = {1}",
	 //   Value.toString (), Expected.toString ())
	    fail()
      } // if
   } // assert

   /**
    * Convert an Stream object to a string reader
    *
    * @param stream
    *    xml root element
    * @return
    *    a string reader with the xml text
    */
   public fun toReader(@NotNull stream: java.io.InputStream) = java.io.InputStreamReader (stream, "UTF-8")

} // Utilities

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=scala foldmethod=syntax spell spelllang=en_gb :
