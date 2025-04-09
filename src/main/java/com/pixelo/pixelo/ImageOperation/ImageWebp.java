package com.pixelo.pixelo.ImageOperation;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.twelvemonkeys.imageio.plugins.webp.WebPImageReaderSpi;

public class ImageWebp {
    public static byte[] getWebp(BufferedImage image){
        ImageIO.scanForPlugins();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(out);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("image/webp");
            if (!writers.hasNext()){
                System.out.println("No writer was Found");
            }
            ImageWriter writer = writers.next();
            writer.setOutput(ios);
            writer.write(image);
            ios.close();
            writer.dispose();

            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//
//    public static void main(String[] args) {
//        BufferedImage inputImage = null;
//        try {
//            inputImage = ImageIO.read(new File("C:/Users/kushw/Downloads/1.jpeg"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        byte[] webpBytes = getWebp(inputImage);
//
//        // Optional: write to file
//        try (FileOutputStream fos = new FileOutputStream("C:/Users/kushw/OneDrive/Desktop/Output/output.webp")) {
//            fos.write(webpBytes);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("WebP conversion complete. Size: " + webpBytes.length + " bytes.");
//    }
    }

