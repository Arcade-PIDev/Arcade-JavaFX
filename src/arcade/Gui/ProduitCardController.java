/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import static arcade.Arcade.loggedInUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.util.Duration;
import static arcade.Arcade.panier;
import arcade.Service.WishlistService;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class ProduitCardController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label quantite;
    @FXML
    private Label prodId;
    @FXML
    private ImageView cartbtn;
    @FXML
    private ImageView wishlistBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
        public void setNomProduit(String nom) {
        this.name.setText(nom);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url, 209, 114, false, false));
    }

    public void setPrix(String prix) {
        this.price.setText(prix);
    }
    
    public void setQuantite(String quantite) {
        this.quantite.setText(quantite);
    }

    public void setId(String id) {
        this.prodId.setText(id);
    }
    
    public void setWishlist(String url) {
        this.wishlistBtn.setImage(new Image(url, 32, 32, false, false));
    }
    
    @FXML
    private void addProductToCart(MouseEvent event) {
        int qt = 0;
        if (panier.containsKey(Integer.parseInt(prodId.getText()))) {
            qt = panier.get(Integer.parseInt(prodId.getText()));
            if ( (Integer.parseInt(this.quantite.getText()) - qt) == 0) {
                        Image img=new Image("arcade/images/warning.png");
                        Notifications notfBuilder = Notifications.create().title("Warning!!").text("La quantité de produit: '"+this.name.getText()+"' est nulle")
                        .darkStyle().graphic(new ImageView (img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);
                        notfBuilder.show();
                    }
            else {
            panier.put(Integer.parseInt(prodId.getText()), qt + 1);
            Notifications notfBuilder = Notifications.create().title("La quantité commandée a augmenté")
                    .darkStyle().hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
            notfBuilder.show();
            }

        }
        else {
            if ( (Integer.parseInt(this.quantite.getText()) - qt) == 0) {
                        Image img=new Image("arcade/images/warning.png");
                        Notifications notfBuilder = Notifications.create().title("Warning!!").text("La quantité de produit: '"+this.name.getText()+"' est nulle")
                        .darkStyle().graphic(new ImageView (img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);
                        notfBuilder.show();
                    }
            else {
                panier.put(Integer.parseInt(prodId.getText()), 1);
            qt++;
            Notifications notfBuilder = Notifications.create().title("Produit ajouté au panier")
                    .darkStyle().hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
            notfBuilder.show();
            }
        }

    }
    
    @FXML
    private void addProductToWishlist(MouseEvent event) {
        try {
            WishlistService ws = new WishlistService();
            if (ws.afficher(loggedInUser.getId()).contains(Integer.parseInt(prodId.getText()))) {
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
