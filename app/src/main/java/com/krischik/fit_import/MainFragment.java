/**********************************************************
 * {{{1 *********** Copyright © 2015 "Martin Krischik" «krischik@users.sourceforge.net»
 * ************************************************************************** This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program.  If not, see
 * http://www.gnu.org/licenses/ ********************************************************* }}}1
 **********/

package com.krischik.fit_import;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p> </p>
 * <p/>
 * ${tags}
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 */
@org.androidannotations.annotations.EFragment (R.layout.main_fragment)
public class MainFragment
   extends android.support.v4.app.Fragment
   implements IMainFragment
{
   /**
    * <p> TAG as class name for logging </p>
    */
   private final static String TAG = MainFragment.class.getName ();
   /**
    * <p>Google FIT Model</p>
    */
   private GoogleFit Google_Fit;
   /**
    * <p>Import Ketfit CVS button</p>
    */
   @org.androidannotations.annotations.ViewById
   @Nullable
   protected android.widget.Button Ketfit_Button;
   /**
    * <p>Import Withings CVS button</p>
    */
   @org.androidannotations.annotations.ViewById
   @Nullable
   protected android.widget.Button Withings_Button;

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
      if (Ketfit_Button != null)
      {
         Ketfit_Button.setEnabled (connected);
      }
      if (Withings_Button != null)
      {
	 Withings_Button.setEnabled (connected);
      }
      return;
   } // doConnect

   /**
    * <p>the import withings button has been clicked.</p>
    *
    */
   @hugo.weaving.DebugLog
   @org.androidannotations.annotations.Click (R.id.Withings_Button)
   @Override
   public void Do_Withings_Button ()
   {
      if (Google_Fit != null)
      {
      }
      else
      {
         android.util.Log.e (TAG, "LOG00010: No Google_Fit instance!");
      }

      return;
   } // doConnect

   /**
    * <p>the import ketfit button has been clicked.</p>
    *
    */
   @hugo.weaving.DebugLog
   @org.androidannotations.annotations.Click (R.id.Ketfit_Button)
   @Override
   public void Do_Ketfit_Button ()
   {
      if (Google_Fit != null)
      {
	 Google_Fit.Insert_Training (new Ketfit (
              /* Start  => */new java.util.Date (),
              /* End    => */new java.util.Date (),
              /* Watt   => */130,
              /* Puls   => */140,
              /* Umin   => */70,
              /* kCal   => */500,
              /* km     => */6,
              /* ω      => */0));
      }
      else
      {
	 android.util.Log.e (TAG, "LOG00000: No Google_Fit instance!");
      }

      return;
   } // doConnect

   public void setGoogle_Fit (@NotNull GoogleFit google_Fit)
   {
      java.util.Objects.requireNonNull (google_Fit);

      Google_Fit = google_Fit;

      return;
   }
} // MainFragment

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :