/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Service;

import arcade.Entities.Produit;
import arcade.Utils.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Amira
 */
public class WishlistService {
        public WishlistService() {
        con = database.getInstance().getCon();
    }

    Connection con;
    Statement stm;

    public void add(int prodId) throws SQLException {
        String req = "INSERT INTO `wishlist`(`produits_id`) VALUES (" + prodId +")";

        PreparedStatement pstm = con.prepareStatement(req);

        pstm.executeUpdate();
    }
    
      public void remove(int prodId) throws SQLException {
        String req = "DELETE FROM `wishlist` WHERE `produits_id`="+prodId;

        PreparedStatement pstm = con.prepareStatement(req);

        pstm.executeUpdate();
    }
    public List<Integer> afficher(int id) throws SQLException {
        String req = "Select `produits_id` from `wishlist` where `user_id` = " + id;
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Integer> prod = new ArrayList<>();
        while (rst.next()) {
            
            int c=rst.getInt(1);      
            prod.add(c);
            
        }
        return prod;
    }
}
