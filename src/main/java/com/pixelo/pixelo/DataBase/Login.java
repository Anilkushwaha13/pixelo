package com.pixelo.pixelo.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Login {
    public static ArrayList<String> getLogin(String EmailOrNumber,String password){
        Connection con = null;
        PreparedStatement stat = null;

        try {
            con = ConnectionProvider.getCon();
            String sql = "Select * From appuser where email=? and password =?";
            stat = con.prepareStatement(sql);
            stat.setString(1,EmailOrNumber);
            stat.setString(2,password);
            ResultSet result = stat.executeQuery();
            if (result.next()){
                ArrayList<String> Userdata = new ArrayList<>();
                    String name = result.getString("name");
                    String username = result.getString("email");
                    Userdata.add(name);
                    Userdata.add(username);
                    return  Userdata;
            }
            else return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
