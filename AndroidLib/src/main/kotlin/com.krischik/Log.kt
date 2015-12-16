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

package com.krischik

/**
 * <p>
 * Project specific logger
 * </p>
 *
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @see android.util.Log
 */
public object Log
{
   /**
    * <p>
    * maximum tag length when {@link android.util.Log#isLoggable} is used
    * </p>
    */
   val Max_Tag_Length = 23;
   /**
    * <p>
    * log tag
    * </p>
    */
   val TAG = Log.getLogTag (Log.javaClass)

   /**
    * <p/>
    * debug log with additional parameters
    * <p/>
    *
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @see android.util.Log#d(String, String)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun d(tag: String, msg: String, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.DEBUG))
      {
         android.util.Log.d (tag, Log.format (msg, args));
      } // if

      return;
   } // d

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @param exception
    *           exception parameter
    * @see android.util.Log#d(String, String, Throwable)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun d( tag: String, msg: String, exception: Throwable, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.DEBUG))
      {
         android.util.Log.d (tag, Log.format (msg, args), exception);
      } // if

      return;
   } // d

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @see android.util.Log#e(String, String)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun e(tag: String, msg: String, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.ERROR))
      {
         android.util.Log.e (tag, Log.format (msg, args));
      } // if

      return;
   } // e

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @param exception
    *           exception parameter
    * @see android.util.Log#e(String, String, Throwable)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun e( tag: String, msg: String, exception: Throwable, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.ERROR))
      {
         android.util.Log.e (tag, Log.format (msg, args), exception);
      } // if

      return;
   } // e

   /**
    * <p>
    * formatiert eine nachricht mit parameters wenn diese gegeben sind. Sonst wird der string ungeändert
    * zurückgeliefert.
    * </p>
    *
    * @param msg
    *           nachricht
    * @param args
    *           parameter für die nachricht.
    * @return die formatierte nachricht.
    * @see String#format(String, Object...)
    */
   public fun format(msg: String, vararg args: Any): String
   {
      val retval = if (args.size > 0)
      {
         try
         {
            java.lang.String.format (msg, args);
         }
         catch (exception: java.util.IllegalFormatException)
         {
            android.util.Log.w (Log.TAG, "LOG01840: Error ignored for now, please fix ASAP)", exception);

            msg;
         } // try
      }
      else
      {
         msg;
      } // if

      return retval;
   } // format

   /**
    * <p>
    * creates a log Tag for the given class. It is ensured that the Log tag does not exceeds the allowed maximum of 23
    * characters used by{@link android.util.Log#isLoggable}
    * </p>
    *
    * @param clazz
    *           class name to create a log tag for
    * @return a log tag of maximum 23 characters.
    */
   @JvmStatic
   public fun getLogTag(clazz: java.lang.Class<out Any>): String
   {
      val name = clazz.getName ();
      val endIndex = name.length;
      val beginIndex = endIndex - Max_Tag_Length;
      val retval = if (endIndex > Max_Tag_Length)
      {
         String (
            /* value  => */name.toCharArray (),
            /* offset => */beginIndex,
            /* count  => */Max_Tag_Length);
      }
      else
      {
         name;
      } // if

      return retval;
   } // getLogTag

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @see android.util.Log#i(String, String)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun i(tag:String, msg: String, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.INFO))
      {
         android.util.Log.i (tag, Log.format (msg, args));
      } // if

      return;
   } // i

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @param exception
    *           exception parameter
    * @see android.util.Log#i(String, String, Throwable)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun i( tag: String, msg: String, exception: Throwable, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.INFO))
      {
         android.util.Log.i (tag, Log.format (msg, args), exception);
      } // if

      return;
   } // i

   /**
    * <p>
    * test of log message should be displayed
    * </p>
    *
    * @param tag
    *           log tag
    * @param level
    *           log level
    * @return true: show log message
    * @see android.util.Log#isLoggable(String, int)
    */
   @JvmStatic
   public fun isLoggable(tag: String, level: Int): Boolean
   {
      val retval = try
      {
         android.util.Log.isLoggable ("KrischikLog", level) || android.util.Log.isLoggable (tag, level);
      }
      catch (exception: java.lang.IllegalArgumentException)
      {
         android.util.Log.wtf (Log.TAG, "LOG01890: You are using the wrong log TAG!", exception);

         true;
      } // try

      return retval;
   } // isLoggable

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @see android.util.Log#v(String, String)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun v(tag:String , msg: String, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.VERBOSE))
      {
         android.util.Log.v (tag, Log.format (msg, args));
      } // if

      return;
   } // v

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @param exception
    *           exception parameter
    * @see android.util.Log#v(String, String, Throwable)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun v( tag: String, msg: String, exception: Throwable, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.VERBOSE))
      {
         android.util.Log.v (tag, Log.format (msg, args), exception);
      } // if

      return;
   } // v

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @see android.util.Log#w(String, String)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun w(tag:String, msg: String, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.WARN))
      {
         android.util.Log.w (tag, Log.format (msg, args));
      } // if

      return;
   } // w

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @param exception
    *           exception parameter
    * @see android.util.Log#w(String, String, Throwable)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun w( tag: String, msg: String, exception: Throwable, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.WARN))
      {
         android.util.Log.w (tag, Log.format (msg, args), exception);
      } // if

      return;
   } // w

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @see android.util.Log#wtf(String, String)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun wtf(tag:String, msg: String, vararg args: Any)
   {
      if (Log.isLoggable (tag, android.util.Log.ASSERT))
      {
         android.util.Log.wtf (tag, Log.format (msg, args));
      } // if

      return;
   } // wtf

   /**
    * @param tag
    *           logging tag
    * @param msg
    *           logging message (with {@link String#format(String, Object...)} tags
    * @param args
    *           additional parameters passed to {@link String#format(String, Object...)}
    * @param exception
    *           exception parameter
    * @see android.util.Log#wtf(String, String, Throwable)
    */
   @com.krischik.TestOnly
   @JvmStatic
   public fun wtf( tag: String, msg: String, exception: Throwable, vararg args: Any)
   {
      if (isLoggable (tag, android.util.Log.ASSERT))
      {
         android.util.Log.wtf (tag, Log.format (msg, args), exception);
      } // if

      return;
   } // wtf

} // Log
