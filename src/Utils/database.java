/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Amira
 */
public class database {
 
    final String url ="jdbc:mysql://localhost:3306/arcade";
    final String user="root";
    final String password="";
    
    static database instance;
    private Connection con;
    
    private database(){
        try {
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("connection established");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static database getInstance(){
    if (instance == null)
        instance = new database();
    return instance;
    }

    public Connection getCon() {
        return con;
    }
    
}
