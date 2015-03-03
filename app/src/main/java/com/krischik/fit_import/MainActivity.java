/********************************************************** {{{1 ***********
 *  Copyright © 2015 "Martin Krischik" «krischik@users.sourceforge.net»
 ***************************************************************************
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/
 ********************************************************** }}}1 **********/

package com.krischik.fit_import;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p> </p>
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 */
@org.androidannotations.annotations.EActivity (R.layout.main_activity)
public class MainActivity
   extends android.support.v7.app.ActionBarActivity
   implements IMainFragment
{

   /**
    * Logging tag
    */
   // private final static String TAG = MainActivity.class.getName ();
   /**
    * <p>Google FIT Model</p>
    */
   protected GoogleFit googleFit;
   /**
    * <p> Calculator fragment </p>
    */
   @org.androidannotations.annotations.FragmentById
   @Nullable
   protected MainFragment Main_Fragment;

   /**
    * <p>we are connected to Google Fit (or not);
    *
    * @param connected
    *    true when we are connected
    */
   @hugo.weaving.DebugLog
   @Override
   public void doConnect (boolean connected)
   {
      if (Main_Fragment != null)
      {
         Main_Fragment.doConnect (connected);
      } // if

      if (connected)
      {
         googleFit.insertWeight (new Withings (new java.util.Date (), 80.0f, 0.0f, 0.0f, ""));
      }

      return;
   } // doConnect

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

   @hugo.weaving.DebugLog
   @Override
   protected void onActivityResult (int requestCode, int resultCode, android.content.Intent data)
   {
      if (requestCode == GoogleFit.REQUEST_OAUTH)
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

      boolean authInProgress;

      authInProgress = savedInstanceState != null && savedInstanceState.getBoolean (GoogleFit.AUTH_PENDING);

      googleFit = new GoogleFit (this, authInProgress);

      return;
   } // onCreate

   @hugo.weaving.DebugLog
   @Override
   protected void onSaveInstanceState (@NotNull android.os.Bundle outState)
   {
      super.onSaveInstanceState (outState);
      outState.putBoolean (GoogleFit.AUTH_PENDING, googleFit.getAuthentication_In_Progress ());

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

      googleFit.disconnect ();

      return;
   } // onStop
} // MainActivity

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :