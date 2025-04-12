package com.pixelo.pixelo.APICaller;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.util.Iterator;

public class detectImageFormate {
    public static String getFormate(File inputFile) {
        try (ImageInputStream input = ImageIO.createImageInputStream(inputFile)){
            Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(input);
            if (imageReaders.hasNext()){
                return  imageReaders.next().getFormatName();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}