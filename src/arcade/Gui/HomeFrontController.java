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
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                
        new Thread(){
            public void run(){
                
               int count = 0;
               try{
                while(true){

                    switch(count){

                        case 0:
                            Thread.sleep(5000);
                            
                            TranslateTransition slider1 = new TranslateTransition();
                            
                            slider1.setNode(image1);
                            slider1.setDuration(Duration.seconds(3));
                            slider1.setToX(0);
                            slider1.play();
                            
                            TranslateTransition slider2 = new TranslateTransition();
                            
                            slider2.setNode(image2);
                            slider2.setDuration(Duration.seconds(3));
                            slider2.setToX(0);
                            slider2.play();
                            
                            TranslateTransition slider3 = new TranslateTransition();
                            
                            slider3.setNode(image3);
                            slider3.setDuration(Duration.seconds(3));
                            slider3.setToX(-600);
                            slider3.play();
                            
                            TranslateTransition slider4 = new TranslateTransition();
                            
                            slider4.setNode(image4);
                            slider4.setDuration(Duration.seconds(3));
                            slider4.setToX(-1200);
                            slider4.play();
                            
                            count=1;
                            break;
                        case 1:
                            Thread.sleep(5000);
                             TranslateTransition slider5 = new TranslateTransition();
                            
                            slider5.setNode(image1);
                            slider5.setDuration(Duration.seconds(3));
                            slider5.setToX(600);
                            slider5.play();
                            
                            TranslateTransition slider6 = new TranslateTransition();
                            
                            slider6.setNode(image2);
                            slider6.setDuration(Duration.seconds(3));
                            slider6.setToX(600);
                            slider6.play();
                            
                            TranslateTransition slider7 = new TranslateTransition();
                            
                            slider7.setNode(image3);
                            slider7.setDuration(Duration.seconds(3));
                            slider7.setToX(0);
                            slider7.play();
                            
                            TranslateTransition slider8 = new TranslateTransition();
                            
                            slider8.setNode(image4);
                            slider8.setDuration(Duration.seconds(3));
                            slider8.setToX(-600);
                            slider8.play();
                            
                            count=2;
                            break;
                        case 2:
                            Thread.sleep(5000);
                             TranslateTransition slider9 = new TranslateTransition();
                            
                            slider9.setNode(image1);
                            slider9.setDuration(Duration.seconds(3));
                            slider9.setToX(1200);
                            slider9.play();
                            
                            TranslateTransition slider10 = new TranslateTransition();
                            
                            slider10.setNode(image2);
                            slider10.setDuration(Duration.seconds(3));
                            slider10.setToX(1200);
                            slider10.play();
                            
                            TranslateTransition slider11 = new TranslateTransition();
                            
                            slider11.setNode(image3);
                            slider11.setDuration(Duration.seconds(3));
                            slider11.setToX(600);
                            slider11.play();
                            
                            TranslateTransition slider12 = new TranslateTransition();
                            
                            slider12.setNode(image4);
                            slider12.setDuration(Duration.seconds(3));
                            slider12.setToX(0);
                            slider12.play();
                            
                            count=3;
                            break;
                        case 3 :
                            Thread.sleep(5000);
                            TranslateTransition slider13 = new TranslateTransition();
                            
                            slider13.setNode(image1);
                            slider13.setDuration(Duration.seconds(3));
                            slider13.setToX(1200);
                            slider13.play();
                            
                            TranslateTransition slider14 = new TranslateTransition();
                            
                            slider14.setNode(image2);
                            slider14.setDuration(Duration.seconds(3));
                            slider14.setToX(1200);
                            slider14.play();
                            
                            TranslateTransition slider15 = new TranslateTransition();
                            
                            slider15.setNode(image3);
                            slider15.setDuration(Duration.seconds(3));
                            slider15.setToX(1200);
                            slider15.play();
                            
                            TranslateTransition slider16 = new TranslateTransition();
                            
                            slider16.setNode(image4);
                            slider16.setDuration(Duration.seconds(3));
                            slider16.setToX(600);
                            slider16.play();
                            
                            count=0;
                            break;
                            
                            


                    }
                   
               }
               }catch(Exception e){e.printStackTrace();}
               
            }
        }.start();
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
