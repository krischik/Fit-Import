#!/opt/local/bin/zsh
########################################################### {{{1 ###########
#   Copyright © 2005 … 2020  Martin Krischik
#
#   This program is free software; you can redistribute it and/or
#   modify it under the terms of the GNU General Public License
#   as published by the Free Software Foundation; either version 2
#   of the License, or (at your option) any later version.
#
#   This program is distributed in the hope that it will be useful,
#   but WITHOUT ANY WARRANTY; without even the implied warranty of
#   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#   GNU General Public License for more details.
#
#   You should have received a copy of the GNU General Public License
#   along with this program; if not, write to the Free Software
#   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
############################################################################
#  $Author: krischik $
#  $Revision: 7102 $
#  $Date: 2020-08-19 15:58:03 +0200 (Mi, 19. Aug 2020) $
#  $Id: Start-Vim.command 7102 2020-08-19 13:58:03Z krischik $
#  $HeadURL: svn+ssh://krischik@svn.code.sf.net/p/uiq3/code/trunk/Utilities/Start-Vim.command $
########################################################### }}}1 ###########

source ${0:h}/Setup.command

setopt Err_Exit;
setopt XTrace;

pushd ${PROJECT_HOME}
    gvim --servername "${PROJECT_NAME}"			    \
	1>~/Library/Logs/${PROJECT_NAME}-${0:t:r}.out	    \
	2>~/Library/Logs/${PROJECT_NAME}-${0:t:r}.err	    &

    sleep 5;

    gvim --servername "${PROJECT_NAME}"			    \
	--remote-send ":source Utilities/Functions.vim<CR>"
    gvim --servername "${PROJECT_NAME}"			    \
	--remote-send ":TlistToggle<CR>"
popd;

############################################################ {{{1 ###########
# vim: set nowrap tabstop=8 shiftwidth=4 softtabstop=4 noexpandtab :
# vim: set textwidth=78 filetype=zsh foldmethod=marker nospell :
