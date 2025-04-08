package com.pixelo.pixelo.businessLogic;

import com.pixelo.pixelo.Base64CD.Base64Code;
import com.pixelo.pixelo.ImageOperation.ImageCompressor;
import com.pixelo.pixelo.ImageOperation.ImageCompressorWithQuality;
import com.pixelo.pixelo.ImageOperation.ImageTypeConvertor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class imageLogic {
    public static List<String> getCompressedImage(List<String> Base64Image){
        List<String> result = new ArrayList<>();
        try {

            for (String Base64Images: Base64Image){
                try {
                    String formateName = Base64Images.split(",")[0].split("/")[1].split(";")[0];
                    System.out.println(formateName);
                    String base64data = Base64Images.split(",")[1];
                    System.out.println(base64data);
                    BufferedImage image = Base64Code.getDecodeImage(base64data);
                    image = ImageCompressor.getCompressed(image, formateName);
                    result.add(Base64Code.getEncodeImage(image, formateName));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static List<String> getCompressedImageWithQuality(List<String> Base64Image,String Quality){
        List<String> result = new ArrayList<>();
        try {

            for (String Base64Images: Base64Image){
                String formateName = Base64Images.split(",")[0].split("/")[1].split(";")[0];
                String base64data = Base64Images.split(",")[1];
                BufferedImage image = Base64Code.getDecodeImage(base64data);
                try {

                image = ImageCompressorWithQuality.getCompress(image,formateName,Float.parseFloat(Quality));
                result.add(Base64Code.getEncodeImage(image,formateName));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<String> getConvert(List<String> Base64Image,String Type){
        List<String> result = new ArrayList<>();
        try {

            for (String Base64Images: Base64Image){
                String base64data = Base64Images.split(",")[1];
                BufferedImage image = Base64Code.getDecodeImage(base64data);
                try {
                    image = ImageTypeConvertor.getConvert(image,Type);

                } catch (Exception e) {
                if (Type.equalsIgnoreCase("jpeg") || Type.equalsIgnoreCase("jpg")) {
                    BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = rgbImage.createGraphics();
                    g.drawImage(image, 0, 0, Color.WHITE, null);
                    g.dispose();
                    image = rgbImage;
                    image = ImageTypeConvertor.getConvert(image,Type);
                } else System.out.println(e.getMessage());
                }
                result.add(Base64Code.getEncodeImage(image,Type));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
