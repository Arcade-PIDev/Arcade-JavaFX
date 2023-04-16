/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Amira
 */
public class Produit {
    private int id,categorie,quantiteStock,prix;
    private String nomProduit, image,description;
    private Date creationDate,ModificationDate;
    private boolean isEnabled;

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

    public Produit(){};

    public Produit(int id, int categorie, String nomProduit, int prix,int  quantiteStock, String description,Boolean isEnabled) {
        this.id = id;
        this.categorie = categorie;
        this.quantiteStock = quantiteStock;
        this.prix = prix;
        this.nomProduit = nomProduit;
        this.isEnabled = isEnabled;
        this.description = description;
    }
    
    public Produit(int id, int categorie, int quantiteStock, int prix, String nomProduit, String image, String description) {
        this.id = id;
        this.categorie = categorie;
        this.quantiteStock = quantiteStock;
        this.prix = prix;
        this.nomProduit = nomProduit;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
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

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categorie=" + categorie + ", quantiteStock=" + quantiteStock + ", prix=" + prix + ", nomProduit=" + nomProduit + ", image=" + image + ", description=" + description + "}";
    }
    
}
