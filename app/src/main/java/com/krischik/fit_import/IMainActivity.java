/**********************************************************
 * {{{1 ***********
 * Copyright © 2011 … 2012 Noser Engineering
 * **************************************************************************
 * $$Author$$
 * $$Revision$$
 * $$Date$$
 * $$Id$$
 * $$HeadURL$$
 * ********************************************************* }}}1
 **********/

package com.krischik.fit_import;

/**
 * <p>
 * </p>
 *
 * ${tags}
 * @author krma1
 * @version 1.0
 * @since 1.0
 */
public interface IMainActivity
{
   /**
    * <p>Google FIT Model</p>
    */
   @org.jetbrains.annotations.NotNull GoogleFit getGoogleFit ();

   /**
    * <p>remember if we are connected</p>
    *
    * @return true when we are connected
    */
   boolean isConnected () // doDisconnect
   ;
} // IMainActivity

// vim: set nowrap tabstop=8 shiftwidth=3 softtabstop=3 expandtab textwidth=96 :
// vim: set fileencoding=utf-8 filetype=java foldmethod=syntax spell spelllang=en_gb :