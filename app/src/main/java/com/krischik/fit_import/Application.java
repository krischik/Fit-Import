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
@org.androidannotations.annotations.EApplication
public class Application
   extends android.app.Application
{
   /**
    * <p> TAG as class name for logging </p>
    */
   private final static String TAG;

   static
   {
      TAG = Application.class.getName ();
   } // static
} // Application

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :