PUSHD %PROJECT_HOME%
  adb -s 06921b990b113778 shell setprop log.tag.Main INFO
  adb -s 06921b990b113778 shell setprop log.tag.KrischikLog INFO
  adb -s 06921b990b113778 shell setprop log.tag.import.Application_Test VERBOSE
  adb -s 06921b990b113778 shell setprop log.tag.t_import.GoogleFIT_Test VERBOSE
  adb -s 06921b990b113778 shell setprop log.tag.port.Main_Activity_Test VERBOSE
  adb -s 06921b990b113778 shell setprop log.tag.ik.fit_import.PackageKt VERBOSE
POPD