package com.pixelo.pixelo.ImageOperation;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImageTypeConvertor {
    public   static BufferedImage getConvert(BufferedImage img, String converFormate) throws Exception{
        ByteArrayOutputStream bimge = new ByteArrayOutputStream();
        ImageOutputStream image = ImageIO.createImageOutputStream(bimge);

        ImageIO.write(img , converFormate,image);
        ByteArrayInputStream bimg2 = new ByteArrayInputStream(bimge.toByteArray());
        BufferedImage image1 = null;
        image1 = ImageIO.read(bimg2);
        return image1 ;
    }
}
