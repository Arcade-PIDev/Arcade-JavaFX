/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Produit;
import arcade.Entities.Commande;
import arcade.Service.PanierService;
import arcade.Service.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;
import static arcade.Arcade.panier;
import java.io.IOException;

/**
 *
 * @author Amira
 */
public class PanierController implements Initializable{
    @FXML
    private FlowPane content;
    @FXML
    private Button ProceedToCheckout;
    @FXML
    private Label userName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ProduitService ps = new ProduitService();

        for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {
            //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            FXMLLoader item = new FXMLLoader(getClass().getResource("PanierItem.fxml"));
            try {
                Parent itek = item.load();
                PanierItemController PanierItemController = item.getController();
                try {
                    Produit p = ps.getProduitById(entry.getKey());
                    PanierItemController.setProdId(p.getNomProduit());
                } catch (Exception ex) {
                    Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                }

                PanierItemController.setQuantite(entry.getValue());
                PanierItemController.setProdIdHidden(entry.getKey());

                content.getChildren().add(itek);
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void ProceedToCheckout(ActionEvent event) {
        ProduitService ps = new ProduitService();
        int prixTotal = 0;

        for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {
            try {
                //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
                Produit p = ps.getProduitById(entry.getKey());
                    prixTotal += p.getPrix() * entry.getValue();
                    p.setQuantiteStock(p.getQuantiteStock() - entry.getValue());
                    //System.out.println(p);
                    ps.update2(p);
                    PanierService cs = new PanierService();
                    Commande c = new Commande();
                    c.setPrixTotal(prixTotal);
                    cs.ajouterCommande(c);
                    
                    FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
                    Parent homeRoot = homeLoader.load();
                    HomeFrontController homeCtrl = homeLoader.getController();
                    homeCtrl.changePage("validerCommande");
                    ProceedToCheckout.getScene().setRoot(homeRoot);
            } catch (Exception ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
