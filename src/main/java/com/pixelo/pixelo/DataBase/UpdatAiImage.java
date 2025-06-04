package com.pixelo.pixelo.DataBase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

public class UpdatAiImage {
    public static boolean updateImage(String userName,String type,byte[] bytes){
        Connection con = null;
        PreparedStatement stat = null;

        try {
            con = ConnectionProvider.getCon();
            String sql = "insert into imageai values (?,?,?,?);";
            stat = con.prepareStatement(sql);
            stat.setString(1,userName);
            stat.setBytes(2,bytes);
            stat.setString(3,type);
            stat.setString(4,String.valueOf(new Timestamp(System.currentTimeMillis())));
            int result = stat.executeUpdate();

            if (result == 0){
                return false;
            }else return true;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<BufferedImage> getAiImage(int req){
        Connection con = null;
        PreparedStatement stat = null;
        int image = 10;
        int offset = req * image;
      ArrayList<BufferedImage> list = new ArrayList<>();
        try {
            con = ConnectionProvider.getCon();
            String sql = "SELECT * FROM appusers.imageai limit ? offset ?;";
            stat = con.prepareStatement(sql);
            stat.setInt(1,image);
            stat.setInt(2,offset);
            System.out.println(stat);
            ResultSet result = stat.executeQuery();
            while (result.next()){
                byte[] bytes= result.getBytes("imageData");
                String type = result.getString("imageType");
                ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                BufferedImage img = ImageIO.read(in);
                list.add(img);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }

}
