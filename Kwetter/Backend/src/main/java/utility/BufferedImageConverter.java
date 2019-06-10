/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author Jeroen Roovers
 */
public class BufferedImageConverter {

    /**
     * Static helper method to turn raw bytes into a buffered image.
     *
     * @param imageData bytes of image
     * @return buffered image
     */
    public static BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Static helper method to convert image to byte array
     *
     * @param image
     * @return Byte[] object with image data
     */
    public static Byte[] getBytesFromImage(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            Byte[] bytes = new Byte[imageInByte.length];
            Arrays.setAll(bytes, n -> imageInByte[n]);
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
