/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Service;

import arcade.Entities.Jeux;
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
public class JeuxService implements IService<Jeux>{
    
    Connection con;
    Statement stm;

    public JeuxService() {
        con = database.getInstance().getCon();
    }
    
    @Override
    public void ajouter(Jeux c) throws SQLException {
        String req = "INSERT INTO `jeux`(`nom`, `description`, `image`, `genre`, `color`) VALUES (?,?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, c.getNom());
        pstm.setString(2, c.getDescription());
        pstm.setString(3, c.getImage());
        pstm.setString(4, c.getGenre());
        pstm.setString(5, c.getColor());
        pstm.executeUpdate();
    }
    
    @Override
    public List<Jeux>  afficher() throws SQLException{
       List<Jeux> personnes = new ArrayList<>();
        try {
             String req ="SELECT * FROM `jeux` ";
             
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Jeux p = new Jeux();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setImage(rs.getString(4));
                p.setGenre(rs.getString(5));
                p.setColor(rs.getString(6));
                
                personnes.add(p);
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return personnes;
        
    }
    
        public void delete(int id) throws SQLException {
        String req = "DELETE FROM `jeux` WHERE `id`=?;";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, id);

        pstm.executeUpdate();
    }
        
    @Override
    public void update(Jeux t) throws SQLException {
         
        String req = "UPDATE `jeux` SET `nom`='" + t.getNom()
                + "',`description`='" + t.getDescription() + "',`genre`='" + t.getGenre()+"',`color`='" + t.getColor()+  "' WHERE `id`=" + t.getId() + "";

        PreparedStatement pstm = con.prepareStatement(req);
        pstm.executeUpdate();
    }

    @Override
    public void modifier(int id, Jeux c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Jeux c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
