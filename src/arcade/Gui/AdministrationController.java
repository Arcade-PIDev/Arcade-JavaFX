/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chaim
 */
public class AdministrationController implements Initializable {

    @FXML
    private Circle ProfileCircle;
    @FXML
    private BorderPane BorderPane;
    @FXML
    private Button btnuser;
    @FXML
    private Button btnadduser;
    @FXML
    private Button logout;
    @FXML
    private Pane ghada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MainPage();
    }

    private void MainPage() {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("user.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }




    @FXML
    private void btnuser(ActionEvent event) {
                    try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("user.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnadduser(ActionEvent event) {
                            try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("useradd.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

@FXML
private void logout(ActionEvent event) {
    try {
        // Close the current window
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();

        // Load the new FXML file and show it in the same window
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}



}
