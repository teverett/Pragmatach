package com.khubla.pragmatach.plugin.responsive;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class ImageResizer {
   public static void resize(InputStream inputImage, OutputStream outputImage, int width, int height) throws PragmatachException {
      try {
         final BufferedImage bufferedImage = ImageIO.read(inputImage);
         final Image scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
         final BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         final Graphics g = imageBuff.createGraphics();
         g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
         g.dispose();
         ImageIO.write(imageBuff, "png", outputImage);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in resize", e);
      }
   }
}
