/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Services.CategorieService;
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
public class EditCategorieController implements Initializable {

    @FXML
    private TextField nomCategorie;
    @FXML
    private Label nomCategorieError;
    @FXML
    private TextField description;
    @FXML
    private Label descriptionError;
    @FXML
    private Button cancel;
    @FXML
    private Button editCategorie;
    private Categorie categorie;

    public void setCategorie(Categorie c) {
        this.categorie = c;
        nomCategorie.setText(categorie.getNomCategorie());
        description.setText(categorie.getDescription());
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
            controller.changePage("Categories");

            editCategorie.getScene().setRoot(root);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void editCategorie(ActionEvent event) {
        if (nomCategorie.getText().length() != 0 && description.getText().length() != 0) {
            if (Pattern.matches("^[a-zA-Z]*$", nomCategorie.getText()) == true && Pattern.matches("^[a-zA-Z]*$", description.getText()) == true) {

                categorie.setNomCategorie(nomCategorie.getText());
                categorie.setDescription(description.getText());

                CategorieService serv = new CategorieService();
                try {
                    System.out.println("amira1");
                    serv.update(categorie);
                    System.out.println("amira2");
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                        Parent root = loader.load();
                        HomeController controller = loader.getController();
                        controller.changePage("Categories");

                        editCategorie.getScene().setRoot(root);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (Pattern.matches("^[a-zA-Z]*$", nomCategorie.getText()) == false) {
                    nomCategorieError.setText("Seulement des lettres");
                }
                if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                    descriptionError.setText("Seulement des lettres");
                }
            }
        } else {
            if (nomCategorie.getText().length() == 0) {
                nomCategorieError.setText("Champs Obligatoire");
            }

            if (description.getText().length() == 0) {
                descriptionError.setText("Champs Obligatoire");
            }
            if (Pattern.matches("^[a-zA-Z]*$", nomCategorie.getText()) == false) {
                nomCategorieError.setText("Seulement des lettres");
            }
            if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                descriptionError.setText("Seulement des lettres");
            }
        }
    }

}
