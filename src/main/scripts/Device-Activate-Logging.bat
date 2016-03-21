PUSHD %PROJECT_HOME%
  adb -s 0a8280a3 shell setprop log.tag.Main VERBOSE
  adb -s 0a8280a3 shell setprop log.tag.KrischikLog VERBOSE
  adb -s 0a8280a3 shell setprop log.tag.ik.fit_import.PackageKt VERBOSE
POPD
