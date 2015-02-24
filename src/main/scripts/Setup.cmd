::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: {{{1 :::::::::::
::  Copyright © 2005 … 2012  Martin Krischik
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::  This program is free software; you can redistribute it and/or
::  modify it under the terms of the GNU General Public License
::  as published by the Free Software Foundation; either version 2
::  of the License, or (at your option) any later version.
::
::  This program is distributed in the hope that it will be useful,
::  but WITHOUT ANY WARRANTY; without even the implied warranty of
::  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
::  GNU General Public License for more details.
::
::  You should have received a copy of the GNU General Public License
::  along with this program; if not, write to the Free Software
::  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: $Author: krischik $
:: $Revision: 6762 $
:: $Date: 2015-01-16 15:19:00 +0100 (Fr, 16. Jan 2015) $
:: $Id: Setup.cmd 6762 2015-01-16 14:19:00Z krischik $
:: $HeadURL: svn+ssh://krischik@svn.code.sf.net/p/uiq3/code/trunk/Java/src/main/scripts/Setup.cmd $
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: }}}1 :::::::::::

@ECHO OFF

IF NOT "%@eval[2 + 2]%" == "4" ( echo ^e[42mYou need TakeCommand [http://www.jpsoft.com] to execute this batch file.^e[m & EXIT /B 1)

IFF NOT DIREXIST "%[LOCALAPPDATA]\Logs" THEN
    MKDIR /C "%[LOCALAPPDATA]\Logs"
ENDIFF

TITLE UIQ3

SET		opt=%@if[DEFINED opt,%[opt],C:\opt]
SET	       Work=%@if[DEFINED Work,%[Work],C:\Work]
SET	CygWin_Home=%@if[DEFINED CygWin_Home,%[CygWin_Home],C:\cygwin]
SET	       HOME=%[USERPROFILE]

SET		VIM=%[opt]\vim
SET	    JAVACMD=%[opt]\Java\jdk\1.7.0\bin\java.exe
SET	    M2_HOME=%[opt]\apache\maven\3.2.3
SET	   ANT_HOME=%[opt]\apache\ant\1.8.3
SET	   JDK_HOME=%[opt]\Java\jdk\1.7.0
SET	   SVN_HOME=%[opt]\Subversion\1.8
SET	  JAVA_HOME=%[opt]\Java\jdk\1.7.0
SET	  Workspace=%[Work]\workspaces\UIQ3
SET	 SCALA_HOME=%[opt]\scala\2.11.4
SET	 VIMRUNTIME=%[opt]\vim\vim74
SET	GRADLE_HOME=%[opt]\gradle\2.2.1
SET    ANDROID_HOME=%[opt]\android-sdk-windows
SET    ECLIPSE_HOME=%[opt]\eclipse\luna
SET   INTELLIJ_HOME=%[opt]\JetBrains\IDEA\14.0
SET   PROGUARD_HOME=%[opt]\Proguard\4.11

SET	  JAVA_OPTS=-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Xms128m -Xmx512m -XX:MaxPermSize=250m
SET	 MAVEN_OPTS=-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Xms128m -Xmx512m -XX:MaxPermSize=250m
SET	GRADLE_OPTS=-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Xms128m -Xmx512m -XX:MaxPermSize=250m
SET	       LANG=C.UTF-8

SET	     UIQ3_DEMO="false";
SET	  PROJECT_NAME="UIQ3"
SET	  HP45_VERSION=6.7.1
SET	  UIQ3_VERSION=6.7.1
SET	FX602P_VERSION=6.7.1
SET	FX603P_VERSION=5.3.1
SET	NIMBUS_VERSION=6.7.1
SET   INFORMER_VERSION=6.7.1
SET CALCULATOR_VERSION=6.7.1

SET MAVEN_DEPLOY=file:///Work/HomePage/uiq3/htdocs/Repository

SET PATH=%[JDK_HOME]\bin;%[PATH]
SET PATH=%[ANDROID_HOME]\tools;%[PATH]
SET PATH=%[ANDROID_HOME]\platform-tools;%[PATH]

DO I IN /T"; " /L FX-602P; FX-602P-Applet; FX-602P-Desktop; FX-602P-Droid; FX-603P; FX-603P-Droid; HP45; HP45-Droid; Informer
    IFF EXIST "%[PROJECT_HOME]\%[I]\src\main\scripts\Build.cmd" THEN
	ALIAS Build-%[I]=CALL "%[PROJECT_HOME]\%[I]\src\main\scripts\Build.cmd"
    ENDIFF
    IFF EXIST "%[PROJECT_HOME]\%[I]\src\main\scripts\Start.cmd" THEN
	ALIAS Start-%[I]=CALL "%[PROJECT_HOME]\%[I]\src\main\scripts\Start.cmd"
    ENDIFF
    IFF EXIST "%[PROJECT_HOME]\%[I]\src\main\scripts\Deploy.cmd" THEN
	ALIAS Deploy-%[I]=CALL "%[PROJECT_HOME]\%[I]\src\main\scripts\Deploy.cmd"
    ENDIFF
    IFF EXIST "%[PROJECT_HOME]\%[I]\src\main\scripts\Upload.cmd" THEN
	ALIAS Upload-%[I]=CALL "%[PROJECT_HOME]\%[I]\src\main\scripts\Upload.cmd"
    ENDIFF
    IFF EXIST "%[PROJECT_HOME]\%[I]\src\main\scripts\Start-Device.cmd" THEN
	ALIAS Start-Device-%[I]=CALL "%[PROJECT_HOME]\%[I]\src\main\scripts\Start-Device.cmd"
    ENDIFF
    IFF EXIST "%[PROJECT_HOME]\%[I]\src\main\scripts\Start-Emulator.cmd" THEN
	ALIAS Start-Emulator-%[I]=CALL "%[PROJECT_HOME]\%[I]\src\main\scripts\Start-Emulator.cmd"
    ENDIFF
    IFF EXIST "%[PROJECT_HOME]\%[I]\src\main\scripts\Debug-Device.cmd" THEN
	ALIAS Debug-Device-%[I]=CALL "%[PROJECT_HOME]\%[I]\src\main\scripts\Debug-Device.cmd"
    ENDIFF
ENDDO

ALIAS	    PP=CALL "%[PROJECT_HOME]\src\main\scripts\Pretty_Print.cmd"
ALIAS	   mvn=CALL "%[M2_HOME]\bin\mvn.bat"
ALIAS	 Maven=CALL "%[PROJECT_HOME]\src\main\scripts\Maven.cmd"
ALIAS	Commit=CALL "%[WORK]\UIQ3\Utilities\Exec" "Svn-Commit"
ALIAS	gradle=CALL "%[GRADLE_HOME]\bin\gradle.bat"
ALIAS Fix-Mime=CALL "%[WORK]\UIQ3\Utilities\Exec" "Svn-Mime"

CALL "X:\Google Drive\Secure\.keystore.cmd"

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: {{{1 :::::::::::
:: vim: set wrap tabstop=8 shiftwidth=4 softtabstop=4 noexpandtab :
:: vim: set textwidth=0 filetype=btm foldmethod=marker nospell :
