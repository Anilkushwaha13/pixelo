package com.pixelo.pixelo.businessLogic;

import com.pixelo.pixelo.Base64CD.Base64Code;
import com.pixelo.pixelo.DataBase.UpdatAiImage;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Base64;


public class UpdateImage {

    private   static boolean getUpdateAiImage(String userName,String base64Image){
        try {
            System.out.println(base64Image);
            String type =base64Image.split(",")[0].split(";")[0].split("/")[1];
            String base64data = base64Image.split(",")[1];
            System.out.println("type"+type);
            byte[] bytes =  Base64.getDecoder().decode(base64data);
            boolean bol = UpdatAiImage.updateImage(userName,type,bytes);
            return bol;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
//
//    public static void main(String[] args) {
//               String userName = "as123@gmail.com";
//               String base64Image =  "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==";
//               System.out.println(getUpdateAiImage(userName,base64Image));
//    }
}
