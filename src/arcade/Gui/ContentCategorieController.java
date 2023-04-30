/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Categorie;
import arcade.Service.CategorieService;
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
public class ContentCategorieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label idCategorie;
    @FXML
    private Label nomCategorie;
    @FXML
    private Label descriptionCategorie;
    @FXML
    private ImageView imageCategorie;
    @FXML
    private Label creationDate;
    @FXML
    private Label modificationDate;
    @FXML
    private Label enable;
    @FXML
    private ImageView isEnabled;
    @FXML
    private Pane contentCategorie;
    @FXML
    private ImageView EditCategorie;
    @FXML
    private ImageView deleteCategorie;
    @FXML
    private Button addCategorie;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idCategorie.setVisible(false);
        enable.setVisible(false);
    }    
    
        public void setIdCategorie(String IdCategorie) {
        this.idCategorie.setText(IdCategorie);
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie.setText(nomCategorie);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.setText(creationDate);
    }
    
    public void setEnable(String enable) {
        this.enable.setText(enable);
    }

    public void setIsEnabled(String url) {
        this.isEnabled.setImage(new Image(url));
    }

    public void setImage(String url) {
        this.imageCategorie.setImage(new Image(url));
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate.setText(modificationDate);
    }

    public void setDescription(String descriptionCategorie) {
        this.descriptionCategorie.setText(descriptionCategorie);
    }
    
    @FXML
    private void deleteCategorie(MouseEvent event) {
        int id = Integer.parseInt(idCategorie.getText());
        CategorieService serv = new CategorieService();
        try {            
            serv.delete(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller= loader.getController();
            controller.changePage("Categories");
            idCategorie.getScene().setRoot(root);

        } catch (Exception ex) {
                System.out.println(ex);
        }
       
    }

    @FXML
    private void EditCategorie(MouseEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCategorie.fxml"));
            Parent root = loader.load();
            EditCategorieController controller= loader.getController();
            Categorie c =new Categorie(Integer.parseInt(this.idCategorie.getText()),this.nomCategorie.getText(),this.descriptionCategorie.getText(),Boolean.parseBoolean(this.enable.getText()));
            System.out.println(c);
                System.out.println("amira");
            controller.setCategorie(c);
            
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeController homeCtrl = homeLoader.getController();
            homeCtrl.changePage("editCategorie",root);
            idCategorie.getScene().setRoot(homeRoot);

        } catch (Exception ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
