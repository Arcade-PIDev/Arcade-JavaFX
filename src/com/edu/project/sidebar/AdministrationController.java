/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.sidebar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author chaim
 */
public class AdministrationController implements Initializable {

    @FXML
    private BorderPane BorderPane;
    @FXML
    private Button btnjeux;
    @FXML
    private Button btnaddjeux;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MainPage();
    }

    private void MainPage() {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../jeux/jeux.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @FXML
    private void btnjeux(ActionEvent event) {
                try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../jeux/jeux.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnaddjeux(ActionEvent event) {
                try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../jeux/jeuxadd.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
