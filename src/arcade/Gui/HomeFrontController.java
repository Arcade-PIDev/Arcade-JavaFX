/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Jeux;
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

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class HomeFrontController implements Initializable {
    
    @FXML
    private MenuItem btnJeux;
   
    @FXML
    private Label title;
    @FXML
    private Pane content;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void handleClicks(ActionEvent event) {
         content.getChildren().removeAll(content.getChildren());
           if(event.getSource() == btnJeux){
               title.setVisible(true);
                title.setText("Jeux");
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JeuxFront.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(JeuxFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        
    }
    
    
    
    
}
}
