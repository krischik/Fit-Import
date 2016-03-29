/***********************************************************
 * {{{1 *********** Copyright © 2015 … 2016 "Martin Krischik" «krischik@users.sourceforge.net»
 * ************************************************************************** This program is
 * free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see http://www.gnu.org/licenses/
 * *********************************************************
 * }}}1
 **********/

package com.krischik.fit_import;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p> </p>
 *
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings ({"HardcodedLineSeparator", "WeakerAccess"})
@org.androidannotations.annotations.EFragment (R.layout.main_fragment)
public class MainFragment
   extends android.support.v4.app.Fragment
   implements IMainFragment
{
   /**
    * <p>the app is not finished and some things are still not there. Like the file
    * picker.</p>
    */
   @android.annotation.SuppressLint ("SdCardPath")
   @SuppressWarnings ("HardcodedFileSeparator")
   public static final String SD_Card_Path =
      "/sdcard/Android/data/com.krischik.fit_import";
//    "/storage/extSdCard/Android/data/com.krischik.fit_import/files";
   /**
    * <p> TAG as class name for logging </p>
    */
   private final static String TAG = com.krischik.Log.getLogTag (MainFragment.class);
   /**
    * <p>Google FIT Model</p>
    */
   private GoogleFit googleFit;

   @Override
   public void onCreate (@Nullable final android.os.Bundle savedInstanceState)
   {
      handler = new GoogleFitHandler ();

      return;
   }

   /**
    * <p>hander for long running jobs.</p>
    */
   @NotNull
   private GoogleFitHandler handler;
   /**
    * <p>Import Withings CVS button</p>
    */
   @org.androidannotations.annotations.ViewById (R.id.connect)
   @Nullable
   protected android.widget.Button connectButton;
   /**
    * <p>Import Withings CVS button</p>
    */
   @org.androidannotations.annotations.ViewById (R.id.Error_Text)
   @Nullable
   protected android.widget.EditText errorText;
   /**
    * <p>Import Ketfit CVS button</p>
    */
   @org.androidannotations.annotations.ViewById (R.id.Ketfit_Button)
   @Nullable
   protected android.widget.Button ketfitButton;
   /**
    * <p>Import Withings CVS button</p>
    */
   @org.androidannotations.annotations.ViewById (R.id.Withings_Button)
   @Nullable
   protected android.widget.Button withingsButton;

   /**
    * <p>we are connected to Google Fit (or not)</p>
    *
    * @param connected
    *    true when we are connected
    */
   @hugo.weaving.DebugLog
   @Override
   public void doConnect (boolean connected)
   {
      if (ketfitButton != null)
      {
         ketfitButton.setEnabled (connected);
      } // if
      if (withingsButton != null)
      {
         withingsButton.setEnabled (connected);
      } // if
      if (connectButton != null)
      {
         connectButton.setText (connected
                                   ? R.string.Disconnect
                                   : R.string.Connect);
      } // if

      return;
   } // doConnect

   /**
    * <p>disconnect from Google Fit.</p>
    */
   @hugo.weaving.DebugLog
   @org.androidannotations.annotations.Click (com.krischik.fit_import.R.id.connect)
   @Override
   public void doConnectButton ()
   {
      if (googleFit != null)
      {
         if (googleFit.isConnected ())
         {
            googleFit.disconnect (/* disable => */true);
         }
         else
         {
            googleFit.connect ();
         } // if
      }
      else
      {
         this.doConnect (false);
      } // if

      return;
   } // doConnectButton

   /**
    * <p>the import ketfit button has been clicked.</p>
    */
   @hugo.weaving.DebugLog
   @org.androidannotations.annotations.Click (R.id.Ketfit_Button)
   @Override
   public void doKetfitButton ()
   {
      final android.support.v4.app.FragmentActivity activity = getActivity ();

      assert errorText != null : "Won't be null if buttons are available to be clicked";
      errorText.setText ("");

      if (googleFit != null && activity != null)
      {
         // OK, some kind of file selector is missing here.
         // final java.io.File dir = activity.getExternalFilesDir (null);
         @SuppressWarnings ("HardcodedFileSeparator")
         final java.io.File dir = new java.io.File (SD_Card_Path);

         //noinspection ConstantConditions
         if (dir != null)
         {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs ();

            final java.io.File file = new java.io.File (
               dir,
               "kettfit_01_01_2014-29_02_2016.csv");

            if (file.exists ())
            {
               final android.os.Message Keyboard_Message = this.handler.obtainMessage ();
               Keyboard_Message.obj = new GoogleFitHandler.Command (
               /* Type       => */ GoogleFitHandler.Type.Ketfit,
               /* File       => */ file,
               /* Error_Text => */ errorText,
               /* GoogleFit  => */ googleFit);
               this.handler.sendMessage (Keyboard_Message);
            }
            else
            {
               errorText.append ("Input file “" + file + "” does not exist\n");
               com.krischik.Log.e (TAG, "LOG00020: Input file “%1$s” does not exist", file);
            } // if
         }
         else
         {
            errorText.append ("No directory to read from!\n");
            com.krischik.Log.e (TAG, "LOG00020: No directory to read from");
         } // if
      }
      else
      {
         errorText.append ("No googleFit or activity!\n");
         com.krischik.Log.e (TAG, "LOG00010: No googleFit or activity!");
      } // if

      return;
   } // doKetfitButton

   /**
    * <p>the import Withings button has been clicked.</p>
    */
   @hugo.weaving.DebugLog
   @org.androidannotations.annotations.Click (R.id.Withings_Button)
   @Override
   public void doWithingsButton ()
   {
      final android.support.v4.app.FragmentActivity activity = getActivity ();

      assert errorText != null : "Won't be null if buttons are available to be clicked";
      errorText.setText ("");

      if (googleFit != null && activity != null)
      {
         // OK, some kind of file selector is missing here.
         // final java.io.File dir = activity.getExternalFilesDir (null);
         @SuppressWarnings ("HardcodedFileSeparator")
         final java.io.File dir = new java.io.File (SD_Card_Path);

         //noinspection ConstantConditions
         if (dir != null)
         {
            final java.io.File file = new java.io.File (dir, "Withings - Gewicht Martin.csv");

            if (file.exists ())
            {
               final android.os.Message Keyboard_Message = this.handler.obtainMessage ();
               Keyboard_Message.obj = new GoogleFitHandler.Command (
               /* Type       => */ GoogleFitHandler.Type.Withings,
               /* File       => */ file,
               /* Error_Text => */ errorText,
               /* GoogleFit  => */ googleFit);
               this.handler.sendMessage (Keyboard_Message);
            }
            else
            {
               errorText.append ("Input file “" + file + "” does not exist\n");
               com.krischik.Log.e (TAG, "LOG00030: Input file “%1$s” does not exist", file);
            } // if
         }
         else
         {
            errorText.append ("No directory to read from!\n");
            com.krischik.Log.e (TAG, "LOG00040: No directory to read from");
         } // if
      }
      else
      {
         errorText.append ("No googleFit or activity!\n");
         com.krischik.Log.e (TAG, "LOG00050: No googleFit or activity!");
      } // if

      return;
   } // doWithingsButton

   public void setGoogleFit (@NotNull GoogleFit googleFit)
   {
      // API 19, activate again at a later time
      // java.util.Objects.requireNonNull (googleFit);

      this.googleFit = googleFit;

      return;
   } // setGoogleFit
} // MainFragment

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :
