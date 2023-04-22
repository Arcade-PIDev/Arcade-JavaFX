/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Service;

import arcade.Entities.Produit;
import arcade.Utils.database;
import arcade.Service.IService;
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
public class ProduitService implements IService<Produit>{
    
    Connection con;
    Statement stm;

    public ProduitService() {
        con = database.getInstance().getCon();
    }
    
        
    public List<Produit> getProduitByCategorie(int id) throws SQLException {
        String req = "select * from `Produit` where categorie_id=" + id;
        stm = con.createStatement();
        ResultSet rs = stm.executeQuery(req);
        
        List<Produit> produits = new ArrayList<>();
        while (rs.next()) {

            Produit p = new Produit();
                p.setId(rs.getInt(1));
                p.setCategorie(rs.getInt(2));
                p.setNomProduit(rs.getString(3));
                p.setPrix(rs.getInt(4));
                p.setQuantiteStock(rs.getInt(5));
                p.setImage(rs.getString(6));
                p.setDescription(rs.getString(7));
                p.setCreationDate(rs.getDate(8));
                p.setModificationDate(rs.getDate(9));
                p.setIsEnabled(rs.getBoolean(10));
                produits.add(p);
            
        }
        return produits;
    }
    
    @Override
    public void ajouter(Produit t) throws SQLException {
        String req = "INSERT INTO `produit`(`categorie_id`, `nom_produit`, `prix`, `quantite_stock`, `image`, `description`,"
                + " `creation_date`, `is_enabled`) VALUES (?,?,?,?,?,?,NOW(),?)";
        PreparedStatement pstm = con.prepareStatement(req);

        pstm.setInt(1, t.getCategorie());
        pstm.setString(2, t.getNomProduit());
        pstm.setFloat(3, t.getPrix());
        pstm.setInt(4, t.getQuantiteStock());
        pstm.setString(5, t.getImage());
        pstm.setString(6, t.getDescription());
        pstm.setBoolean(7, true);
        pstm.executeUpdate();
    }
    
    @Override
    public List<Produit> afficher() throws SQLException{
       List<Produit> produits = new ArrayList<>();
        try {
             String req ="SELECT * FROM `produit` ";
             
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Produit p = new Produit();
                p.setId(rs.getInt(1));
                p.setCategorie(rs.getInt(2));
                p.setNomProduit(rs.getString(3));
                p.setPrix(rs.getInt(4));
                p.setQuantiteStock(rs.getInt(5));
                p.setImage(rs.getString(6));
                p.setDescription(rs.getString(7));
                p.setCreationDate(rs.getDate(8));
                p.setModificationDate(rs.getDate(9));
                p.setIsEnabled(rs.getBoolean(10));
                produits.add(p);
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return produits;
    }
    
    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM `Produit` WHERE `id`=?;";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, id);

        pstm.executeUpdate();
    }
        
    @Override
    public void update(Produit t) throws SQLException {
        String req = "UPDATE `Produit` SET `nom_produit`=" + t.getNomProduit()
                + ",`prix`=" + t.getPrix() + ",`quantite_stock`=" + t.getQuantiteStock()
                + ",`categorie_id`=" + t.getCategorie()
                + ",`description`=" + t.getDescription()
                + ",`modification_date`=NOW(),`is_enabled`=" + t.isIsEnabled() + " WHERE `id`=" + t.getId();
                
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.executeUpdate();
    }
    
    public void update2(Produit t) throws SQLException {
        String req = "UPDATE `Produit` SET `quantite_stock`=" + t.getQuantiteStock()+ " WHERE `id`=" + t.getId();
                
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.executeUpdate();
    }
    
    public Produit getProduitById(int id) throws SQLException {
        String req = "select * from `Produit` where id=" + id;
        stm = con.createStatement();
        ResultSet rs = stm.executeQuery(req);
            rs.next();
            Produit p = new Produit();
                p.setId(rs.getInt(1));
                p.setCategorie(rs.getInt(2));
                p.setNomProduit(rs.getString(3));
                p.setPrix(rs.getInt(4));
                p.setQuantiteStock(rs.getInt(5));
                p.setImage(rs.getString(6));
                p.setDescription(rs.getString(7));
                p.setCreationDate(rs.getDate(8));
                p.setModificationDate(rs.getDate(9));
                p.setIsEnabled(rs.getBoolean(10));
            
        return p;
    }
        
}
