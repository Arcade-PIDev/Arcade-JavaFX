/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import org.mindrot.jbcrypt.BCrypt;
/**
 * FXML Controller class
 *
 * @author pc
 */
public class SignUpController implements Initializable {

    private TextField firstName;
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    
    @FXML
    private ComboBox roletf;
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private Button signup;
    userController obj = new userController();
    @FXML
    private PasswordField passwordtf;
    @FXML
    private TextField imagetf;
    @FXML
    private Button browse;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        list.add("admin");
        list.add("user");
        list1.add("Admin");
        list1.add("User");
        roletf.setItems(list1);
        
           
    }

    private boolean validateFields() {
        if (email.getText().isEmpty()) {
            showAlert("Error", "Email field is required.");
            return false;
        } else {
            String emaile = email.getText().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!emaile.matches(emailRegex)) {
                showAlert("Error", "Please enter a valid email address.");
                return false;
            }
        }

        if (imagetf.getText().isEmpty()) {
            showAlert("Error", "image field is required.");
            return false;
        }
        if (passwordtf.getText().isEmpty()) {
            showAlert("Error", "Password is required.");
            return false;
        } else if (passwordtf.getText().length() < 8) {
            showAlert("Error", "Password must be at least 8 characters long.");
            return false;
        }

        if (username.getText().isEmpty()) {
            showAlert("Error", "respo field is required.");
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
    private void signup(ActionEvent event) {
        
        if (event.getSource() == signup) {
            if (validateFields()) {
                insert();
                obj.showusers();
            }
        }
    }
private ResultSet executeQuery(String query, boolean isDataManipulation, Object... parameters) {
    Connection conn = MyConnection();
    PreparedStatement ps;
    ResultSet rs = null;
    try {
        if (isDataManipulation) {
            ps = conn.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i + 1, parameters[i]);
            }
            ps.executeUpdate();
        } else {
            ps = conn.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i + 1, parameters[i]);
            }
            rs = ps.executeQuery();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return rs;
}


private void insert() {
    String emailtf = email.getText();
    String usernametf = username.getText();
    String pwd = BCrypt.hashpw(passwordtf.getText(), BCrypt.gensalt()) ;
    String image = imagetf.getText();
   
    String role = list.get(list1.indexOf(roletf.getValue()));
    validateFields();
    
    // Check if email already exists
    String queryEmail = "SELECT * FROM `user` WHERE `email` = ?";
    ResultSet rs = executeQuery(queryEmail, false, emailtf);
    try {
        if (rs.next()) {
            // Email already exists, show alert and return
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign Up Failed");
            alert.setHeaderText(null);
            alert.setContentText("Email already exists. Please use a different email.");
            alert.showAndWait();
            return;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    // Insert new user
String queryInsert = "INSERT INTO `user`(`username`, `email`, `password`, `avatar`, `is_verified`, `role`) VALUES (?, ?, ?, ?, 0, ?)";
executeQuery(queryInsert, true, usernametf, emailtf, pwd, image, role);

    
    // Show success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Sign Up Successful");
    alert.setHeaderText(null);
    alert.setContentText("Signed up successfully. Please log in.");
    alert.showAndWait();
}
    @FXML
    private void browse(ActionEvent event) throws FileNotFoundException {
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
            
            // set the image view in your UI, e.g.
    }
    }

}
