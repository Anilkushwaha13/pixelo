package com.pixelo.pixelo.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class UpdatAiImage {
    public static boolean updateImage(String userName,String type,byte[] bytes){
        Connection con = null;
        PreparedStatement stat = null;

        try {
            con = ConnectionProvider.getCon();
            String sql = "insert into imageai values (?,?,?);";
            stat = con.prepareStatement(sql);
            stat.setString(1,userName);
            stat.setString(2, Arrays.toString(bytes));
            stat.setString(3,type);
            int result = stat.executeUpdate();

            if (result == 0){
                return false;
            }else return true;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
