/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Produit;
import arcade.Service.ProduitService;
import java.util.List;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class ProduitController implements Initializable {
    @FXML
    private FlowPane contentProd;
    Produit prod = new Produit();
    
    @FXML
    private Pane contentProduit;
    
    @FXML
    private ImageView EditCategorie;
    @FXML
    private ImageView deleteCategorie;
    @FXML
    private Button addBtn;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    ProduitService ps = new ProduitService();
        try {

            List<Produit> produits = ps.afficher();

            for (Produit c : produits) {

                FXMLLoader item = new FXMLLoader(getClass().getResource("contentProduit.fxml"));
                try {
                    Parent root = item.load();
                    ContentProduitController cont = item.getController();

                    cont.setIdProduit(c.getId()+"");
                    cont.setIdCategorie(c.getCategorie()+"");
                    
                    cont.setNomProduit(c.getNomProduit());
                    
                    cont.setPrix(c.getPrix()+"");
                    cont.setQuantiteStock(c.getQuantiteStock()+"");
                    
                    cont.setImage("http://127.0.0.1:8000/eshop/produit/"+c.getImage());
                    cont.setDescription(c.getDescription());
                    
                    cont.setCreationDate(c.getCreationDate()+"");
                    cont.setModificationDate(c.getModificationDate()+"");
                    
                    cont.setIsEnabled(c.isIsEnabled()+"");

                    contentProd.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(ContentProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(ContentProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    


}