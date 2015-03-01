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
   private static class Connect_To_Google
      implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
   {
      @hugo.weaving.DebugLog
      @Override
      public void onConnected (android.os.Bundle bundle)
      {
         android.util.Log.i (TAG, "LOG00010: Connected to Google-Fit!");
         // Now you can make calls to the Fitness APIs.
         // Put application specific code here.

         return;
      }

      @hugo.weaving.DebugLog
      @Override
      public void onConnectionSuspended (int i)
      {
         // If your connection to the sensor gets lost at some point,
         // you'll be able to determine the reason and react to it here.
         if (i == com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST)
         {
            android.util.Log.e (TAG, "LOG00020: Google-Fit connection lost.  Cause: Network Lost.");
         }
         else if (i ==
            com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED)
         {
            android.util.Log.e (TAG, "LOG00030: Google-Fit Connection lost.  Reason: Service Disconnected");
         } // if

         return;
      } // onConnectionSuspended
   } // Connect_To_Google

   private class Connect_To_Google_Failed
      implements com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
   {
      // Called whenever the API client fails to connect.
      @hugo.weaving.DebugLog
      @Override
      public void onConnectionFailed (@NotNull com.google.android.gms.common.ConnectionResult result)
      {
         android.util.Log.e (TAG, "LOG00040: Connection to Google-Fit failed. Cause: " + result.toString ());

         if (!result.hasResolution ())
         {
            // Show the localized error dialog
            final android.app.Dialog errorDialog = com.google.android.gms.common.GooglePlayServicesUtil.getErrorDialog (
               result.getErrorCode (),
               com.krischik.fit_import.MainActivity.this, 0);
            errorDialog.show ();
         }
         else if (!authInProgress)
         {
            // The failure has a resolution. Resolve it.
            // Called typically when the app is not yet authorized, and an
            // authorization dialog is displayed to the user.

            try
            {
               android.util.Log.i (TAG, "LOG00050: Attempting to resolve failed connection");
               authInProgress = true;
               result.startResolutionForResult (MainActivity.this, REQUEST_OAUTH);
            }
            catch (android.content.IntentSender.SendIntentException e)
            {
               android.util.Log.e (
                  TAG,
                  "LOG00060: Exception while starting resolution activity", e);
            } // try
         } // if

         return;
      } // onConnectionFailed
   } // Connect_To_Google_Failed

   /**
    * Track whether an authorization activity is stacking over the current activity, i.e. when a known auth error is
    * being resolved, such as showing the account chooser or presenting a consent dialog. This avoids common
    * duplications as might happen on screen rotations, etc.
    */
   private static final String AUTH_PENDING = "auth_state_pending";
   private static final int REQUEST_OAUTH = 1;
   private final static String TAG = MainActivity.class.getName ();
   @Nullable
   private com.google.android.gms.common.api.GoogleApiClient Google_API_Client = null;
   private boolean authInProgress = false;
   /**
    * <p> Calculator fragment </p>
    */
   @org.androidannotations.annotations.FragmentById
   @Nullable
   protected MainFragment Main_Fragment;

   /**
    * Build a {@link com.google.android.gms.common.api.GoogleApiClient} that will authenticate the user and allow the
    * application to connect to Fitness APIs. The scopes included should match the scopes your app needs (see
    * documentation for details). Authentication will occasionally fail intentionally, and in those cases, there will be
    * a known resolution, which the OnConnectionFailedListener() can address. Examples of this include the user never
    * having signed in before, or having multiple accounts on the device and needing to specify which account to use,
    * etc.
    */
   @hugo.weaving.DebugLog
   @org.androidannotations.annotations.AfterViews
   protected void buildFitnessClient ()
   {
      final GoogleFit googleFit = new GoogleFit (this);

      final com.google.android.gms.common.api.GoogleApiClient.Builder Google_API_Builder =
         new com.google.android.gms.common.api.GoogleApiClient.Builder (this);
      final com.google.android.gms.common.api.Scope Scope = new com.google.android.gms.common.api.Scope (
         com.google.android.gms.common.Scopes.FITNESS_LOCATION_READ);

      Google_API_Builder.addApi (com.google.android.gms.fitness.Fitness.API);
      Google_API_Builder.addScope (Scope);
      Google_API_Builder.addConnectionCallbacks (googleFit);
      Google_API_Builder.addOnConnectionFailedListener (new Connect_To_Google_Failed ());
      Google_API_Client = Google_API_Builder.build ();

      return;
   } // buildFitnessClient

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

      return;
   } // doConnect

   @hugo.weaving.DebugLog
   @Override
   protected void onActivityResult (int requestCode, int resultCode, android.content.Intent data)
   {
      if (requestCode == REQUEST_OAUTH)
      {
         authInProgress = false;
         if (resultCode == RESULT_OK)
         {
            // Make sure the app is not already connected or attempting to connect
            if (Google_API_Client != null && !Google_API_Client.isConnecting () && !Google_API_Client.isConnected ())
            {
               Google_API_Client.connect ();
            }
         }
      }

      return;
   } // onActivityResult

   @hugo.weaving.DebugLog
   @Override
   protected void onCreate (@Nullable final android.os.Bundle savedInstanceState)
   {
      super.onCreate (savedInstanceState);

      if (savedInstanceState != null)
      {
         authInProgress = savedInstanceState.getBoolean (AUTH_PENDING);
      }

      return;
   } // onCreate

   @hugo.weaving.DebugLog
   @Override
   protected void onSaveInstanceState (@NotNull android.os.Bundle outState)
   {
      super.onSaveInstanceState (outState);
      outState.putBoolean (AUTH_PENDING, authInProgress);

      return;
   } // onSaveInstanceState

   @hugo.weaving.DebugLog
   @Override
   protected void onStart ()
   {
      super.onStart ();
      // Connect to the Fitness API
      android.util.Log.i (TAG, "LOG00070: Connecting to Google-Fit…");

      doConnect (false);

      if (Google_API_Client != null)
      {
         Google_API_Client.connect ();
      }

      return;
   } // onStart

   @hugo.weaving.DebugLog
   @Override
   protected void onStop ()
   {
      super.onStop ();

      if (Google_API_Client != null && Google_API_Client.isConnected ())
      {
         Google_API_Client.disconnect ();
      } // if

      return;
   } // onStop
} // MainActivity

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :