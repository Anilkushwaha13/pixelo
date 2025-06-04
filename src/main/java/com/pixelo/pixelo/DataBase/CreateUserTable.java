package com.pixelo.pixelo.DataBase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;

public class CreateUserTable {
    public static void main(String[] args) {

        try(  Connection con = ConnectionProvider.getCon();
              Statement firstState = con.createStatement()
        ){
            int createResult = firstState.executeUpdate("CREATE TABLE imageai (" +
                    "    email VARCHAR(200)," +
                    "    imagedata LONGBLOB ," +
                    "    imageType VARCHAR(20) not null," +
                    "    CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES appuser(email)" +
                    "`time` VARCHAR(45)"+
                    ");");
//            int createResult = firstState.executeUpdate("CREATE TABLE draftImage (" +
//                    "COLUMN `id` INT NOT NULL AUTO_INCREMENT FIRST,\n" +
//                    "ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE    " +
//                    "email VARCHAR(200)," +
//                    "    imagedata LONGBLOB ," +
//                    "    imageType VARCHAR(20) not null," +
//                    "    CONSTRAINT fk1_email FOREIGN KEY (email) REFERENCES appuser(email)," +
//                    " `time` VARCHAR(45)" +
//                    ");");
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
        }

        }
    }