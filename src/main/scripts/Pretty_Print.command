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

xmlpp.pl -t -s -n -e -z		\
    **/*-jar.xml		\
    **/*.launch			\
    **/*.server			\
    **/*application.xml		\
    **/*ra.xml			\
    **/*resources.xml		\
    **/*web.xml			\
    **/AndroidManifest.xml	\
    **/config.xml		\
    **/persistence.xml		\
    **/settings.xml		\
    **/src.xml			\
    **/site.xml			\
    **/web-fragment.xml		\
    *.xml			\
    **/pom.xml			\
    **/res/**/themes.xml	\
    **/res/drawable/*.xml	\
    **/res/layout*/*.xml	\
    **/res/menu*/*.xml		\
    **/res/xml/*.xml		\
    **/src/site/*.xml		\
    **/src/main/apk/*.xml

xmlpp.pl -n -e -z -s		\
    **/package.html		\
    **/strings.xml		\
    **/dimens.xml		\
    **/colors.xml		\
    **/styles.xml		\
    **/floats.xml		\
    **/integers.xml		\
    **/attrs.xml		\
    **/arrays.xml

############################################################ {{{1 ###########
# vim: set wrap tabstop=8 shiftwidth=4 softtabstop=4 noexpandtab :
# vim: set textwidth=0 filetype=zsh foldmethod=marker nospell :
