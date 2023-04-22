/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Service;

import arcade.Utils.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import arcade.Entities.Panier;
import arcade.Entities.Commande;

/**
 *
 * @author Amira
 */
public class PanierService {
    
        public PanierService() {
        con = database.getInstance().getCon();
        }

    Connection con;
    Statement stm;
      public void addCart(Panier t) throws SQLException {
        String req = "INSERT INTO `panier`(`id`, `produits_id`, `commandes_id`, `quantite`) VALUES ('?','?',NULL,?)";
          System.out.println("req  " + req);
        PreparedStatement pstm = con.prepareStatement(req);
                pstm.setInt(1, t.getId());
                pstm.setInt(2, t.getProduit());
                pstm.setInt(3, t.getQuantite());
    }
      
      public void ajouterCommande(Commande t) throws SQLException {
        String req = "INSERT INTO `commande`(`users_id`, `prix_total`, `is_paid`, `is_canceled`) VALUES "
                + "(null,"+t.getPrixTotal()+",'0','0')";
        
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
    }
}
