#!/opt/local/bin/zsh
########################################################### {{{1 ###########
#  Copyright © 2015 … 2016 "Martin Krischik" «krischik@users.sourceforge.net»
###########################################################################
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
#  along with this program.  If not, see http://www.gnu.org/licenses/
########################################################### }}}1 ###########

setopt Err_Exit;
setopt No_XTrace;

#pushd /Volumes/Boxcryptor/Dropbox/Secure
    #adb push "Withings - Gewicht Martin.csv" "/storage/extSdCard/Android/data/com.krischik.fit_import/files"
#popd

alias aapt2="${ANDROID_HOME}/build-tools/30.0.1/aapt2"

pushd "${PROJECT_HOME}"
    pushd "AndroidLib"
	aapt2 compile		    \
	    -v			    \
	    --dir "src/main/res"    \
	    -o


aapt2 link path-to-input-files [options] -o
        outputdirectory/outputfilename.apk --manifest AndroidManifest.xml

    gradlew build
popd

############################################################ {{{1 ###########
# vim: set wrap tabstop=8 shiftwidth=4 softtabstop=4 noexpandtab :
# vim: set textwidth=0 filetype=zsh foldmethod=marker nospell :
