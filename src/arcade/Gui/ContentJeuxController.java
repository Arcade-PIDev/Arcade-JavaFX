/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Jeux;
import arcade.Service.JeuxService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class ContentJeuxController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        @FXML
    private Label id;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private ImageView image;
    @FXML
    private Label genre;
    @FXML
    private Label color;
    
    @FXML
    private Pane content;
    @FXML
    private ImageView EditJeux;
    @FXML
    private ImageView deleteJeux;
    @FXML
    private Button addJeux;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        public void setId(String Id) {
        this.id.setText(Id);
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public void setgenre(String genre) {
        this.genre.setText(genre);
    }



    public void setImage(String url) {
        this.image.setImage(new Image(url));
    }

    public void setcolor(String color) {
        this.color.setText(color);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }
    
    @FXML
    private void deleteJeux(MouseEvent event) {
      int id1 = Integer.parseInt(id.getText());
        JeuxService serv = new JeuxService();
        try {            
           serv.delete(id1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller= loader.getController();
            controller.changePage("Jeux");
            id.getScene().setRoot(root);

        } catch (Exception ex) {
                System.out.println(ex);
        }
       
    }

    @FXML
    private void EditJeux(MouseEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditJeux.fxml"));
            Parent root = loader.load();
            EditJeuxController controller= loader.getController();
            Jeux c =new Jeux(Integer.parseInt(this.id.getText()),this.nom.getText(),this.description.getText(),this.genre.getText(),this.color.getText());
            System.out.println(c);
            controller.setJeux(c);
            
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeController homeCtrl = homeLoader.getController();
            homeCtrl.changePage(root);
            id.getScene().setRoot(homeRoot);

        } catch (Exception ex) {
            Logger.getLogger(JeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
