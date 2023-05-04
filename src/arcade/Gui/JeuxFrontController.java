/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Jeux;
import arcade.Service.JeuxService;
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
public class JeuxFrontController implements Initializable{

    @FXML
    private FlowPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        JeuxService serv = new JeuxService();
        try {
            List<Jeux> catList = serv.afficher();

            for (Jeux c : catList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JeuxCard.fxml"));
                Parent root = loader.load();
                JeuxCardController cont = loader.getController();

                    
                    cont.setNom(c.getNom());
                    cont.setGenre(c.getGenre());
                    cont.setImage("http://127.0.0.1/pi/public/Jeux/"+c.getImage());
                   // System.out.println("http://127.0.0.1:8000/Jeux/"+c.getImage());
                content.getChildren().add(root);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
