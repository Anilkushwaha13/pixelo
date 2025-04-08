package com.pixelo.pixelo.businessLogic;

import com.pixelo.pixelo.DataBase.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseUpdate {

    public static Boolean getRegister(String email ,String Username,String number, String password){

        Connection con = null;
        PreparedStatement input = null;
        try {
             con = ConnectionProvider.getCon();
             String sql = "INSERT into appuser values  (?,?,?,?)";
             input = con.prepareStatement(sql);
             input.setString(1,email);
             input.setString(2,Username);
             input.setString(3,number);
             input.setString(4,password);

            int result = input.executeUpdate();

            if(result == 0){
                return true;
            }
            else return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                input.close();
                con.close();


            } catch (Exception e) {
                System.out.println(e);
                System.out.println(System.currentTimeMillis());
            }

        }
    }

//    public static void main(String[] args) {
//        Boolean register= getRegister("as123@gmail.com","Abhi","5456464","4646654dfg");
//        System.out.println(register);
//    }
}
