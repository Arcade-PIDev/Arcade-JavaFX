/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author zeine
 */
public class Sponsor {
    private int id , IDEventsFK ,NumTelSponsor ;
    private String NomSponsor, EmailSponsor, AdresseSponsor, DomaineSponsor, logoSponsor ;
    private float MontantSponsoring ;

    public Sponsor() {
    }

    public Sponsor(int IDEventsFK, int NumTelSponsor, String NomSponsor, String EmailSponsor, String AdresseSponsor, String DomaineSponsor, String logoSponsor, float MontantSponsoring) {
        this.IDEventsFK = IDEventsFK;
        this.NumTelSponsor = NumTelSponsor;
        this.NomSponsor = NomSponsor;
        this.EmailSponsor = EmailSponsor;
        this.AdresseSponsor = AdresseSponsor;
        this.DomaineSponsor = DomaineSponsor;
        this.logoSponsor = logoSponsor;
        this.MontantSponsoring = MontantSponsoring;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIDEventsFK() {
        return IDEventsFK;
    }

    public void setIDEventsFK(int IDEventsFK) {
        this.IDEventsFK = IDEventsFK;
    }

    public int getNumTelSponsor() {
        return NumTelSponsor;
    }

    public void setNumTelSponsor(int NumTelSponsor) {
        this.NumTelSponsor = NumTelSponsor;
    }

    public String getNomSponsor() {
        return NomSponsor;
    }

    public void setNomSponsor(String NomSponsor) {
        this.NomSponsor = NomSponsor;
    }

    public String getEmailSponsor() {
        return EmailSponsor;
    }

    public void setEmailSponsor(String EmailSponsor) {
        this.EmailSponsor = EmailSponsor;
    }

    public String getAdresseSponsor() {
        return AdresseSponsor;
    }

    public void setAdresseSponsor(String AdresseSponsor) {
        this.AdresseSponsor = AdresseSponsor;
    }

    public String getDomaineSponsor() {
        return DomaineSponsor;
    }

    public void setDomaineSponsor(String DomaineSponsor) {
        this.DomaineSponsor = DomaineSponsor;
    }

    public String getLogoSponsor() {
        return logoSponsor;
    }

    public void setLogoSponsor(String logoSponsor) {
        this.logoSponsor = logoSponsor;
    }

    public float getMontantSponsoring() {
        return MontantSponsoring;
    }

    public void setMontantSponsoring(float MontantSponsoring) {
        this.MontantSponsoring = MontantSponsoring;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "id=" + id + ", IDEventsFK=" + IDEventsFK + ", NumTelSponsor=" + NumTelSponsor + ", NomSponsor=" + NomSponsor + ", EmailSponsor=" + EmailSponsor + ", AdresseSponsor=" + AdresseSponsor + ", DomaineSponsor=" + DomaineSponsor + ", logoSponsor=" + logoSponsor + ", MontantSponsoring=" + MontantSponsoring + '}';
    }

   
       
}
