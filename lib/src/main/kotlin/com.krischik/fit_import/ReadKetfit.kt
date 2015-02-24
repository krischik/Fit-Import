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
 *     Read ketfit file.
 * </p>
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 */
public class ReadKetfit (dataStream: java.io.InputStream) : AutoCloseable
{
    /**
     * <p>logging tag</p>
     */
    private val TAG = javaClass<ReadKetfit>().getName ()
    /**
     * <p>logger</p>
     */
    private val logger = java.util.logging.Logger.getLogger (TAG);

    /**
     * <p>stream to read from</p>
     */
    val inputStream = java.io.InputStreamReader (dataStream);
    /**
     * <p>buffer to read lines from</p>
     */
    val reader = java.io.BufferedReader (inputStream);

    {
        logger.entering(TAG, "ReadKetfit", dataStream)

        // Skip the fist 2 line. They are just the header.

        reader.readLine()
        reader.readLine()

        logger.exiting(TAG, "ReadKetfit")
    }

    /**
     * <p>read new dataset</p>
     */
    fun read(): Ketfit
    {
        logger.entering(TAG, "read")

        val line = reader.readLine()

        val retval = Ketfit(
                Datum = java.util.Date (),
                Dauer = 0,
                Watt = 0,
                Puls = 0,
                umin = 0,
                kcal = 0,
                km = 0,
                ω = 0)

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
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
