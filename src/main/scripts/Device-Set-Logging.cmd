::#!
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: {{{1 :::::::::::
:: Copyright © 2015 … 2016 "Martin Krischik" «krischik@users.sourceforge.net»
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: This program is free software: you can redistribute it and/or modify
:: it under the terms of the GNU General Public License as published by
:: the Free Software Foundation, either version 3 of the License, or
:: (at your option) any later version.
::
:: This program is distributed in the hope that it will be useful,
:: but WITHOUT ANY WARRANTY; without even the implied warranty of
:: MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
:: GNU General Public License for more details.
::
:: You should have received a copy of the GNU General Public License
:: along with this program.  If not, see http://www.gnu.org/licenses/
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: }}}1 :::::::::::
@ECHO OFF

IF NOT "%@eval[2 + 2]%" == "4" ( echo ^e[42mYou need TakeCommand [http://www.jpsoft.com] to execute this batch file.^e[m & EXIT /B 1)

SETLOCAL
    SET PATH=%PATH%;%SCALA_HOME%\bin

    OPTION //UTF8Output=yes
    CHCP 65001

    PUSHD %PROJECT_HOME%
	CALL scala				   ^
	    -D`file.encoding=UTF-8`		   ^
	    -classpath %[CALCULATOR_SCALASCRIPT]   ^
	    -save %~f0 %*
    POPD

    OPTION //UTF8Output=no
    CHCP 850

ENDLOCAL

GOTO :eof
::!#

import scala.sys.process._
import java.util.logging
import net.sourceforge.uiq3.Android._
import net.sourceforge.uiq3.Shell._

val batchOut = new java.io.BufferedWriter(
   new java.io.FileWriter ("src/main/scripts/Device-Set-Logging.bat"))

batchOut.write ("PUSHD %PROJECT_HOME%")
batchOut.newLine ()

def setLogging (device: String, tag: String, level: String) {
    val endIndex = tag.length ();
    var beginIndex = endIndex - 23;

    val trimTag = if (endIndex > 23) {
	 if (tag.charAt (beginIndex) == '.') {
	    beginIndex = beginIndex + 1;
	 }

	 tag.substring (beginIndex);
      }
      else {
	 tag;
      }

  Logger.log (logging.Level.INFO, "[Scala] Set “" + trimTag + "” to «" + level + "»")

  batchOut.write ("  adb -s " + device + " shell setprop log.tag." + trimTag + " " + level)
  batchOut.newLine ()

  (adb :: "-s" :: device :: "shell" :: "setprop" :: "log.tag." + trimTag :: level :: Nil).!
} // setLogging

forAllDevices {
   Device =>
      Logger.log (logging.Level.INFO, "[Scala] Setup «" + Device + "»")

      (adb :: "-s" :: Device :: "shell" :: "setprop" :: "debug.assert" :: "1" :: Nil).!

      // Logging für das gesammtsystem
      //
      setLogging (Device, "Main",									 "INFO")
      setLogging (Device, "KrischikLog",								 "INFO")

      //  Logging für einzelne Klassen
      //
      setLogging (Device, "com.krischik.fit_import.Application_Test",					 "VERBOSE")
      setLogging (Device, "com.krischik.fit_import.GoogleFIT_Test",					 "VERBOSE")
      setLogging (Device, "com.krischik.fit_import.Main_Activity_Test",					 "VERBOSE")
      
      // Logging des test system sind immer an
      //
      setLogging (Device, "com.krischik.fit_import.PackageKt", "VERBOSE")
} // foreach

batchOut.write ("POPD")
batchOut.newLine ()
batchOut.close ()

//////////////////////////////////////////////////////////// {{{1 ///////////
// vim: set wrap tabstop=8 shiftwidth=3 softtabstop=3 noexpandtab :
// vim: set textwidth=0 filetype=scala fileformat=dos foldmethod=marker nospell :
