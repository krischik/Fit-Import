""""""""""""""""""""""""""""""""""""""""""""""""""""""""""" {{{1 """""""""""
" Copyright © 2015 … 2016 KPT/CPT
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""" }}}1 """""""""""

function s:Merge_1 ()
   /\V<<<<<<</		   delete
   /\V=======/,/\V>>>>>>>/ delete
endfunction Remove_Forks

function s:Merge_2 ()
   /\V<<<<<<</,/\V=======/ delete
   /\V>>>>>>>/		   delete
endfunction Remove_Forks

function s:Convert_Command_To_Cmd ()
   set filetype=btm
   set fileformat=dos

   $	 substitute /zsh/btm/e
   $	 substitute /unix/dos/e

   1,5	 substitute  /^# /::/g
   1,5	 substitute  /#/:/g

   $-3,$ substitute  /^# /::/g
   $-3,$ substitute  /#/:/g

   5 append
@ECHO OFF

IF NOT "%@Eval[2 + 2]%" == "4" (ECHO ^e[42mYou need TakeCommand [http://www.jpsoft.com] to execute this batch file.^e[m & EXIT /B 1)

IFF NOT DEFINED %[PROJECT_HOME] THEN
    CALL %@Path[%_BatchName]\Setup.cmd
ENDIFF

SETLOCAL
    ON CONDITION ERRORLEVEL NE 0    CANCEL
    ON BREAK                        CANCEL
    ON ERRORMSG                     CANCEL
.

   $-3 append
ENDLOCAL
.

   6,$-4 substitute /\Vtypeset -x -g/SET/
   6,$-4 substitute /\<if\>/IF/
   6,$-4 substitute /\<pushd\>/PUSHD/
   6,$-4 substitute /\<popd\>/POPD/
   6,$-4 substitute /\<done\>/ENDDO/
   6,$-4 substitute /\<fi\>/ENDIFF/
   6,$-4 substitute /; \<then\>/ THEN/
   6,$-4 substitute /${\(.\{-}\)}/%[\1]/g
   6,$-4 substitute /\<if\> \<test\> -x/IFF EXIST/
   6,$-4 substitute /\<alias\>/ALIAS/
endfunction Convert_Command_To_Cmd

function s:Java_To_CSharp ()
   %substitute /Log.e(.*.TAG, /Logger.Error(/g
   %substitute /final .* \(\k*\) = /var \1 = /
   %substitute /\([( ]\)final /\1/g
   %substitute /@\<NotNull\>/ /g
   %substitute /@\<Nullable\>/ /g
   %substitute /\<Object\>/in object/g
   %substitute /\<String\>/in string/g
   %substitute /\<CharSequence\>/in string/g
   %substitute /\<boolean\>/bool/g
   %substitute /\<IllegalArgumentException\>/System.InvalidOperationException/g
   %substitute /\<IllegalArgumentException\>/System.ArgumentException/g

   %substitute /trim()/Trim()/g
   %substitute /length()/Length/g

   %substitute /\V\\u00ab/«/g
   %substitute /\V\\u00bb/»/g

   %global /\V@Contract/      delete
   %global /^[ /]*@NotNull$/  delete
   %global /@param/	      .,+1 join
   %global /@throws/	      .,+1 join

   %substitute /@param \(\k*\) \(.*\)/<param name="\1">\2<\/param>/
   %substitute /@throws \(\k*\) \(.*\)/<throws name="\1">\2<\/throws>/
   %substitute /@throws \([A-Za-z.]*\) \(.*\)/<throws name="\1">\2<\/throws>/
endfunction Java_To_CSharp 

function s:Convert_Buildpipeline ()
   %substitute /\V"id": 20/"id": 4/g
   %substitute /\V"id": 23/"id": 3/g
   %substitute /\V"id": 24/"id": 5/g
   %substitute /\V"bf4c942e-30db-4ef3-8a76-ba20a644b029"/"95ceb372-ef89-4553-85f5-83b6499564dc"/
endfunction Convert_Buildpipeline

command! ConvertCommandToCmd	 call <SID>Convert_Command_To_Cmd ()
command! JavaToCSharp		 call <SID>Java_To_CSharp ()
command! Merge1			 call <SID>Merge_1 ()
command! Merge2			 call <SID>Merge_2 ()
command! ConvertBuildpipeline	 call <SID>Convert_Buildpipeline ()

46amenu <silent> Plugin.Convert.Command\ To\ Cmd   :ConvertCommandToCmd<CR>
46amenu <silent> Plugin.Convert.Java\ To\ CSharp   :JavaToCSharp<CR>
46amenu <silent> Plugin.Convert.Buildpipeline      :ConvertBuildpipeline<CR>
46amenu <silent> Plugin.Merge.Use\ First	   :Merge1<CR>
46amenu <silent> Plugin.Merge.Use\ Second	   :Merge2<CR>

"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""" {{{1 """""""""""
" vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 noexpandtab textwidth=96 :
" vim: set fileformat=unix fileencoding=utf-8 filetype=vim foldmethod=marker 
" vim: spell spelllang=en_gb :
