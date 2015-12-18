/********************************************************** {{{1 ***********
 *  Copyright © 2015 … 2016 "Martin Krischik" «krischik@users.sourceforge.net»
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

/**
 * <p>
 * </p>
 *
 * @author "Martin Krischik" «krischik@users.sourceforge.net»
 * @version 1.0
 * @since 1.0
 * @param Authentication_In_Progress: true when authentication is currently on progress. Used to prevent
 * double authentication
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
      private val TAG = com.krischik.Log.getLogTag(GoogleFit::class.java)

      /**
       * <p>Request Authentication message id</p>
       */
      public const val Request_OAuth: Int = 1

      /**
       * Track whether an authorization activity is stacking over the current activity, i.e. when a known auth error is
       * being resolved, such as showing the account chooser or presenting a consent dialog. This avoids common
       * duplications as might happen on screen rotations, etc.
       */
      public const val Auth_Pending: String = "auth_state_pending"

      /**
       * The withings scale
       */
      public val Withings_Scale = com.google.android.gms.fitness.data.Device(
         /* manufacturer => */"Withings",
         /* model        => */"BSB01",
         /* uid          => */"00:24:e4:12:8a:68",
         /* type         => */com.google.android.gms.fitness.`data`.Device.TYPE_SCALE)
      /**
       * The Kettler UNIX S Trainer.
       */
      public val Kettler_Trainer = com.google.android.gms.fitness.data.Device(
         /* manufacturer => */"Kettler",
         /* model        => */"UNIX S",
         /* uid          => */"ABC123",
         /* type         => */com.google.android.gms.fitness.`data`.Device.TYPE_SCALE)
   } // companion object

   /**
    * <p>The underlying api client</p>
    */
   private val Google_API_Client: com.google.android.gms.common.api.GoogleApiClient

   init
   {
      val apiBuilder = com.google.android.gms.common.api.GoogleApiClient.Builder (owner.getActivity())
      val location = com.google.android.gms.common.api.Scope (
         com.google.android.gms.common.Scopes.FITNESS_LOCATION_READ_WRITE)
      val activity = com.google.android.gms.common.api.Scope (
         com.google.android.gms.common.Scopes.FITNESS_ACTIVITY_READ_WRITE)
      val body = com.google.android.gms.common.api.Scope (
         com.google.android.gms.common.Scopes.FITNESS_BODY_READ_WRITE)
      val nutrition = com.google.android.gms.common.api.Scope (
         com.google.android.gms.common.Scopes.FITNESS_NUTRITION_READ_WRITE)

      apiBuilder.addApi (com.google.android.gms.fitness.Fitness.HISTORY_API)
      apiBuilder.addApi (com.google.android.gms.fitness.Fitness.SESSIONS_API)
      apiBuilder.addScope (location)
      apiBuilder.addScope (activity)
      apiBuilder.addScope (body)
      apiBuilder.addScope (nutrition)
      apiBuilder.addConnectionCallbacks (this)
      apiBuilder.addOnConnectionFailedListener (this)

      Google_API_Client = apiBuilder.build ()
   } // init

   @hugo.weaving.DebugLog
   override fun onConnected(bundle: android.os.Bundle?)
   {
      com.krischik.Log.i (TAG, "LOG00010: Connected to Google-Fit!")
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
      com.krischik.Log.i (TAG, "LOG00070: Connecting to Google-Fit…")

      Google_API_Client.connect ()
   } // connect

   /**
    * <p>disconnect from service</p>
    */
   @hugo.weaving.DebugLog
   public fun disconnect()
   {
      com.krischik.Log.i (TAG, "LOG00080: Disconnecting from Google-Fit…")

      if (Google_API_Client.isConnected)
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
         com.krischik.Log.e (TAG, "LOG00020: Google-Fit connection lost.  Cause: Network Lost.")
      }
      else if (i == com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED)
      {
         com.krischik.Log.e (TAG,
            "LOG00030: Google-Fit Connection lost.  Reason: Service Disconnected")
      } // if

      return
   } // onConnectionSuspended

   /**
    * <p>Called whenever the API client fails to connect.</p>
    */
   @hugo.weaving.DebugLog
   override fun onConnectionFailed(result: com.google.android.gms.common.ConnectionResult)
   {
      com.krischik.Log.e (TAG,
         "LOG00040: Connection to Google-Fit failed. Cause: " + result.toString ())

      if (!result.hasResolution ())
      {
         // Show the localized error dialog
         val apiAvailability = com.google.android.gms.common.GoogleApiAvailability.getInstance ()
         val errorDialog = apiAvailability.getErrorDialog (
            /* activity    => */ owner.activity,
            /* errorCode   => */ result.errorCode,
            /* requestCode => */ Request_OAuth)
         errorDialog.show ()
      }
      else if (!Authentication_In_Progress)
      {
         // The failure has a resolution. Resolve it.
         // Called typically when the app is not yet authorized, and an
         // authorization dialog is displayed to the user.

         try
         {
            com.krischik.Log.i (TAG, "LOG00050: Attempting to resolve failed connection")
            Authentication_In_Progress = true
            result.startResolutionForResult (owner.getActivity(), Request_OAuth)
         }
         catch (exception: android.content.IntentSender.SendIntentException)
         {
            com.krischik.Log.e (TAG,
               "LOG00060: Exception while starting resolution activity",
               exception)
         } // try
      } // if

      return
   }  // onConnectionFailed

   /**
    * This method creates a dataset object to be able to insert data in google fit
    * @param dataType DataType Fitness Data Type object
    * @param sourceType int Data Source Id. For example, DataSource.TYPE_RAW
    * @param startTime long Time when the fitness activity started
    * @param endTime long Time when the fitness activity finished
    * @param setValues lambda to set the values
    * @return
    */
   @hugo.weaving.DebugLog
   private fun createDataSet(
      dataType: com.google.android.gms.fitness.data.DataType,
      sourceType: Int,
      sourceName: String,
      device: com.google.android.gms.fitness.data.Device,
      startTime: java.util.Date,
      endTime: java.util.Date = startTime,
      setValues: (Data_Point: com.google.android.gms.fitness.data.DataPoint) -> Unit):
      com.google.android.gms.fitness.data.DataSet
   {
      val dataSourceBuilder = com.google.android.gms.fitness.data.DataSource.Builder()

      dataSourceBuilder.setAppPackageName(owner.activity)
      dataSourceBuilder.setDataType(dataType)
      dataSourceBuilder.setName(createName(sourceName))
      dataSourceBuilder.setType(sourceType)
      dataSourceBuilder.setDevice(device)

      val dataSource = dataSourceBuilder.build()
      val dataSet = com.google.android.gms.fitness.data.DataSet.create(dataSource)
      val dataPoint = dataSet.createDataPoint()

      dataPoint.setTimeInterval(
         /* startTime => */startTime.time,
         /* endTime   => */endTime.time,
         /* timeUnit  => */java.util.concurrent.TimeUnit.MILLISECONDS)

      setValues (dataPoint)

      dataSet.add (dataPoint);

      return dataSet;
   }

   private fun createName(sourceName: String) = "GoogleFIT Importer – " + sourceName

   /**
    * <p>store withings weight</p>
    */
   @hugo.weaving.DebugLog
   public fun insertWeight(withings: Withings)
   {
      run (
         {
            val dataSet = createDataSet(
               dataType = com.google.android.gms.fitness.data.DataType.TYPE_WEIGHT,
               sourceType = com.google.android.gms.fitness.data.DataSource.TYPE_RAW,
               sourceName = "Withings weight",
               device = Withings_Scale,
               startTime = withings.time,
               setValues = {
                  Data_Point ->
                  val Weight_Field = Data_Point.getValue(com.google.android.gms.fitness.data.Field.FIELD_WEIGHT)

                  Weight_Field.setFloat(withings.weight)
               })

            val Result = com.google.android.gms.fitness.Fitness.HistoryApi.insertData (Google_API_Client, dataSet)

            Result.setResultCallback (
               {
                  status ->
                  if (!status.isSuccess)
                  {
                     com.krischik.Log.e (TAG, "There was a problem inserting the weight: " + status.statusMessage);
                  } // if
               },
               /* time => */ 1,
               /* unit => */ java.util.concurrent.TimeUnit.MINUTES)
         })

      if (withings.fat != 0.0f)
      {
         val dataSet = createDataSet(
            dataType = com.google.android.gms.fitness.data.DataType.TYPE_BODY_FAT_PERCENTAGE,
            sourceType = com.google.android.gms.fitness.data.DataSource.TYPE_RAW,
            sourceName = "Withings fat",
            device = Withings_Scale,
            startTime = withings.time,
            setValues = {
               Data_Point ->
               val Weight_Field = Data_Point.getValue(com.google.android.gms.fitness.data.Field.FIELD_PERCENTAGE)

               Weight_Field.setFloat(withings.getFatPercentage ())
            })

         val Result = com.google.android.gms.fitness.Fitness.HistoryApi.insertData (Google_API_Client, dataSet)

         Result.setResultCallback (
            {
               status ->
               if (!status.isSuccess)
               {
                  com.krischik.Log.e (TAG, "There was a problem inserting the fat content: " + status.statusMessage);
               } // if
            },
            /* time => */ 1,
            /* unit => */ java.util.concurrent.TimeUnit.MINUTES)
      } // if
      return
   } // insertWeight

   /**
    * <p>store Kettler training</p>
    */
   @hugo.weaving.DebugLog
   public fun insertTraining(ketfit: Ketfit)
   {
      val sessionBuilder = com.google.android.gms.fitness.data.Session.Builder()

      sessionBuilder.setName(createName("Ketfit training"))
      sessionBuilder.setStartTime(ketfit.start.time, java.util.concurrent.TimeUnit.MILLISECONDS)
      sessionBuilder.setEndTime(ketfit.end.time, java.util.concurrent.TimeUnit.MILLISECONDS)
      sessionBuilder.setActivity(com.google.android.gms.fitness.FitnessActivities.ELLIPTICAL)

      val session = sessionBuilder.build()
      val requestBuilder = com.google.android.gms.fitness.request.SessionInsertRequest.Builder()

      requestBuilder.setSession (session)
      requestBuilder.addDataSet (createDataSet(
         dataType = com.google.android.gms.fitness.data.DataType.TYPE_ACTIVITY_SAMPLE,
         sourceType = com.google.android.gms.fitness.data.DataSource.TYPE_RAW,
         sourceName = "Ketfit training",
         device = Kettler_Trainer,
         startTime = ketfit.start,
         endTime = ketfit.end,
         setValues =
         {
            Data_Point ->
            val Field = Data_Point.getValue(com.google.android.gms.fitness.data.Field.FIELD_ACTIVITY)

            Field.setActivity(com.google.android.gms.fitness.FitnessActivities.ELLIPTICAL);
         }))
      requestBuilder.addDataSet (createDataSet(
         dataType = com.google.android.gms.fitness.data.DataType.TYPE_HEART_RATE_BPM,
         sourceType = com.google.android.gms.fitness.data.DataSource.TYPE_RAW,
         sourceName = "Ketfit training",
         device = Kettler_Trainer,
         startTime = ketfit.start,
         endTime = ketfit.end,
         setValues =
         {
            Data_Point ->

            val field = Data_Point.getValue(com.google.android.gms.fitness.data.Field.FIELD_BPM)

            field.setFloat(ketfit.puls.toFloat());
         }))
      requestBuilder.addDataSet (createDataSet(
         dataType = com.google.android.gms.fitness.data.DataType.TYPE_POWER_SAMPLE,
         sourceType = com.google.android.gms.fitness.data.DataSource.TYPE_RAW,
         sourceName = "Ketfit training",
         device = Kettler_Trainer,
         startTime = ketfit.start,
         endTime = ketfit.end,
         setValues =
         {
            Data_Point ->

            val field = Data_Point.getValue(com.google.android.gms.fitness.data.Field.FIELD_WATTS)

            field.setFloat(ketfit.watt.toFloat());
         }))
      requestBuilder.addDataSet (createDataSet(
         dataType = com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_WHEEL_RPM,
         sourceType = com.google.android.gms.fitness.data.DataSource.TYPE_RAW,
         sourceName = "Ketfit training",
         device = Kettler_Trainer,
         startTime = ketfit.start,
         endTime = ketfit.end,
         setValues =
         {
            Data_Point ->

            val field = Data_Point.getValue(com.google.android.gms.fitness.data.Field.FIELD_RPM)

            field.setFloat(ketfit.uMin.toFloat());
         }))
      requestBuilder.addDataSet (createDataSet(
         dataType = com.google.android.gms.fitness.data.DataType.TYPE_CALORIES_EXPENDED,
         sourceType = com.google.android.gms.fitness.data.DataSource.TYPE_RAW,
         sourceName = "Ketfit training",
         device = Kettler_Trainer,
         startTime = ketfit.start,
         endTime = ketfit.end,
         setValues =
         {
            Data_Point ->

            val field = Data_Point.getValue(com.google.android.gms.fitness.data.Field.FIELD_CALORIES)

            field.setFloat(ketfit.kCal.toFloat ());
         }))
      requestBuilder.addDataSet (createDataSet(
         dataType = com.google.android.gms.fitness.data.DataType.TYPE_DISTANCE_DELTA,
         sourceType = com.google.android.gms.fitness.data.DataSource.TYPE_RAW,
         sourceName = "Ketfit training",
         device = Kettler_Trainer,
         startTime = ketfit.start,
         endTime = ketfit.end,
         setValues =
         {
            Data_Point ->

            val field = Data_Point.getValue(com.google.android.gms.fitness.data.Field.FIELD_DISTANCE)

            field.setFloat(ketfit.getMeter ());
         }))

      val request = requestBuilder.build();
      val Result = com.google.android.gms.fitness.Fitness.SessionsApi.insertSession (Google_API_Client, request)

      Result.setResultCallback (
         {
            status ->
            if (!status.isSuccess)
            {
               com.krischik.Log.e (TAG, "There was a problem inserting the training session: " + status.statusMessage);
            } // if
         },
         /* time => */ 1,
         /* unit => */ java.util.concurrent.TimeUnit.MINUTES)

      // com.google.android.gms.fitness.Fitness.HistoryApi.insertData (Google_API_Client, Data_Set)
      return
   } // insertWeight

} // GoogleFit

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=kotlin foldmethod=syntax spell spelllang=en_gb :
