/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeine
 */
public class ServiceEvenement implements IService<Evenement> {
    
     Connection cnx;
    
    public  ServiceEvenement(){
        cnx =MyDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Evenement t) {
        try {
            String qry = "INSERT INTO `Evenement`( `nom_event`, `lieu`, `affiche_e`, `description_event`, `prix_ticket`, `nbr_places`,`date_debut_e`, `date_fin_e`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(qry);
           
            ps.setString(1, t.getNomEvent());
            ps.setString(2, t.getLieu());
            ps.setString(3, t.getAfficheE());
            ps.setString(4, t.getDescriptionEvent());
            ps.setFloat(5, t.getPrixTicket());
            ps.setInt(6, t.getNbrPlaces());
            ps.setDate(7, (Date) t.getDateDebutE());
            ps.setDate(8, (Date) t.getDateFinE());

            ps.executeUpdate();
            System.out.println("Evenement ajouté ");
        } catch (SQLException ex) {
            System.out.println("Erreur: evenement n'est pas ajouté");

            System.out.println(ex.getMessage());
        }

    }
    
      @Override
    public List<Evenement> afficher() {
       List<Evenement> evenements = new ArrayList<>();
         
        try {
             String req ="SELECT * FROM `Evenement` ";
            
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setDateDebutE(rs.getDate("date_debut_e"));
                e.setDateFinE(rs.getDate("date_fin_e"));
                e.setAfficheE(rs.getString("affiche_e"));
                e.setPrixTicket(rs.getFloat("prix_ticket"));
                e.setNbrPlaces(rs.getInt("nbr_places"));
                e.setDescriptionEvent(rs.getString("description_event"));
                e.setNomEvent(rs.getString("nom_event"));
                
                evenements.add(e);
         
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return evenements;
        
    }

   
    public void modifier(int id,Evenement t) {
     try {
        String qry = "UPDATE `Evenement` SET `nom_event`=?,`lieu`=?,`affiche_e`=?,`description_event`=?,`prix_ticket`=?,`nbr_places`=?,`date_debut_e`=?,`date_fin_e`=? WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(qry);
           
            ps.setString(1, t.getNomEvent());
            ps.setString(2, t.getLieu());
            ps.setString(3, t.getAfficheE());
            ps.setString(4, t.getDescriptionEvent());
            ps.setFloat(5, t.getPrixTicket());
            ps.setInt(6, t.getNbrPlaces());
            ps.setDate(7, (Date) t.getDateDebutE());
            ps.setDate(8, (Date) t.getDateFinE());
            ps.setInt(9, id);
          
          ps.executeUpdate();
            System.out.println("Evenement modifié ");
        } catch (SQLException ex) {
            System.out.println("Erreur: evenement n'est pas modifié");

            System.out.println(ex.getMessage());
        }   
    }

    @Override
    public void supprimer(Evenement t) 
    {
        try {
            String qry = "DELETE FROM `Evenement` WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("Evenement supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur: evenement n'est pas supprimé");
            System.out.println(ex.getMessage());
        }
    }

    public String getNomEvenement(int id) {
         Iterable<Evenement> evenements = null;
   for (Evenement e : evenements) {
      if (e.getId() == id) {
         return e.getNomEvent();
      }
   }
   return null; // or throw an exception if no event is found with the given id
}


    
}
