package com.pixelo.pixelo.DataBase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;

public class CreateUserTable {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        Connection con = null;
        Statement firstState = null;
        try {
            con = ConnectionProvider.getCon();
            firstState = con.createStatement();
//            int createResult = firstState.executeUpdate("CREATE TABLE imageai (" +
//                    "    email VARCHAR(200)," +
//                    "    imagedata LONGBLOB ," +
//                    "    imageType VARCHAR(20) not null," +
//                    "    CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES appuser(email)" +
//                    ");");
            int createResult = firstState.executeUpdate("CREATE TABLE draftImage (" +
                    "    email VARCHAR(200)," +
                    "    imagedata LONGBLOB ," +
                    "    imageType VARCHAR(20) not null," +
                    "    CONSTRAINT fk1_email FOREIGN KEY (email) REFERENCES appuser(email)" +
                    ");");
//            int createResult = firstState.executeUpdate("CREATE TABLE appuser ("
//                    + "email VARCHAR(200) PRIMARY KEY,  "
//                    + "name VARCHAR(200), "
//                    + "password VARCHAR(200) )");
//
            if (createResult == 0) {  // executeUpdate() returns 0 for DDL statements
                JOptionPane.showMessageDialog(null, "Table created successfully");
            }

//              Drop table (if needed)
//            int dropResult = firstState.executeUpdate("DROP TABLE imageai;");
//            if (dropResult == 0) {
//                JOptionPane.showMessageDialog(null, "Table dropped successfully");
//            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                firstState.close();
                con.close();


            } catch (Exception e) {
                System.out.println(e);
                System.out.println(System.currentTimeMillis());
            }

        }
    }
}