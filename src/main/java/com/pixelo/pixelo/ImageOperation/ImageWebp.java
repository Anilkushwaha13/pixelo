package com.pixelo.pixelo.ImageOperation;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.spi.IIORegistry;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.luciad.imageio.webp.WebPWriteParam;


public class ImageWebp {
    public static byte[] getWebp(BufferedImage image){
        ImageIO.scanForPlugins();
        IIORegistry.getDefaultInstance().registerServiceProvider(
                new com.luciad.imageio.webp.WebPImageWriterSpi()
        );


        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(out);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("webp");
            if (!writers.hasNext()){
                System.out.println("No writer was Found");
                return null;
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

    }

