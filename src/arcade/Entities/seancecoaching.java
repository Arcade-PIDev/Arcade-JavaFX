/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Entities;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class seancecoaching {
    private Integer id;
    private Date date_debut_seance , date_fin_seance ;
    private Double prix_seance ;
    private String description_seance ,image_seance , titre_seance ; 

    public seancecoaching() {
    }

    public seancecoaching(Integer id, Date date_debut_seance, Date date_fin_seance, Double prix_seance, String description_seance, String image_seance, String titre_seance) {
        this.id = id;
        this.date_debut_seance = date_debut_seance;
        this.date_fin_seance = date_fin_seance;
        this.prix_seance = prix_seance;
        this.description_seance = description_seance;
        this.image_seance = image_seance;
        this.titre_seance = titre_seance;
    }

    public seancecoaching(Date date_debut_seance, Date date_fin_seance, Double prix_seance, String description_seance, String image_seance, String titre_seance) {
        this.date_debut_seance = date_debut_seance;
        this.date_fin_seance = date_fin_seance;
        this.prix_seance = prix_seance;
        this.description_seance = description_seance;
        this.image_seance = image_seance;
        this.titre_seance = titre_seance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate_debut_seance() {
        return date_debut_seance;
    }

    public void setDate_debut_seance(Date date_debut_seance) {
        this.date_debut_seance = date_debut_seance;
    }

    public Date getDate_fin_seance() {
        return date_fin_seance;
    }

    public void setDate_fin_seance(Date date_fin_seance) {
        this.date_fin_seance = date_fin_seance;
    }

    public Double getPrix_seance() {
        return prix_seance;
    }

    public void setPrix_seance(Double prix_seance) {
        this.prix_seance = prix_seance;
    }

    public String getDescription_seance() {
        return description_seance;
    }

    public void setDescription_seance(String description_seance) {
        this.description_seance = description_seance;
    }

    public String getImage_seance() {
        return image_seance;
    }

    public void setImage_seance(String image_seance) {
        this.image_seance = image_seance;
    }

    public String getTitre_seance() {
        return titre_seance;
    }

    public void setTitre_seance(String titre_seance) {
        this.titre_seance = titre_seance;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", date_debut_seance=" + date_debut_seance + ", date_fin_seance=" + date_fin_seance + ", prix_seance=" + prix_seance + ", description_seance=" + description_seance + ", image_seance=" + image_seance + ", titre_seance=" + titre_seance + '}';
    }
    
    

 
}
