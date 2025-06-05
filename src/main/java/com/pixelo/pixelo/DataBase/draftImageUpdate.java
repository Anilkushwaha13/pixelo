package com.pixelo.pixelo.DataBase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class draftImageUpdate {
    public static boolean draftImage(String userName, String type, byte[] bytes) {
        Connection con = null;
        PreparedStatement stat = null;

        try {

            con = ConnectionProvider.getCon();

            String sql1 = "SELECT  COUNT(*) count  FROM appusers.draftimage where email = ?";
            stat = con.prepareStatement(sql1);
            stat.setString(1, userName);
            ResultSet rs = stat.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("count");
            }
            if (count < 3) {

                String sql = "insert into draftImage (email, imagedata, imageType, time) values (?,?,?,?);";
                stat = con.prepareStatement(sql);
                stat.setString(1, userName);
                stat.setBytes(2, bytes);
                stat.setString(3, type);
                stat.setString(4, String.valueOf(new Timestamp(System.currentTimeMillis())));
                int result = stat.executeUpdate();

                if (result == 0) {
                    return false;
                } else return true;
            } else throw new RuntimeException("Limit has been reached for Draft");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Integer, BufferedImage> getDraftImage(String userName) {
        Connection con = null;
        PreparedStatement stat = null;
        Map<Integer, BufferedImage> image = new HashMap<>();
        try {
            con = ConnectionProvider.getCon();
            String sql = "SELECT * FROM appusers.draftImage where email=?;";
            stat = con.prepareStatement(sql);
            stat.setString(1, userName);
            System.out.println(stat);
            ResultSet result = stat.executeQuery();
            while (result.next()) {
                int i;
                byte[] bytes = result.getBytes("imageData");
                int id = result.getInt("id");
//                String type = result.getString("imageType");
                ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                BufferedImage img = ImageIO.read(in);
                image.put(id, img);


            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        } finally {
            try {
                stat.close();
                con.close();


            } catch (Exception e) {
                System.out.println("error:" + e);
                System.out.println(System.currentTimeMillis());
            }
            return image;
        }
    }

    public static boolean deleteDraftImage(String email, int id) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = ConnectionProvider.getCon();
            String sql = "Delete FROM appusers.draftImage where email=? and id = ?;";
            stat = con.prepareStatement(sql);
            stat.setString(1, email);
            stat.setInt(2, id);
            int result = stat.executeUpdate();

            if (result == 0) {
                return false;
            } else return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stat.close();
                con.close();


            } catch (Exception e) {
                System.out.println("error:" + e);
                System.out.println(System.currentTimeMillis());
            }
        }
    }
}
