package com.pixelo.pixelo.DataBase;

import com.pixelo.pixelo.businessLogic.PasswordEncrypter;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseUpdate {

    public static Boolean getRegister(String Username,String number, String email ,String password){

        Connection con = null;
        PreparedStatement input = null;
        try {
             con = ConnectionProvider.getCon();
             String sql = "INSERT into appuser values  (?,?,?,?)";
             input = con.prepareStatement(sql);
             input.setString(1,email);
             input.setString(2,Username);
             input.setString(3,number);
             input.setString(4, PasswordEncrypter.hashPassword(password));

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
    public static Boolean getUpdate(String email ,String name){

        Connection con = null;
        PreparedStatement input = null;
        try {
             con = ConnectionProvider.getCon();
             String sql = "Update  appuser name=? Where email=?)";
             input = con.prepareStatement(sql);
             input.setString(1,name);
             input.setString(2,email);

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

    public static void main(String[] args) {
        Boolean register= getRegister("as123@gmail.com","Abhi","5456464","4646654dfg");
        System.out.println(register);
    }
}
