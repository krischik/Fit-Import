#!/bin/echo usage: source
########################################################### {{{1 ###########
#   Copyright © 2005 … 2013  Martin Krischik
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
#  $Revision: 6762 $
#  $Date: 2015-01-16 15:19:00 +0100 (Fr, 16. Jan 2015) $
#  $Id: Setup.command 6762 2015-01-16 14:19:00Z krischik $
#  $HeadURL: svn+ssh://krischik@svn.code.sf.net/p/uiq3/code/trunk/Java/src/main/scripts/Setup.command $
########################################################### }}}1 ###########

pushd "/Work"
    typeset -g -x WORK=$(pwd -P)
popd
typeset -g -x		     opt="/opt/local"
typeset -g -x	       Developer="/Applications/Developer"
typeset -g -x	    PROJECT_HOME="${WORK}/Projects/Fit-Import"
typeset -g -x	   MACPORTS_HOME="${opt}/share/java"

typeset -g -x		 M2_HOME="${MACPORTS_HOME}/maven3"
typeset -g -x		ANT_HOME="${MACPORTS_HOME}/apache-ant"
typeset -g -x		IDEA_JDK="/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk"
typeset -g -x		JDK_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home"
typeset -g -x		SVN_HOME="/opt/local"
typeset -g -x	       JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home"
typeset -g -x	       Workspace="${WORK}/Workspaces/Fit-Import"
typeset -g -x	      SCALA_HOME="${opt}/share/scala-2.11"
typeset -g -x	     STUDIO_HOME="${Developer}/Android Studio.app"
typeset -g -x	    ANDROID_HOME="/opt/android-sdk-macosx"
typeset -g -x	   INTELLIJ_HOME="${Developer}/IntelliJ IDEA CE.app"
typeset -g -x	   PROGUARD_HOME="${MACPORTS_HOME}"

typeset -g -x	    PROJECT_NAME="Fit-Import"
typeset -g -x	     FIT_VERSION=1.0.0

typeset -x -g	       JAVA_OPTS="-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Xms256m -Xmx1g"
typeset -x -g	      MAVEN_OPTS="-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Xms256m -Xmx1g"
typeset -x -g	     GRADLE_OPTS="-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Xms256m -Xmx1g"
typeset -x -g	    MAVEN_DEPLOY="file:///Work/HomePage/uiq3/htdocs/Repository"
typeset -x -g	   MAVEN_INSTALL="file:///Work/Repositories/Local/"
typeset -x -g -U -T CLASSPATH classpath ":"

path=("${JAVA_HOME}/bin" ${path})
path=("${SVN_HOME}/bin" ${path})
path=("${ANDROID_HOME}/tools" ${path})
path=("${ANDROID_HOME}/platform-tools" ${path})
fpath=(${PROJECT_HOME}/src/main/scripts ${fpath})

#mvn org.apache.maven.plugins:maven-dependency-plugin:2.1:get \
    #-DrepoUrl=http://download.java.net/maven/2/ \
    #-Dartifact=robo-guice:robo-guice:0.4-SNAPSHOT

typeset -x -g     CALCULATOR_VERSION=6.7.6
typeset -x -g CALCULATOR_SCALASCRIPT="${WORK}/Repositories/Local/net/sourceforge/uiq3/Calculator-Script/${CALCULATOR_VERSION}/Calculator-Script-${CALCULATOR_VERSION}.jar"

alias	 PP="${PROJECT_HOME}/src/main/scripts/Pretty_Print.command"
alias	mvn="${M2_HOME}/bin/mvn"

for I in			\
    "Build-Debug"		\
    "Clean"			\
    "Device-Activate-Logging"	\
    "Device-Deactivate-Logging"	\
    "Device-Set-Logging"	\
    "Git-Commit"		\
    "logcat"			\
    "Test-Debug"			
do
    zcompile "${PROJECT_HOME}/src/main/scripts/${I}"
    typeset -f -u "${I}"
done; unset I;

for I in			\
    "Change-Java"		\
    "Create_Images"		\
    "Pretty_Print"		\
    "Run-Debug"			\
    "Setup-iMac.local"		\
    "Setup-KonyMac01.local"	\
    "Setup-KPTiM02.local"	\
    "Start-IntelliJ"
do
    zcompile "${PROJECT_HOME}/src/main/scripts/${I}.command"
    alias "${I}"="${PROJECT_HOME}/src/main/scripts/${I}.command"
done; unset I;

function lxpm ()
{
    if test -z "${1}"; then
	${VIM}/../../MacOS/Vim -g --servername ${PROJECT_NAME} 1>/dev/null 2>/dev/null &
    else
	${VIM}/../../MacOS/Vim -g --servername ${PROJECT_NAME} --remote-silent "${@}" 1>/dev/null 2>/dev/null &
    fi;
    return;
} # function

source "${HOME}/.keystore.command"

############################################################ {{{1 ###########
# vim: set wrap tabstop=8 shiftwidth=4 softtabstop=4 noexpandtab :
# vim: set textwidth=0 filetype=zsh fileencoding=utf8 foldmethod=marker nospell :
