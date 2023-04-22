/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Produit;
import static arcade.Arcade.panier;
import arcade.Service.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
/**
 *
 * @author Amira
 */
public class PanierItemController implements Initializable {

    @FXML
    private Label prodId;
    @FXML
    private Label quantite;
    @FXML
    private Label ProdIdHidden;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setProdId(String nomProd) {
        this.prodId.setText(nomProd);
    }

    public void setProdIdHidden(int prodIdHidden) {
        this.ProdIdHidden.setText(prodIdHidden + "");
    }

    public void setQuantite(int quantite) {
        this.quantite.setText(quantite + "");
    }


    @FXML
    private void removeQuantity(MouseEvent event) {
        int qt;

        qt = panier.get(Integer.parseInt(ProdIdHidden.getText()));
        if (qt > 1) {
            panier.put(Integer.parseInt(ProdIdHidden.getText()), qt - 1);
            int s = qt - 1;
            quantite.setText(s + "");
        }
    }

    @FXML
    private void addQuantity(MouseEvent event) {

        int qt;
        qt = panier.get(Integer.parseInt(ProdIdHidden.getText()));
        panier.put(Integer.parseInt(ProdIdHidden.getText()), qt + 1);
        int s = qt + 1;
        quantite.setText(s + "");

    }
}
