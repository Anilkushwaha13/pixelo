package com.pixelo.pixelo.DataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.pixelo.pixelo.MyCredential.CredentailsStore.*;


public class ConnectionProvider {

    public static Connection getCon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
             final String db= db_repo.getValue(); // Replace with your own value
             final String user=db_user.getValue();// Replace with your own value
             final  String pass =db_pass.getValue();// Replace with your own value
            Connection con = DriverManager.getConnection(db,user,pass);
            return con;

        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
            return null;
        }
    }

//    public static void main(String[] args) {
//        Connection con = null;
//        try {
//           con =getCon();
//           if(con!=null){
//               System.out.println("connected to database");
//           }
//        } catch (Exception e) {
//            System.out.println("connection failed");
//            throw new RuntimeException(e);
//
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }


}
