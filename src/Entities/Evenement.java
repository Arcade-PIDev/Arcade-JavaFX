/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author zeine
 */
public class Evenement {
    
     private int id, nbrPlaces ;
     private String NomEvent , lieu, AfficheE, DescriptionEvent  ;
     private float PrixTicket ;
     private Date DateDebutE, DateFinE ;

    public Evenement() {
    }
     

    public Evenement(int nbrPlaces, String NomEvent, String lieu, String AfficheE, String DescriptionEvent, float PrixTicket, Date DateDebutE, Date DateFinE) {
        this.nbrPlaces = nbrPlaces;
        this.NomEvent = NomEvent;
        this.lieu = lieu;
        this.AfficheE = AfficheE;
        this.DescriptionEvent = DescriptionEvent;
        this.PrixTicket = PrixTicket;
        this.DateDebutE = DateDebutE;
        this.DateFinE = DateFinE;
    }

    public Evenement(String nomEvent, String startDate, String endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public String getNomEvent() {
        return NomEvent;
    }

    public void setNomEvent(String NomEvent) {
        this.NomEvent = NomEvent;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getAfficheE() {
        return AfficheE;
    }

    public void setAfficheE(String AfficheE) {
        this.AfficheE = AfficheE;
    }

    public String getDescriptionEvent() {
        return DescriptionEvent;
    }

    public void setDescriptionEvent(String DescriptionEvent) {
        this.DescriptionEvent = DescriptionEvent;
    }

    public float getPrixTicket() {
        return PrixTicket;
    }

    public void setPrixTicket(float PrixTicket) {
        this.PrixTicket = PrixTicket;
    }

    public Date getDateDebutE() {
        return DateDebutE;
    }

    public void setDateDebutE(Date DateDebutE) {
        this.DateDebutE = DateDebutE;
    }

    public Date getDateFinE() {
        return DateFinE;
    }

    public void setDateFinE(Date DateFinE) {
        this.DateFinE = DateFinE;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nbrPlaces=" + nbrPlaces + ", NomEvent=" + NomEvent + ", lieu=" + lieu + ", AfficheE=" + AfficheE + ", DescriptionEvent=" + DescriptionEvent + ", PrixTicket=" + PrixTicket + ", DateDebutE=" + DateDebutE + ", DateFinE=" + DateFinE + '}';
    }
    
    

   

  




  
  

   



    
}
