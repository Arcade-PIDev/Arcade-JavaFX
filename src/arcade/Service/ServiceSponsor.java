/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Service;

import arcade.Entities.Evenement;
import arcade.Entities.Sponsor;
import arcade.Utils.database;
import arcade.Service.IService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zeine
 */
public class ServiceSponsor implements IService<Sponsor> {
    
     Connection cnx;
    
    public  ServiceSponsor(){
        cnx =database.getInstance().getCon();
    }
    
    @Override
    public void ajouter(Sponsor t) {
        try {
            String qry = "INSERT INTO `Sponsor`( `nom_sponsor`, `num_tel_sponsor`, `email_sponsor`, `adresse_sponsor`, `domaine_sponsor`, `logo_sponsor`,`montant_sponsoring`, `idevents_fk_id`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(qry);
       
            ps.setString(1, t.getNomSponsor()); 
            ps.setInt(2, t.getNumTelSponsor());
            ps.setString(3, t.getEmailSponsor());
            ps.setString(4, t.getAdresseSponsor());
            ps.setString(5, t.getDomaineSponsor());
            ps.setString(6, t.getLogoSponsor());
            ps.setFloat(7, t.getMontantSponsoring());
            ps.setInt(8, t.getIDEventsFK());

            ps.executeUpdate();
            System.out.println("Sponsor ajouté ");
        } catch (SQLException ex) {
            System.out.println("Erreur: Sponsor n'est pas ajouté");

            System.out.println(ex.getMessage());
        }

    }
    
      @Override
    public List<Sponsor> afficher() {
       List<Sponsor> sponsors = new ArrayList<>();
         
        try {
             String req ="SELECT * FROM `Sponsor` ";
            
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Sponsor s = new Sponsor();
                s.setId(rs.getInt("id"));
                s.setNomSponsor(rs.getString("nom_sponsor"));
                s.setNumTelSponsor(rs.getInt("num_tel_sponsor"));
                s.setEmailSponsor(rs.getString("email_sponsor"));
                s.setAdresseSponsor(rs.getString("adresse_sponsor"));
                s.setDomaineSponsor(rs.getString("domaine_sponsor"));
                s.setLogoSponsor(rs.getString("logo_sponsor"));
                s.setMontantSponsoring(rs.getFloat("montant_sponsoring"));
                s.setIDEventsFK(rs.getInt("idevents_fk_id"));
                
                sponsors.add(s);
             
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return sponsors;
        
    }

   
    public void modifier(int id,Sponsor t) {
     try {
        String qry = "UPDATE `Sponsor` SET `nom_sponsor`=?,`num_tel_sponsor`=?,`email_sponsor`=?,`adresse_sponsor`=?,`domaine_sponsor`=?,`logo_sponsor`=?,`montant_sponsoring`=?,`idevents_fk_id`=? WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(qry);
           
            ps.setString(1, t.getNomSponsor()); 
            ps.setInt(2, t.getNumTelSponsor());
            ps.setString(3, t.getEmailSponsor());
            ps.setString(4, t.getAdresseSponsor());
            ps.setString(5, t.getDomaineSponsor());
            ps.setString(6, t.getLogoSponsor());
            ps.setFloat(7, t.getMontantSponsoring());
            ps.setInt(8, t.getIDEventsFK());
            ps.setInt(9, id);
          
          ps.executeUpdate();
            System.out.println("Sponsor modifié ");
        } catch (SQLException ex) {
            System.out.println("Erreur: Sponsor n'est pas modifié");

            System.out.println(ex.getMessage());
        }   
    }

    @Override
    public void supprimer(Sponsor t) 
    {
        try {
            String qry = "DELETE FROM `Sponsor` WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("Sponsor supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur: Sponsor n'est pas supprimé");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Sponsor c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}