PUSHD %PROJECT_HOME%
  adb -s 06921b990b113778 shell setprop log.tag.Main VERBOSE
  adb -s 06921b990b113778 shell setprop log.tag.KrischikLog VERBOSE
  adb -s 06921b990b113778 shell setprop log.tag.ik.fit_import.PackageKt VERBOSE
POPD
