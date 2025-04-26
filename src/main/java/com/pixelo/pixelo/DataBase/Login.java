package com.pixelo.pixelo.DataBase;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class Login {
    public Map<String,String> getLogin(String EmailOrNumber){
        Connection con = null;
        PreparedStatement stat = null;

        try {
            con = ConnectionProvider.getCon();
            String sql = "Select * From appuser where email=? ";
            stat = con.prepareStatement(sql);
            stat.setString(1,EmailOrNumber);
//            stat.setString(2,password);
            ResultSet result = stat.executeQuery();
            if (result.next()){
                Map<String,String> userData = new HashMap<>();
                    String name = result.getString("name");
                    String username = result.getString("email");
                    String password = result.getString("password");
                    userData.put("name",name);
                    userData.put("username",username);
                    userData.put("password",password);
                    return  userData;
            }
            else return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
