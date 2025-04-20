package com.pixelo.pixelo.ImageOperation;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageTypeConvertor {
    public   static BufferedImage getConvert(BufferedImage img, String convertFormate) {
        try {
            ByteArrayOutputStream bimge = new ByteArrayOutputStream();
            ImageOutputStream image = ImageIO.createImageOutputStream(bimge);
           ImageIO.write(img , convertFormate,image);

            ByteArrayInputStream bimg2 = new ByteArrayInputStream(bimge.toByteArray());
            return ImageIO.read(bimg2);

        } catch (Exception e) {
            System.out.println("error2 "+e.getMessage());
               return null;
        }

    }
}
