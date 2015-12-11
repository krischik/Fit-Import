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

package com.krischik.fit_import;

/**
 * <p>Main Fragment</p>
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 */
public interface IMainFragment
{
   /**
    * <p>we are connected to Google Fit (or not);
    *
    * @param connected true when we are connected
    */
   public abstract void doConnect (boolean connected);

   /**
    * <p>the activity</p>
    * @return actvivity
    */
   public android.support.v4.app.FragmentActivity getActivity();

   /**
    * <p>the import withings button has been clicked.</p>
    *

    */
   public void doWithingsButton ();
   /**
    * <p>the import ketfit button has been clicked.</p>
    *
    */
   public void doKetfitButton ();

} // IMainFragment

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :