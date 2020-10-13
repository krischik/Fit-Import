#!/opt/local/bin/zsh
############################################################ {{{1 ###########
#  Copyright (C) 2007,2008  Martin Krischik
#############################################################################
#  This program is free software: you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  This program is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#
#  You should have received a copy of the GNU General Public License
#  along with this program.  If not, see <http://www.gnu.org/licenses/>.
#############################################################################
#  $Author: krischik $
#  $Revision: 7106 $
#  $Date: 2020-09-08 15:06:21 +0200 (Di, 08. Sep 2020) $
#  $Id: Start-Studio.command 7106 2020-09-08 13:06:21Z krischik $
#  $HeadURL: svn+ssh://krischik@svn.code.sf.net/p/uiq3/code/trunk/Utilities/Start-Studio.command $
############################################################ }}}1 ###########

source ${0:h}/Setup.command

setopt Err_Exit;
setopt XTrace;

"${STUDIO_HOME}/Contents/MacOS/studio"		    \
    "${PROJECT_HOME}"				    \
    1>~/Library/Logs/${PROJECT_NAME}-${0:t:r}.out   \
    2>~/Library/Logs/${PROJECT_NAME}-${0:t:r}.err   &

############################################################ {{{1 ###########
# vim: set wrap tabstop=8 shiftwidth=4 softtabstop=4 noexpandtab :
# vim: set textwidth=0 filetype=zsh foldmethod=marker nospell :
