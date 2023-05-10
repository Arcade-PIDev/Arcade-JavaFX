/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import static arcade.Arcade.loggedInUser;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Amira
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Pane mainContent;
    @FXML
    private Pane affichage;
    @FXML
    private Button addBtn;
    @FXML
    private Button btnCategories;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnJeux;
    @FXML
    private Button btnEvenements;
    @FXML
    private Button btnSponsor;
    @FXML
    private Button btnSponsors;
    @FXML
    private Line line;
    
    @FXML
    private Button btnCoaching;
    @FXML
    private Button btnCommandes;
    @FXML
    private ImageView userImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button logout;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 //       usernameLabel.setText(loggedInUser.getUsername());
//        userImage.setImage(new Image(loggedInUser.getAvatar()));
    }
    
        @FXML
    private void addBtn(ActionEvent event) throws Exception{
            if(titleLabel.getText().equals("Categories")){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addCategorie.fxml"));
                Parent root = loader.load();
                addCategorieController cont = loader.getController();
                anchorPane.getChildren().add(root);
                //mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
            else if(titleLabel.getText().equals("Users")){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("useradd.fxml"));
                Parent root = loader.load();
                userAddController cont = loader.getController();
                anchorPane.getChildren().add(root);
                //mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if(titleLabel.getText().equals("Coaching")){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("seancecoachingadd.fxml"));
                Parent root = loader.load();
                seancecoachingAddController cont = loader.getController();
                anchorPane.getChildren().add(root);
                //mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if(titleLabel.getText().equals("Evenement")){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAjouterEvent.fxml"));
                Parent root = loader.load();
                FXMLControllerAjouterE cont = loader.getController();
                anchorPane.getChildren().add(root);
                //mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if(titleLabel.getText().equals("Sponsor")){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAddSponsor.fxml"));
                Parent root = loader.load();
                FXMLControllerAjouterS cont = loader.getController();
                anchorPane.getChildren().add(root);
                //mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
          else if(titleLabel.getText().equals("Produits")){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addProduit.fxml"));
                Parent root = loader.load();
                addProduitController cont = loader.getController();
                cont.setCombo();
                anchorPane.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
          else if(titleLabel.getText().equals("Jeux")){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addJeux.fxml"));
                Parent root = loader.load();
                addJeuxController cont = loader.getController();
                anchorPane.getChildren().add(root);
                //mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnCategories) {
            affichage.getChildren().removeAll(affichage.getChildren());
            titleLabel.setText("Categories");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorie.fxml"));
                Parent root = loader.load();
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
         else if (event.getSource() == btnProducts) {
            affichage.getChildren().removeAll(affichage.getChildren());
            titleLabel.setText("Produits");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Produit.fxml"));
                Parent root = loader.load();
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
         else if (event.getSource() == btnEvenements) {
            affichage.getChildren().removeAll(affichage.getChildren());
            titleLabel.setText("Evenement");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEvenement.fxml"));
                Parent root = loader.load();
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
         else if (event.getSource() == btnUser) {
            affichage.getChildren().removeAll(affichage.getChildren());
            titleLabel.setText("Users");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("user.fxml"));
                Parent root = loader.load();
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
         else if (event.getSource() == btnCoaching) {
            affichage.getChildren().removeAll(affichage.getChildren());
            titleLabel.setText("Coaching");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageSeance.fxml"));
                Parent root = loader.load();
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
         else if (event.getSource() == btnJeux) {
            affichage.getChildren().removeAll(affichage.getChildren());
            titleLabel.setText("Jeux");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Jeux.fxml"));
                Parent root = loader.load();
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
         
          else if (event.getSource() == btnEvenements) {
            affichage.getChildren().removeAll(affichage.getChildren());
            titleLabel.setText("Evenement");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEvenement.fxml"));
                Parent root = loader.load();
                affichage.getChildren().add(root);
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
          
          else if (event.getSource() == btnSponsor) {
            affichage.getChildren().removeAll(affichage.getChildren());
            titleLabel.setText("Sponsor");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSponsor.fxml"));
                Parent root = loader.load();
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
          }
        else {
            System.out.println(event.getSource());
        }
       
        
       
    }

    public void changePage(String state) {
        if (state.equals("Categories")) {
            titleLabel.setText("Categories");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorie.fxml"));
                Parent root = loader.load();
                affichage.getChildren().clear(); // clear previous view
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (state.equals("seancecoachinggg")) {
            titleLabel.setText("seance coaching");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("seancecoaching.fxml"));
                Parent root = loader.load();
                affichage.getChildren().clear(); // clear previous view
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (state.equals("Sponsor")) {
            titleLabel.setText("Sponsor");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSponsor.fxml"));
                Parent root = loader.load();
                affichage.getChildren().clear(); // clear previous view
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (state.equals("Produits")) {
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            titleLabel.setText("Produits");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Produit.fxml"));
                Parent root = loader.load();
                affichage.getChildren().clear(); // clear previous view
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (state.equals("Jeux")) {
            titleLabel.setText("Jeux");
            addBtn.setVisible(true);
            titleLabel.setVisible(true);
            line.setVisible(true);
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Jeux.fxml"));
                Parent root = loader.load();
                affichage.getChildren().clear(); // clear previous view
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (state.equals("editJeux")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("editJeux.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().clear(); // clear previous view
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void changePage(Parent node) {
        mainContent.getChildren().removeAll(mainContent.getChildren());
        mainContent.getChildren().add(node);
    }

    public void changePage(String title, Parent node) {
        if (title.equals("editCategorie")) {
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            anchorPane.getChildren().add(node);
        }
        else if (title.equals("editProduit")) {
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            anchorPane.getChildren().add(node);
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
