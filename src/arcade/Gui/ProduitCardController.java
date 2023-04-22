/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

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

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class ProduitCardController implements Initializable {

    @FXML
    private Pane categoryCard;
    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label prodId;
    @FXML
    private ImageView cartbtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
        public void setNomProduit(String nomCategorie) {
        this.name.setText(nomCategorie);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url, 209, 114, false, false));
    }

    public void setPrix(String prix) {
        this.price.setText(prix);
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
}
