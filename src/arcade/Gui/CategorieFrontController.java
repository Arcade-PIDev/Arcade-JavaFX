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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
/**
 * FXML Controller class
 *
 * @author Amira
 */
public class CategorieFrontController implements Initializable{

    @FXML
    private FlowPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CategorieService serv = new CategorieService();
        try {
            List<Categorie> catList = serv.afficher();

            for (Categorie c : catList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieCard.fxml"));
                Parent root = loader.load();
                CategorieCardController cont = loader.getController();

                    cont.setIdCategorie(c.getId()+"");
                    cont.setNomCategorie(c.getNomCategorie());
                    cont.setDescription(c.getDescription());
                    cont.setImage("http://127.0.0.1:8000/eshop/categorie/"+c.getImage());
                    
                content.getChildren().add(root);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }    
}
