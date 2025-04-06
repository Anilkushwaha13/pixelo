package com.pixelo.pixelo.ImageOperation;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.twelvemonkeys.imageio.plugins.webp.WebPImageReaderSpi;




public class ImageTypeConvertor {
    public   static BufferedImage getConvert(BufferedImage img, String convertFormate) {
        BufferedImage image1 = null;
        ImageIO.scanForPlugins();
        try {
            ByteArrayOutputStream bimge = new ByteArrayOutputStream();
            ImageOutputStream image = ImageIO.createImageOutputStream(bimge);

            ImageIO.write(img , convertFormate,image);
            ByteArrayInputStream bimg2 = new ByteArrayInputStream(bimge.toByteArray());

            image1 = ImageIO.read(bimg2);

        } catch (IOException e) {
            System.out.println("error2 "+e.getMessage());
        }
        return image1 ;
    }
}
