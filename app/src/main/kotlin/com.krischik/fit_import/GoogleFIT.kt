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

import android.os.Bundle

/**
 * <p>
 * </p>
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 */

class GoogleFit (val owner: IMainFragment) :
   com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
{

   class object
   {
      /**
       * Logging tag
       */
      private val TAG = javaClass<GoogleFit>().getName()
   }

   [hugo.weaving.DebugLog]
   override fun onConnected(bundle: Bundle?)
   {
      android.util.Log.i (TAG, "LOG00010: Connected to Google-Fit!");
      // Now you can make calls to the Fitness APIs.
      // Put application specific code here.

      return;
   } // onConnected

   [hugo.weaving.DebugLog]
   override fun onConnectionSuspended(i: Int)
   {
      // If your connection to the sensor gets lost at some point,
      // you'll be able to determine the reason and react to it here.
      if (i == com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST)
      {
	 android.util.Log.e (TAG, "LOG00020: Google-Fit connection lost.  Cause: Network Lost.");
      }
      else if (i == com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED)
      {
	 android.util.Log.e (TAG, "LOG00030: Google-Fit Connection lost.  Reason: Service Disconnected");
      } // if

      return;
   } // onConnectionSuspended

}
