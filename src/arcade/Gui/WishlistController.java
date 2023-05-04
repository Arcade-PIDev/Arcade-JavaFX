/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import static arcade.Arcade.loggedInUser;
import arcade.Entities.Produit;
import arcade.Service.ProduitService;
import arcade.Service.WishlistService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
/**
 *
 * @author Amira
 */
public class WishlistController implements Initializable {

    @FXML
    private FlowPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ProduitService ps = new ProduitService();
        try {

            List<Produit> products = ps.afficher();

            for (Produit p : products) {

                FXMLLoader item = new FXMLLoader(getClass().getResource("WishlistCard.fxml"));
                try {
                    Parent itek = item.load();

                    //wishlist
                    WishlistService ws = new WishlistService();
                    if (ws.afficher(loggedInUser.getId()).contains(p.getId())) {
                        WishlistCardController itemController = item.getController();

                        itemController.setNomProduit(p.getNomProduit());
                        itemController.setPrix(p.getPrix() + "");
                        itemController.setId(p.getId() + "");
                        itemController.setImage("http://127.0.0.1/pi/public/eshop/produit/"+p.getImage());
                        content.getChildren().add(itek);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(ProduitByCategorieFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
