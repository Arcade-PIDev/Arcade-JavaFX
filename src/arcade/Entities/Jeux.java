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
public class Jeux {
    private int id;
    private String nom,description,image,genre,color;
    

    public Jeux(){};
    
    public Jeux(String nom, String description, String image,String genre,String color) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.genre = genre;
        this.color = color;
    }
    
    
    public Jeux(int id, String nom, String description, String image, String genre,String color) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.genre = genre;
        this.color = color;
    }

    

    public Jeux(String nom, String description, String genre, String color) {
        this.nom = nom;
        this.description = description;
        this.genre = genre;
        this.color = color;
    }
    
     public Jeux(int id,String nom, String description, String genre, String color) {
                 
         this.id = id;

         this.nom = nom;
        this.description = description;
        this.genre = genre;
        this.color = color;
    }
    
    
    
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "jeux{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", image=" + image + ", genre=" + genre + ", color=" + color + '}';
    }

    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
