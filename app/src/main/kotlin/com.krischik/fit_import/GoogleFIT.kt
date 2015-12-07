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

package com.krischik.fit_import

import com.google.android.gms.fitness.data.Device

/**
 * <p>
 * </p>
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 * @param Authentication_In_Progress: true when authentication is currently on progress. Used to prevent double authentication
 */

class GoogleFit(
   val owner: IMainFragment,
   var Authentication_In_Progress: Boolean) :
   com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks,
   com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{
   companion object
   {
      /**
       * Logging tag
       */
      private val TAG = GoogleFit::class.java.getName()

      /**
       * <p>Request Authentication message id</p>
       */
      @JvmField
      public val REQUEST_OAUTH: Int = 1

      /**
       * Track whether an authorization activity is stacking over the current activity, i.e. when a known auth error is
       * being resolved, such as showing the account chooser or presenting a consent dialog. This avoids common
       * duplications as might happen on screen rotations, etc.
       */
      @JvmField
      public val AUTH_PENDING: String = "auth_state_pending"
   } // companion object

   /**
    * <p>The underlying api client</p>
    */
   private val Google_API_Client: com.google.android.gms.common.api.GoogleApiClient

   init
   {
      val Google_API_Builder = com.google.android.gms.common.api.GoogleApiClient.Builder (owner.getActivity())
      val Location = com.google.android.gms.common.api.Scope (
         com.google.android.gms.common.Scopes.FITNESS_LOCATION_READ_WRITE)
      val Activity = com.google.android.gms.common.api.Scope (
         com.google.android.gms.common.Scopes.FITNESS_ACTIVITY_READ_WRITE)
      val Body = com.google.android.gms.common.api.Scope (
         com.google.android.gms.common.Scopes.FITNESS_BODY_READ_WRITE)

      Google_API_Builder.addApi (com.google.android.gms.fitness.Fitness.API)
      Google_API_Builder.addScope (Location)
      Google_API_Builder.addScope (Activity)
      Google_API_Builder.addScope (Body)
      Google_API_Builder.addConnectionCallbacks (this)
      Google_API_Builder.addOnConnectionFailedListener (this)
      Google_API_Client = Google_API_Builder.build ()
   } // init

   @hugo.weaving.DebugLog
   override fun onConnected(bundle: android.os.Bundle?)
   {
      android.util.Log.i (TAG, "LOG00010: Connected to Google-Fit!")
      // Now you can make calls to the Fitness APIs.
      // Put application specific code here.

      owner.doConnect(true)

      return
   } // onConnected

   @hugo.weaving.DebugLog
   public fun doConnect(resultCode: Int)
   {
      Authentication_In_Progress = false

      if (resultCode == android.app.Activity.RESULT_OK)
      {
         // Make sure the app is not already connected or attempting to connect
         if (!Google_API_Client.isConnecting () && !Google_API_Client.isConnected ())
         {
            Google_API_Client.connect ()
         } // if
      } // if

      return
   } // doConnect

   /**
    * <p>connect to service</p>
    */
   @hugo.weaving.DebugLog
   public fun connect()
   {
      android.util.Log.i (TAG, "LOG00070: Connecting to Google-Fit…")

      Google_API_Client.connect ()
   } // connect

   /**
    * <p>disconnect from service</p>
    */
   @hugo.weaving.DebugLog
   public fun disconnect()
   {
      android.util.Log.i (TAG, "LOG00080: Disconnecting from Google-Fit…")

      if (Google_API_Client.isConnected ())
      {
         Google_API_Client.disconnect ()
      } // if
   } // disconnect

   @hugo.weaving.DebugLog
   override fun onConnectionSuspended(i: Int)
   {
      // If your connection to the sensor gets lost at some point,
      // you'll be able to determine the reason and react to it here.
      if (i == com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST)
      {
         android.util.Log.e (TAG, "LOG00020: Google-Fit connection lost.  Cause: Network Lost.")
      }
      else if (i == com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED)
      {
         android.util.Log.e (TAG, "LOG00030: Google-Fit Connection lost.  Reason: Service Disconnected")
      } // if

      return
   } // onConnectionSuspended

   /**
    * <p>Called whenever the API client fails to connect.</p>
    */
   @hugo.weaving.DebugLog
   override fun onConnectionFailed(result: com.google.android.gms.common.ConnectionResult)
   {
      android.util.Log.e (TAG, "LOG00040: Connection to Google-Fit failed. Cause: " + result.toString ())

      if (!result.hasResolution ())
      {
         // Show the localized error dialog
         val errorDialog = com.google.android.gms.common.GooglePlayServicesUtil.getErrorDialog (
            result.getErrorCode (),
            owner.getActivity(),
            0)
         errorDialog.show ()
      }
      else if (!Authentication_In_Progress)
      {
         // The failure has a resolution. Resolve it.
         // Called typically when the app is not yet authorized, and an
         // authorization dialog is displayed to the user.

         try
         {
            android.util.Log.i (TAG, "LOG00050: Attempting to resolve failed connection")
            Authentication_In_Progress = true
            result.startResolutionForResult (owner.getActivity(), REQUEST_OAUTH)
         }
         catch (exception: android.content.IntentSender.SendIntentException)
         {
            android.util.Log.e (TAG, "LOG00060: Exception while starting resolution activity", exception)
         } // try
      } // if

      return
   }  // onConnectionFailed

   /**
    * <p>store withings weight</p>
    */
   @hugo.weaving.DebugLog
   public fun insertWeight(withings: Withings)
   {
      val Data_Source_Builder = com.google.android.gms.fitness.data.DataSource.Builder()

      Data_Source_Builder.setAppPackageName(owner.getActivity())
      Data_Source_Builder.setDataType(com.google.android.gms.fitness.data.DataType.TYPE_WEIGHT)
      Data_Source_Builder.setName(TAG + " – Withings weight")
      Data_Source_Builder.setType(com.google.android.gms.fitness.data.DataSource.TYPE_RAW)
//      Data_Source_Builder.setDevice(Device())

      val Data_Source = Data_Source_Builder.build()
      val Data_Set = com.google.android.gms.fitness.data.DataSet.create(Data_Source)
      val Data_Point = Data_Set.createDataPoint()

      Data_Point.setTimeInterval(
         /* startTime => */withings.Time.getTime(),
         /* endTime   => */withings.Time.getTime(),
         /* timeUnit  => */java.util.concurrent.TimeUnit.MILLISECONDS)

      val Weight_Field = Data_Point.getValue(com.google.android.gms.fitness.data.Field.FIELD_WEIGHT)

      Weight_Field.setFloat(withings.Weight)

      Data_Set.add(Data_Point)

      val Result = com.google.android.gms.fitness.Fitness.HistoryApi.insertData (Google_API_Client, Data_Set)

      Result.setResultCallback ({ Status ->
         if (!Status.isSuccess) {
            android.util.Log.e (TAG, "There was a problem inserting the weight: " + Status.statusMessage);
         } // if
      }, 1, java.util.concurrent.TimeUnit.MINUTES)
      return
   } // insertWeight
   /**
    * <p>store withings weight</p>
    */
   @hugo.weaving.DebugLog
   public fun insertKetfit(ketfit: Ketfit)
   {
      val Data_Source_Builder = com.google.android.gms.fitness.data.DataSource.Builder()

      Data_Source_Builder.setAppPackageName(owner.getActivity())
      Data_Source_Builder.setDataType(com.google.android.gms.fitness.data.DataType.TYPE_HEART_RATE_BPM)
      Data_Source_Builder.setName(TAG + " – Ketfit training")
      Data_Source_Builder.setType(com.google.android.gms.fitness.data.DataSource.TYPE_RAW)

      val Data_Source = Data_Source_Builder.build()
      val Data_Set = com.google.android.gms.fitness.data.DataSet.create(Data_Source)
      val Data_Point = Data_Set.createDataPoint()

      Data_Set.add(Data_Point)

      // com.google.android.gms.fitness.Fitness.HistoryApi.insertData (Google_API_Client, Data_Set)
      return
   } // insertWeight
} // GoogleFit
