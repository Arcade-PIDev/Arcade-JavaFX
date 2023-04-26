/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import Services.ServiceEvenement;
import java.io.IOException;
import javafx.scene.control.MenuItem;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.scene.control.Button;
/**
 *
 * @author zeine
 */
public class FrontMainController implements Initializable{
    
     @FXML
    private Button btnEvenement;
   
    private Label title;
    @FXML
    private Pane content;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
public void handleClicks(ActionEvent event) throws IOException {
    if (event.getSource() == btnEvenement) {
        System.out.println("Clicked button: " + btnEvenement.toString());

        if (content != null) {
            content.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFrontE.fxml"));
            Parent root = loader.load();
            content.getChildren().add(root);
        } else {
            System.out.println("content is null");
        }
    }
}


    
    
    
    
}
