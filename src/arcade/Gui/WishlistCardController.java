/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import static arcade.Arcade.panier;
import arcade.Service.WishlistService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private ImageView cartbtn;
    @FXML
    private ImageView wishlistBtn;
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
    
    @FXML
    private void addProductToCart(MouseEvent event) {
        int qt = 0;
        if (panier.containsKey(Integer.parseInt(prodId.getText()))) {
            qt = panier.get(Integer.parseInt(prodId.getText()));
            panier.put(Integer.parseInt(prodId.getText()), qt + 1);
        } else {
            panier.put(Integer.parseInt(prodId.getText()), 1);
            //System.out.print(panier.keySet());
        }

    }
    
    @FXML
    private void removeProductFromWishlist(MouseEvent event) {
        try {
            WishlistService ws = new WishlistService();
            if (ws.afficher().contains(Integer.parseInt(prodId.getText()))) {
                ws.remove(Integer.parseInt(prodId.getText()));
                wishlistBtn.setImage(new Image("http://127.0.0.1/pi/public/eshop/" + "empty.png", 32, 32, false, false));
            } else {
                ws.add(Integer.parseInt(prodId.getText()));
                wishlistBtn.setImage(new Image("http://127.0.0.1/pi/public/eshop/" + "full.png", 32, 32, false, false));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitCardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
