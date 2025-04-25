package com.pixelo.pixelo.DataBase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class draftImageUpdate {
    public static boolean draftImage(String userName,String type,byte[] bytes){
        Connection con = null;
        PreparedStatement stat = null;

        try {
            con = ConnectionProvider.getCon();
            String sql = "insert into draftImage values (?,?,?);";
            stat = con.prepareStatement(sql);
            stat.setString(1,userName);
            stat.setBytes(2,bytes);
            stat.setString(3,type);
            int result = stat.executeUpdate();

            if (result == 0){
                return false;
            }else return true;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<BufferedImage> getDraftImage(String userName){
        Connection con = null;
        PreparedStatement stat = null;
        ArrayList<BufferedImage> list = new ArrayList<>();
        try {
            con = ConnectionProvider.getCon();
            String sql = "SELECT * FROM appusers.draftImage where email=?;";
            stat = con.prepareStatement(sql);
            stat.setString(1,userName);
            System.out.println(stat);
            ResultSet result = stat.executeQuery();
            while (result.next()){
                byte[] bytes= result.getBytes("imageData");
//                String type = result.getString("imageType");
                ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                BufferedImage img = ImageIO.read(in);
                list.add(img);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        return  list;
    }
}
