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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class EditJeuxController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Label nomJeuxError;
    @FXML
    private TextField description;
    @FXML
    private Label descriptionError;
    @FXML
    private Button cancel;
    @FXML
    private Button editJeux;
    private Jeux jeux;

    public void setJeux(Jeux c) {
        this.jeux = c;
        nom.setText(jeux.getNom());
        description.setText(jeux.getDescription());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.changePage("Jeux");

            editJeux.getScene().setRoot(root);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void editJeux(ActionEvent event) {
        if (nom.getText().length() != 0 && description.getText().length() != 0) {
            if (Pattern.matches("^[a-zA-Z]*$", nom.getText()) == true && Pattern.matches("^[a-zA-Z]*$", description.getText()) == true) {

                jeux.setNom(nom.getText());
                jeux.setDescription(description.getText());

                JeuxService serv = new JeuxService();
                try {
                    serv.update(jeux);
                    
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                        Parent root = loader.load();
                        HomeController controller = loader.getController();
                        controller.changePage("Jeux");

                        editJeux.getScene().setRoot(root);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (Pattern.matches("^[a-zA-Z]*$", nom.getText()) == false) {
                    nomJeuxError.setText("Seulement des lettres");
                }
                if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                    descriptionError.setText("Seulement des lettres");
                }
            }
        } else {
            if (nom.getText().length() == 0) {
                nomJeuxError.setText("Champs Obligatoire");
            }

            if (description.getText().length() == 0) {
                descriptionError.setText("Champs Obligatoire");
            }
            if (Pattern.matches("^[a-zA-Z]*$", nom.getText()) == false) {
                nomJeuxError.setText("Seulement des lettres");
            }
            if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                descriptionError.setText("Seulement des lettres");
            }
        }
    }

}
