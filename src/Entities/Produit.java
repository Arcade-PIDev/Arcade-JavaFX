/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Amira
 */
public class Produit {
    private int id,categorie,quantiteStock,prix;
    private String nomProduit, image,description;

    public Produit(){};

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
