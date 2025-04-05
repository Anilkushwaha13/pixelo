package com.pixelo.pixelo.ImageToPdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;

public class PdfMaker {

    public  static byte[] makePdf(ArrayList<String> base64Image){
        return getpdf(base64Image);
    }

   private static byte[] getpdf(ArrayList<String> base64Image){
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            for (int i = 0; i <base64Image.size() ; i++) {


                byte[] imageBytes = Base64.getDecoder().decode(base64Image.get(i));

                ImageData image = ImageDataFactory.create(imageBytes);

                if (i>0){
                    pdfDoc.addNewPage();
                }

                Image img = new Image(image);
                img.setAutoScaleHeight(true);
                img.setAutoScaleWidth(true);

                document.add(img);

            }
          return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
