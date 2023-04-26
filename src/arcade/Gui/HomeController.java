/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnGames;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView userImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Pane mainContent;
    @FXML
    private Pane affichage;
    @FXML
    private Button addBtn;
    @FXML
    private Button btnStat;
    @FXML
    private Button btnJeux;
    @FXML
    private Button pdf;
    @FXML
    private Button tri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
        @FXML
    private void addBtn(ActionEvent event) throws Exception{
            if(titleLabel.getText().equals("Jeux")){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
            mainContent.getChildren().removeAll(mainContent.getChildren());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addJeux.fxml"));
                Parent root = loader.load();
                addJeuxController cont = loader.getController();
                anchorPane.getChildren().add(root);
                //mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
          
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnJeux) {
            titleLabel.setText("Jeux");
            addBtn.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Jeux.fxml"));
                Parent root = loader.load();
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        
        else {
            System.out.println(event.getSource());
        }
    }

    public void changePage(String state) {
        if (state.equals("Jeux")) {
            titleLabel.setText("Jeux");

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Jeux.fxml"));
                Parent root = loader.load();
                affichage.getChildren().clear(); // clear previous view
                affichage.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (state.equals("editJeux")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("editJeux.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().clear(); // clear previous view
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
        
    }
    
    public void changePage(Parent node) {
        mainContent.getChildren().removeAll(mainContent.getChildren());
        mainContent.getChildren().add(node);
    }
   
private void tri(ActionEvent event)
{
    
}

}
