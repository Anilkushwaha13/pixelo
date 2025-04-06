package com.pixelo.pixelo.Base64CD;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class Base64Code {
    public   static String getEncodeImage(BufferedImage image, String formateName){

        try {
            ByteArrayOutputStream bose = new ByteArrayOutputStream();
            ImageIO.write(image,formateName,bose);
            byte[] bytes = bose.toByteArray();
            String Image1 = Base64.getEncoder().encodeToString(bytes);
            String encodedImage="data:image/"+formateName+";base64,"+Image1;
            return encodedImage;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static BufferedImage getDecodeImage(String img) throws Exception{
        byte[] bytes = Base64.getDecoder().decode(img);
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        BufferedImage image =ImageIO.read(bin);
        return image;
    }
}
