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
:: $Revision: 6750 $
:: $Date: 2015-01-15 11:49:50 +0100 (Do, 15. Jan 2015) $
:: $Id: Start-IntelliJ.cmd 6750 2015-01-15 10:49:50Z krischik $
:: $HeadURL: svn+ssh://krischik@svn.code.sf.net/p/uiq3/code/trunk/Java/src/main/scripts/Start-IntelliJ.cmd $
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: }}}1 :::::::::::

@ECHO OFF

IF NOT "%@Eval[2 + 2]%" == "4" (ECHO ^e[42mYou need TakeCommand [http://www.jpsoft.com] to execute this batch file.^e[m & EXIT /B 1)

SETLOCAL
    CALL %@Path[%_BatchName]\Setup.cmd

    SET IDEA_PROPERTIES=%[Workspace]\idea.properties

    START				^
	"UIQ3 - IntelliJ"		^
	%[INTELLIJ_HOME]\bin\idea64.exe	^
	%[Workspace]

ENDLOCAL
EXIT 0

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: {{{1 :::::::::::
:: vim: set wrap tabstop=8 shiftwidth=4 softtabstop=4 noexpandtab :
:: vim: set textwidth=0 filetype=btm foldmethod=marker nospell :
