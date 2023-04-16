/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Produit;
import Service.ProduitService;
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
public class ContentProduitController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label idProduit;
    @FXML
    private Label idCategorie;
    @FXML
    private Label nomProduit;
    @FXML
    private Label description;
    @FXML
    private ImageView image;
    @FXML
    private Label prix;
    @FXML
    private Label quantite;
    @FXML
    private Label creationDate;
    @FXML
    private Label modificationDate;
    @FXML
    private Label isEnabled;
    @FXML
    private Pane contentProduit;
    @FXML
    private ImageView EditProduit;
    @FXML
    private ImageView delete;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setIdProduit(String idProduit) {
        this.idProduit.setText(idProduit);
    }
    
    public void setIdCategorie(String idCategorie) {
        this.idCategorie.setText(idCategorie);
    }
    
    public void setPrix(String prix) {
        this.prix.setText(prix);
    }
    
    public void setQuantiteStock(String quantite) {
        this.quantite.setText(quantite);
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit.setText(nomProduit);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.setText(creationDate);
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled.setText(isEnabled);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url));
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate.setText(modificationDate);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }
    
    
    
    @FXML
    private void delete(MouseEvent event) {
        int id = Integer.parseInt(idProduit.getText());
        ProduitService serv = new ProduitService();
        try {            
            serv.delete(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller= loader.getController();
            controller.changePage("Produits");
            idProduit.getScene().setRoot(root);

        } catch (Exception ex) {
                System.out.println(ex);
        }
       
    }

    @FXML
    private void EditProduit(MouseEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduit.fxml"));
            Parent root = loader.load();
            EditProduitController controller= loader.getController();
            Produit c =new Produit(Integer.parseInt(idProduit.getText()),Integer.parseInt(idCategorie.getText()),nomProduit.getText(),Integer.parseInt(prix.getText()),Integer.parseInt(quantite.getText()),description.getText(),Boolean.parseBoolean(isEnabled.getText()));
            //System.out.println(c);
            controller.setProduct(c);
            
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeController homeCtrl = homeLoader.getController();
            homeCtrl.changePage(root);
            idProduit.getScene().setRoot(homeRoot);
        } catch (Exception ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
