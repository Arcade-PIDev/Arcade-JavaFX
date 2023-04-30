/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Entities;

import java.util.Date;

/**
 *
 * @author Amira
 */
public class Categorie {
    private int id;
    private String nomCategorie, image,description;
    private boolean isEnabled;
    private Date creationDate,ModificationDate;

    public Categorie(){};
    
    public Categorie(String nomCategorie, String description, String image) {
        this.nomCategorie = nomCategorie;
        this.image = image;
        this.description = description;
    }
    
    
    public Categorie(int id, String nomCategorie, String description, String image, Date creationDate, Date ModificationDate, boolean isEnabled) {
        this.id = id;
        this.nomCategorie = nomCategorie;
        this.description = description;
        this.image = image;
        this.isEnabled = isEnabled;
        this.creationDate = creationDate;
        this.ModificationDate = ModificationDate;
    }
    
    public Categorie(int id, String nomCategorie, String description, boolean isEnabled) {
        this.id = id;
        this.nomCategorie = nomCategorie;
        this.description = description;
        this.isEnabled = isEnabled;
    }
    
    public Categorie(int id, String nomCategorie, String description, String image, boolean isEnabled) {
        this.id = id;
        this.nomCategorie = nomCategorie;
        this.description = description;
        this.image = image;
        this.isEnabled = isEnabled;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return ModificationDate;
    }

    public void setModificationDate(Date ModificationDate) {
        this.ModificationDate = ModificationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nomCategorie=" + nomCategorie + ", image=" + image + ", description=" + description + ", isEnabled=" + isEnabled + ", creationDate=" + creationDate + ", ModificationDate=" + ModificationDate + "}";
    }


    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
