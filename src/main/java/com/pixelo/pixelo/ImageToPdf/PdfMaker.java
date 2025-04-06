package com.pixelo.pixelo.ImageToPdf;

//import org.apache.commons.codec.binary.Base64;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;

public class PdfMaker {

    public static byte[] makePdf(ArrayList<String> base64Image){
        return getpdf(base64Image);
    }

     private static byte[] getpdf(ArrayList<String> base64Image){
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            for (int i = 0; i <base64Image.size() ; i++) {
                String  base64Images = base64Image.get(i);
                if (base64Images.contains(",")) {
                   base64Images = base64Images.split(",")[1];
                }
//                base64Images = base64Images.replaceAll("\\s+", "").trim();
//                byte[] imageBytes = Base64.decodeBase64(base64Images);

               byte[] imageBytes = Base64.getDecoder().decode(base64Images);
                ImageData image = ImageDataFactory.create(imageBytes);

//                if (i>0){
//                    pdfDoc.addNewPage();
//                }

                Image img = new Image(image);
                System.out.println(img);
                img.setAutoScaleHeight(true);
                img.setAutoScaleWidth(true);
                document.add(img);
            }
            document.close();

           return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
