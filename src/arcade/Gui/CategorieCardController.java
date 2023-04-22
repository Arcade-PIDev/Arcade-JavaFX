/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Produit;
import arcade.Service.ProduitService;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class CategorieCardController implements Initializable {
    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Pane categorieCard;
    @FXML
    private Label catId;
    @FXML
    private Button goToProductBtn;

    List<Produit> products;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setNomCategorie(String nomCategorie) {
        this.name.setText(nomCategorie);
    }

    public void setImage(String url) {
        this.image.setImage(new Image(url, 209, 114, false, false));
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setIdCategorie(String id) {
        this.catId.setText(id);
    }
    
    @FXML
    public void goToProductBtn(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homeFront.fxml"));
            Parent root = loader.load();
            HomeFrontController cont = loader.getController();
            //cont.changePage("Produit",this.catId.getText());
            cont.changePage("Produit");
            cont.getCat(this.catId.getText());

            
            goToProductBtn.getScene().setRoot(root);

            } catch (Exception ex) {
            System.out.println(ex.getMessage());
            }
    }
}
