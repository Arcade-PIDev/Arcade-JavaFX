/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author HP
 */
public class MyConnection {
       
    private static Connection cnx;
       private static String HOST = "localhost";
        private static int PORT = 3306;
        private static String DB_NAME = "ghada";
        private static String USERNAME = "root";
        private static String PASSWORD = "";
    public static MyConnection c;

    public static Connection MyConnection(){
        try {
            cnx = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  cnx;
    }

    public static MyConnection getInstance() {
        if (c == null) {
            c = new MyConnection();
        }
        return c;
    }

    public Connection getCnx() {
        return cnx;
    }

}
