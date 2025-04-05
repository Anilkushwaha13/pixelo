package com.pixelo.pixelo.ImageOperation;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImageCompressor {
    public static BufferedImage getCompressed(BufferedImage image, String imageFormate){

        try {
            ByteArrayOutputStream bimge = new ByteArrayOutputStream();
            ImageOutputStream img = ImageIO.createImageOutputStream(bimge);

            ImageIO.write(image,imageFormate, img);

            ByteArrayInputStream bimg2 = new ByteArrayInputStream(bimge.toByteArray());
            BufferedImage outputFile = ImageIO.read(bimg2);

            return outputFile;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
