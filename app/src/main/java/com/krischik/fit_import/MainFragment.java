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
   //private final static String TAG = MainFragment.class.getName ();

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
    * <p>we are connected to Google Fit (or not);
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
} // MainFragment

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :