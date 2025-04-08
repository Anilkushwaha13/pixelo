package com.pixelo.pixelo.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

    public static Connection getCon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String db= "jdbc:mysql://localhost:3306/appusers";
            final String user="root";
            final String pass ="8446783531";

            Connection con = DriverManager.getConnection(db,user,pass);
            return con;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



}
