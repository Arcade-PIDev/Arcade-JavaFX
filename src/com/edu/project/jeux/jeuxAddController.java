/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.jeux;

import static DBCnx.MyConnection.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class jeuxAddController implements Initializable {

    @FXML
    private Button btnajouter;

    jeuxController obj = new jeuxController();

    @FXML
    private TextField desctf;
    @FXML
    private TextField imagetf;

    @FXML
    private Button btnbrowse;

    @FXML
    private ImageView imageview;
    @FXML
    private TextField genretf;
    @FXML
    private TextField nomtf;
    @FXML
    private ColorPicker colortf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private boolean validateFields() {
 
        LocalDate today = LocalDate.now();
        if (desctf.getText().isEmpty()) {
            showAlert("Error", "desc field is required.");
            return false;
        }
        if (genretf.getText().isEmpty()) {
            showAlert("Error", "genre field is required.");
            return false;
        }
           if (nomtf.getText().isEmpty()) {
            showAlert("Error", "nom field is required.");
            return false;
        }

    
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void OnCreate(ActionEvent event) {
        if (event.getSource() == btnajouter) {
            if (validateFields()) {
                insert();
                obj.showjeuxs();
            }
        }

    }

    private void executeQuery(String query) {
        Connection conn = MyConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

private void insert() {
    String desc = desctf.getText();
    String image = imagetf.getText();
    String nom = nomtf.getText();
    String genre = genretf.getText();
    Color color = colortf.getValue();
    String colorString = color.toString();
    validateFields();
    String query = "INSERT INTO `jeux`( `nom`, `description`, `image`, `genre`, `color`) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = MyConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, nom);
        ps.setString(2, desc);
        ps.setString(3, image);
        ps.setString(4, genre);
        ps.setString(5, colorString);
        ps.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jeux ");
        alert.setHeaderText(null);
        alert.setContentText("added  succesfuly");
        alert.showAndWait();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    @FXML
    private void actionperformed(ActionEvent event) throws FileNotFoundException {
       FileChooser filechooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        
        //filechooser.setInitialDirectory(new File("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Notex\\src\\com\\gn\\module\\evenements\\image"));
        filechooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        
        File file = filechooser.showOpenDialog(new Stage());
        if (file != null) {
    Image image = new Image(new FileInputStream(file));
    imagetf.setText(file.getName());
    // set fit height and width of ImageView to the image size
        imageview.setImage(image);
    // set the image view in your UI, e.g.
    
    
        }  
    }
}
