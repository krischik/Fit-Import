/********************************************************** {{{1 ***********
 * Copyright © 2011 … 2012 Noser Engineering
 ***************************************************************************
 * $$Author$$
 * $$Revision$$
 * $$Date$$
 * $$Id$$
 * $$HeadURL$$
 ********************************************************** }}}1 **********/

package com.krischik.fit_import;

/**
 * <p> </p>
 * <p/>
 * ${tags}
 *
 * @author martin
 * @version 1.0
 * @since 1.0
 */
@org.androidannotations.annotations.EActivity (R.layout.main_activity)
public class MainActivity
   extends android.support.v7.app.ActionBarActivity
{
   private final static String TAG;

   static
   {
      TAG = MainActivity.class.getName ();
   } // static

   @hugo.weaving.DebugLog
   @org.androidannotations.annotations.AfterViews
   public void afterViews ()
   {
      return;
   }
} // MainActivity

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :