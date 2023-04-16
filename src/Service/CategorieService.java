/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Categorie;
import Utils.database;
import Service.IService;
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
public class CategorieService implements IService<Categorie>{
    
    Connection con;
    Statement stm;

    public CategorieService() {
        con = database.getInstance().getCon();
    }
    
    @Override
    public void ajouter(Categorie c) throws SQLException {
        String req = "INSERT INTO `categorie`(`nom_categorie`, `description`, `image`, `creation_date`, `modification_date`, `is_enabled`) VALUES (?,?,?,NOW(),null,?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, c.getNomCategorie());
        pstm.setString(2, c.getDescription());
        pstm.setString(3, c.getImage());
        pstm.setBoolean(4, true);
        pstm.executeUpdate();
    }
    
    @Override
    public List<Categorie>  afficher() throws SQLException{
       List<Categorie> personnes = new ArrayList<>();
        try {
             String req ="SELECT * FROM `Categorie` ";
             
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Categorie p = new Categorie();
                p.setId(rs.getInt(1));
                p.setNomCategorie(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setImage(rs.getString(4));
                p.setCreationDate(rs.getDate(5));
                p.setModificationDate(rs.getDate(6));
                p.setIsEnabled(rs.getBoolean(7));
                personnes.add(p);
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return personnes;
        
    }
    
        public void delete(int id) throws SQLException {
        String req = "DELETE FROM `categorie` WHERE `id`=?;";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, id);

        pstm.executeUpdate();
    }
        
    @Override
    public void update(Categorie t) throws SQLException {

        String req = "UPDATE `categorie` SET nom_categorie='" + t.getNomCategorie()
                + "',description='" + t.getDescription() + "',modification_date=NOW(),is_enabled=" + t.isIsEnabled() + " WHERE id=" + t.getId() + "";

        PreparedStatement pstm = con.prepareStatement(req);
        pstm.executeUpdate();
    }
}
