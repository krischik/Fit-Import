::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: {{{1 :::::::::::
:: Copyright © 2005 … 2006  Martin Krischik
::
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
:: $Revision: 6102 $
:: $Date: 2013-05-16 11:27:40 +0200 (Do, 16. Mai 2013) $
:: $Id: Pretty_Print.cmd 6102 2013-05-16 09:27:40Z krischik $
:: $HeadURL: svn+ssh://krischik@svn.code.sf.net/p/uiq3/code/trunk/Java/src/main/scripts/Pretty_Print.cmd $
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: }}}1 :::::::::::

@ECHO OFF

IF NOT "%@eval[2 + 2]%" == "4" ( echo ^e[42mYou need TakeCommand [http://www.jpsoft.com] to execute this batch file.^e[m & EXIT /B 1)

SETLOCAL
    FUNCTION         CygPath=`%@ExecStr[%[CygWin_Home]\bin\cygpath --unix %1]`
    SET	    nodosfilewarning=true
    SET		   BatchPath=%@CygPath[%@PATH[%_BATCHNAME]]
    ALIAS             XML_PP=%[CygWin_Home]\bin\perl.exe %[BatchPath]xmlpp.pl
    ALIAS	     Java_PP=%@PATH[%_BATCHNAME]\jacobe.exe -cfg=%@PATH[%_BATCHNAME]Allman-Style.cfg -overwrite -nobackup

    DO I IN /S *-jar.xml *.launch *.server *application.xml *ra.xml *resources.xml *web.xml AndroidManifest.xml config.xml persistence.xml pom.xml settings.xml src.xml web-fragment.xml site.xml
	ECHO %[_cwd]\%[I]
	XML_PP -t -s -n -e -z %@CygPath[%[I]]
    ENDDO
    DO I IN res\drawable\*.xml res\layout-*\*.xml res\layout\*.xml res\menu\*.xml res\xml\*.xml src\main\apk\*.xml
	ECHO %[_cwd]\%[I]
	XML_PP -t -s -n -e -z %@CygPath[%[I]]
    ENDDO
    DO I IN /D"res" /S themes.xml
	ECHO %[_cwd]\%[I]
	XML_PP -t -s -n -e -z %@CygPath[%[I]]
    ENDDO
    DO I IN /S package.html
	ECHO %[_cwd]\%[I]
	XML_PP -n -e -z %@CygPath[%[I]]
    ENDDO
    DO I IN /D"res" /S strings.xml dimens.xml colors.xml styles.xml intergers.xml attrs.xml arrays.xml
	ECHO %[_cwd]\%[I]
	XML_PP -n -e -z %@CygPath[%[I]]
    ENDDO

    ::IFF ISDIR src/main/java THEN
	::Java_PP src\main\java
	::ERASE /S /E src\main\java\*.jacobe
    ::ENDIFF
    ::IFF ISDIR src/test/java THEN
	::Java_PP src\test\java
	::ERASE /S /E src\test\java\*.jacobe
    ::ENDIFF
ENDLOCAL
QUIT 0

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: {{{1 :::::::::::
:: vim: set wrap tabstop=8 shiftwidth=4 softtabstop=4 noexpandtab :
:: vim: set textwidth=0 filetype=btm foldmethod=marker nospell :
