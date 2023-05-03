/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;


import static arcade.Utils.MyConnection.MyConnection;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nadak
 */
public class seancecoachingAddController implements Initializable {

    @FXML
    private Button btnajouter;

    seancecoachingController obj = new seancecoachingController();

    @FXML
    private TextField desctf;
    @FXML
    private TextField imagetf;
    @FXML
    private TextField prixtf;
    @FXML
    private TextField titretf;
    @FXML
    private Button btnbrowse;
    @FXML
    private DatePicker datedebtf;
    @FXML
    private DatePicker datefintf;
    @FXML
    private ImageView imageview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private boolean validateFields() {
        LocalDate datedeb = datedebtf.getValue();
        LocalDate datefin = datefintf.getValue();
        LocalDate today = LocalDate.now();
        if (desctf.getText().isEmpty()) {
            showAlert("Error", "Description field is required.");
            return false;
        }
        if (titretf.getText().isEmpty()) {
            showAlert("Error", "Titre field is required.");
            return false;
        }
        if (prixtf.getText().isEmpty()) {
            showAlert("Error", "Prix field is required.");
            return false;
        } else if (!prixtf.getText().matches("\\d+(\\.\\d+)?")) {
            showAlert("Error", "Prix must be a number.");
            return false;
        }
        if (datedeb == null) {
            showAlert("Error", "Please select a start date.");
            return false;
        }

        if (datedeb.isBefore(today)) {
            showAlert("Error", "The start date must be in the future.");
            return false;
        }

        if (datefin == null) {
            showAlert("Error", "Please select an end date.");
            return false;
        }

        if (datefin.isBefore(today)) {
            showAlert("Error", "The end date must be in the future.");
            return false;
        }

        if (datefin.isBefore(datedeb)) {
            showAlert("Error", "The end date must be after the start date.");
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
                obj.showseancecoachings();
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
    Double prix = Double.valueOf(prixtf.getText());
    String titre = titretf.getText();
    String datedeb = String.valueOf(datedebtf.getValue());
    String datefin = String.valueOf(datefintf.getValue());

    validateFields();
    String query = "INSERT INTO `seancecoaching` (`date_debut_seance`, `date_fin_seance`, `prix_seance`, `description_seance`, `image_seance`, `titre_seance`) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = MyConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, datedeb);
        ps.setString(2, datefin);
        ps.setDouble(3, prix);
        ps.setString(4, desc);
        ps.setString(5, image);
        ps.setString(6, titre);
        ps.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("seancecoaching ");
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
