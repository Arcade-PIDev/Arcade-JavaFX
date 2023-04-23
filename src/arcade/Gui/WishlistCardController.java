/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Amira
 */
public class WishlistCardController implements Initializable{
    
    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label prodId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
      public void setNomProduit(String nom) {
        this.nom.setText(nom);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url, 209, 114, false, false));
    }

    public void setPrix(String prix) {
        this.prix.setText(prix);
    }

    public void setId(String id) {
        this.prodId.setText(id);
    }
    
}
