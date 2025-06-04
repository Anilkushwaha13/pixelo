package com.pixelo.pixelo.businessLogic;

import com.pixelo.pixelo.Base64CD.Base64Code;
import com.pixelo.pixelo.DataBase.draftImageUpdate;

import java.awt.image.BufferedImage;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class draftImageUser {
    public static boolean draft(String email, String base64Image){
        try {
            System.out.println(base64Image);
            String type =base64Image.split(",")[0].split(";")[0].split("/")[1];
            String base64data = base64Image.split(",")[1];
            System.out.println("type"+type);
            byte[] bytes =  Base64.getDecoder().decode(base64data);
            System.out.println(bytes);
            boolean bol = draftImageUpdate.draftImage(email,type,bytes);
            return bol;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Map<Integer, String> getDraft(String email){
        Map<Integer,BufferedImage> list = draftImageUpdate.getDraftImage(email);
        Map<Integer,String> encodedImage = new HashMap<>();

        for ( Map.Entry<Integer, BufferedImage> entry : list.entrySet()){
            String image = Base64Code.getEncodeImage(entry.getValue(),"png");
            encodedImage.put(entry.getKey(),image);
        }

        return encodedImage;
    }
    public static boolean deleteDraft(String email, int id) {
        return draftImageUpdate.deleteDraftImage(email,id);

    }


//    public static void main(String[] args) {
////               String userName = "as123@gmail.com";
////               String base64Image =  "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==";
////               System.out.println(draft(userName,base64Image));
//               ArrayList<?> list =getDraft("as123@gmail.com");
//        System.out.println(list);
//    }
}
