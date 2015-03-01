#!/opt/local/bin/scala
########################################################### {{{1 ###########
#  Copyright © 2011 Martin Krischik
############################################################################
#  $Author: krischik $
#  $Revision: 6690 $
#  $Date: 2014-12-29 20:51:07 +0100 (Mo, 29. Dez 2014) $
#  $Id: Create-IC-Launcher.scala 6690 2014-12-29 19:51:07Z krischik $
#  $HeadURL: svn+ssh://krischik@svn.code.sf.net/p/uiq3/code/trunk/Java/src/main/scripts/Create-IC-Launcher.scala $
########################################################### }}}1 ###########
!#
val logger = java.util.logging.Logger.getLogger ("Create-IC-Launcher")

if (args.length != 1)
{
   logger.log (
      java.util.logging.Level.INFO, """
Create-IC-Launcher must be lauched in the res directory of your project and you need to
pass exacty one parameter: The source file to be converted. The source file must not be one
of the potential destination files.For best result the Input file should be sqare and larger then 144x144 pixel.""")
}
else
{
   val Input_File = new
	 java.io.File (args (0))

   /**
    * Convenience method that returns a scaled instance of the
    * provided `BufferedImage`.
    *
    * @param img the original image to be scaled
    * @param targetWidth the desired width of the scaled instance,
    *                    in pixels
    * @param targetHeight the desired height of the scaled instance,
    *                     in pixels
    * @param hint one of the rendering hints that corresponds to
    *             ` RenderingHints.KEY_INTERPOLATION` (e.g.
    *             ` RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR`,
    *             ` RenderingHints.VALUE_INTERPOLATION_BILINEAR`,
    *             ` RenderingHints.VALUE_INTERPOLATION_BICUBIC`)
    * @param higherQuality if true, this method will use a multi-step
    *                      scaling technique that provides higher quality than the usual
    *                      one-step technique (only useful in downscaling cases, where
    *                      ` targetWidth` or ` targetHeight` is
    *                      smaller than the original dimensions, and generally only when
    *                      the ` BILINEAR` hint is specified)
    * @return a scaled version of the original ` BufferedImage`
    */
   @scala.annotation.tailrec
   def getScaledInstance (
      img: java.awt.image.BufferedImage,
      targetWidth: Int,
      targetHeight: Int,
      hint: Object = java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC,
      higherQuality: Boolean = false): java.awt.image.BufferedImage =
   {
      val currentWidth = img.getWidth
      val currentHeight = img.getHeight

      if (higherQuality && (targetWidth > currentWidth || targetHeight > currentHeight))
      {
	 throw new
	       java.lang.IllegalArgumentException (
		  "higherQuality can only be true for downscaling")
      } // if

      if (currentWidth == targetWidth && currentHeight == targetHeight)
      {
	 // the scaling is finished, return the image
	 img
      }
      else
      {
	 def Half (value: Int, min: Int): Int =
	 {
	    val retval = value / 2

	    if (retval < min)
	    {
	       min
	    }
	    else
	    {
	       retval
	    } // return
	 } // Half
      val Type = if (img.getTransparency == java.awt.Transparency.OPAQUE)
	 {
	    java.awt.image.BufferedImage.TYPE_INT_RGB
	 }
	 else
	 {
	    java.awt.image.BufferedImage.TYPE_INT_ARGB
	 } // val
      val Size = if (higherQuality)
	 {
	    // Use multi-step technique: start with original size, then
	    // scale down in multiple passes with drawImage()
	    // until the target size is reached
	    (Half (img.getWidth, targetWidth), Half (img.getHeight, targetHeight))
	 }
	 else
	 {
	    // Use one-step technique: scale directly from original
	    // size to target size with a single drawImage() call
	    (targetWidth, targetHeight)
	 } // val
      val targetImage = new
	       java.awt.image.BufferedImage (
		  /* width     => */ Size._1,
		  /* height    => */ Size._2,
		  /* imageType => */ Type)
	 val graphics = targetImage.createGraphics ()
	 graphics.setRenderingHint (
	    /* hintKey   => */ java.awt.RenderingHints.KEY_INTERPOLATION,
	    /* hintValue => */ hint)
	 graphics.drawImage (
	    /* img      => */ img,
	    /* x        => */ 0,
	    /* y        => */ 0,
	    /* width    => */ Size._1,
	    /* height   => */ Size._2,
	    /* observer => */ null)
	 graphics.dispose ()

	 getScaledInstance (
	    img = targetImage,
	    targetWidth = targetWidth,
	    targetHeight = targetHeight,
	    hint = hint,
	    higherQuality = higherQuality)
      }
   } // getScaledInstance

   def Resize (Resolution: String, Size: Int): Unit =
   {
      val Output_Path = new
            java.io.File ("drawable-" + Resolution)
      val Output_File = new
            java.io.File (Output_Path, Input_File.getName)

      logger.log (java.util.logging.Level.INFO, "Read File  = {0}", Input_File)

      val Input_Image = javax.imageio.ImageIO.read (Input_File)
      val Character = getScaledInstance (
         img = Input_Image, targetWidth = Size, targetHeight = Size)

      logger.log (java.util.logging.Level.INFO, "Write File = {0}", Output_File)

      javax.imageio.ImageIO.write (Character, "png", Output_File)
   } // Resize

   val Base = 48

   (("mdpi", Base * 1.0) :: ("hdpi", Base * 1.5) :: ("xhdpi", Base * 2.0) :: ("xxhdpi", Base * 3.0) :: Nil).foreach
   {
      Set => Resize (Set._1, Set._2.toInt)
   } // foreach
}

// vim: set nowrap tabstop=8 shiftwidth=4 softtabstop=4 expandtab :
// vim: set textwidth=0 filetype=scala foldmethod=marker nospell :
