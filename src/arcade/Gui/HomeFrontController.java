/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import static arcade.Arcade.loggedInUser;
import arcade.Entities.Categorie;
import java.io.IOException;
import javafx.scene.control.MenuItem;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class HomeFrontController implements Initializable {
    
    @FXML
    private MenuItem btnCategories;
    @FXML
    private MenuItem btnPanier;
    @FXML
    private MenuItem btnWishlist;
    @FXML
    private MenuItem btnProduits;
    @FXML
    private MenuItem btnCommandes;
    @FXML
    private MenuItem btnJeux;
    @FXML
    private MenuItem btnCoaching;
    @FXML
    private Label title;
    @FXML
    private Pane content;
    
    @FXML
    private Button btnEvenement;
   
    @FXML
    private Button logout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         content.getChildren().removeAll(content.getChildren());
           if(event.getSource() == btnCategories){
               title.setVisible(true);
                title.setText("Categories");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieFront.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(CategorieFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
          else if(event.getSource() == btnProduits){
               title.setVisible(true);
                title.setText("Produits");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitFront.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(ProduitByCategorieFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
          else if(event.getSource() == btnWishlist){
               title.setVisible(true);
                title.setText("Wishlist");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Wishlist.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          else if(event.getSource() == btnPanier){
               title.setVisible(true);
                title.setText("Panier");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       else if(event.getSource() == btnJeux){
               title.setVisible(true);
                title.setText("Jeux");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JeuxFront.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(JeuxFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
       else if (event.getSource() == btnEvenement) {
        //System.out.println("Clicked button: " + btnEvenement.toString());
        title.setVisible(false);
        

        if (content != null) {
            content.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFrontE.fxml"));
            Parent root = loader.load();
            content.getChildren().add(root);
        } else {
            System.out.println("content is null");
        }
          }
           
    }
    
    
    public void changePage(String state) {
        if (state.equals("ProduitByCategorie")) {
               title.setVisible(true);
         content.getChildren().removeAll(content.getChildren());
                title.setText("Produits");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitByCategorieFront.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (state.equals("Produit")) {
               title.setVisible(true);
         content.getChildren().removeAll(content.getChildren());
                title.setText("Produits");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitFront.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (state.equals("validerCommande")) {
               title.setVisible(true);
         content.getChildren().removeAll(content.getChildren());
                title.setText("validerCommande");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("validerCommande.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
        @FXML
private void logout(ActionEvent event) {
    try {
        // Close the current window
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();

        // Load the new FXML file and show it in the same window
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
}
