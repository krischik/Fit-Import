/* ********************************************************** {{{1 **********
 * Copyright © 2015 … 2019 "Martin Krischik" «krischik@users.sourceforge.net»
 * ************************************************************************** This program is
 * free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see http://www.gnu.org/licenses/
 * ********************************************************** }}}1 *********/

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
@SuppressWarnings ("WeakerAccess")
@org.androidannotations.annotations.EActivity (R.layout.main_activity)
public class MainActivity
   extends android.support.v7.app.AppCompatActivity
   implements IMainFragment,
              IMainActivity
{
   /**
    * <p> TAG as class name for logging </p>
    */
   private static final String TAG = com.krischik.Log.getLogTag (MainActivity.class);

   /**
    * <p>Google FIT Model</p>
    */
   @NotNull
   private GoogleFit googleFit;

   /**
    * <p> Calculator fragment </p>
    */
   @org.androidannotations.annotations.FragmentById (R.id.Main_Fragment)
   @Nullable
   protected MainFragment mainFragment;

   @org.androidannotations.annotations.AfterViews
   protected void afterViews ()
   {
      if (mainFragment != null)
      {
         mainFragment.setGoogleFit (googleFit);
      } // if

      return;
   } // doConnect

   /**
    * <p>we are connected to Google Fit (or not);
    *
    * @param connected
    *    true when we are connected
    */
   @hugo.weaving.DebugLog
   @Override
   public void doConnect (final boolean connected)
   {
      if (mainFragment != null)
      {
         mainFragment.doConnect (connected);
      } // if

      return;
   } // doConnect

   /**
    * <p>disconnect from Google Fit.</p>
    */
   @hugo.weaving.DebugLog
   @Override
   public void doConnectButton ()
   {
      if (mainFragment != null)
      {
         mainFragment.doConnectButton ();
      } // if

      return;
   } // doConnectButton

   /**
    * <p>the import ketfit button has been clicked.</p>
    *
    */
   @hugo.weaving.DebugLog
   @Override
   public void doKetfitButton ()
   {
      if (mainFragment != null)
      {
         mainFragment.doKetfitButton ();
      } // if

      return;
   } // doConnect

   /**
    * <p>the import withings button has been clicked.</p>
    *
    */
   @hugo.weaving.DebugLog
   @Override
   public void doWithingsButton ()
   {
      if (mainFragment != null)
      {
         mainFragment.doWithingsButton ();
      } // if

      return;
   } // doWithingsButton

   /**
    * <p>the activity</p>
    *
    * @return actvivity
    */
   @hugo.weaving.DebugLog
   @Override
   public android.support.v4.app.FragmentActivity getActivity ()
   {
      return this;
   } // getActivity

   /**
    * <p>Google FIT Model</p>
    */
   @Override @NotNull
   public com.krischik.fit_import.GoogleFit getGoogleFit ()
   {
      return googleFit;
   }

   /**
    * <p>remember if we are connected</p>
    *
    * @return true when we are connected
    */
   @Override public boolean isConnected ()
   {
      return googleFit.isConnected ();
   } // doConnectButton

   @hugo.weaving.DebugLog
   @Override
   protected void onActivityResult (
       final int requestCode,
       final int resultCode,
       final android.content.Intent data)
   {
      if (requestCode == GoogleFit.Request_OAuth)
      {
         googleFit.doConnect (resultCode);
      } // if

      return;
   } // onActivityResult

   @hugo.weaving.DebugLog
   @Override
   protected void onCreate (@Nullable final android.os.Bundle savedInstanceState)
   {
      super.onCreate (savedInstanceState);

      final boolean authInProgress = savedInstanceState != null &&
          savedInstanceState.getBoolean (GoogleFit.Auth_Pending);

      googleFit = new GoogleFit (this, authInProgress);

      return;
   } // onCreate

   @hugo.weaving.DebugLog
   @Override
   protected void onSaveInstanceState (@NotNull final android.os.Bundle outState)
   {
      super.onSaveInstanceState (outState);

      outState.putBoolean (GoogleFit.Auth_Pending, googleFit.getAuthentication_In_Progress ());

      return;
   } // onSaveInstanceState

   @hugo.weaving.DebugLog
   @Override
   protected void onStart ()
   {
      super.onStart ();
      // Connect to the Fitness API

      doConnect (false);
      googleFit.connect ();

      return;
   } // onStart

   @hugo.weaving.DebugLog
   @Override
   protected void onStop ()
   {
      super.onStop ();

      googleFit.disconnect (/* disable => */false);

      return;
   } // onStop

   @hugo.weaving.DebugLog
   @android.support.annotation.NonNull
   @Override
   public String toString () {
      return "MainActivity{" +
          "googleFit=" + googleFit +
          ", mainFragment=" + mainFragment +
          '}';
   }
} // MainActivity

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :
