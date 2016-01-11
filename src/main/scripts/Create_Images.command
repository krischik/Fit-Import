#!/opt/local/bin/zsh
########################################################### {{{1 ###########
#   Copyright (C) 2005,2006  Martin Krischik
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
#
#  $Revision: 6748 $
#  $Date: 2015-01-13 09:24:25 +0100 (Di, 13. Jan 2015) $
#
#  $Id: Pretty_Print.command 6748 2015-01-13 08:24:25Z krischik $
#  $HeadURL: svn+ssh://krischik@svn.code.sf.net/p/uiq3/code/trunk/Java/src/main/scripts/Pretty_Print.command $
########################################################### }}}1 ###########

setopt CSH_Null_Glob;

pushd ~/Work/Projects/Fit-Import/app/src/main/res
    ../../../../src/main/scripts/Create-IC-Launcher.scala   ../../../../Pictures/ic_launcher.png 
    ../../../../src/main/scripts/Create-IC-Menu.scala	    ../../../../Pictures/ic_button_kettler.png
    ../../../../src/main/scripts/Create-IC-Menu.scala	    ../../../../Pictures/ic_button_withings.png
popd

############################################################ {{{1 ###########
# vim: set wrap tabstop=8 shiftwidth=4 softtabstop=4 noexpandtab :
# vim: set textwidth=0 filetype=zsh foldmethod=marker nospell :
