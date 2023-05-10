/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;
import arcade.Entities.user;
import arcade.Utils.database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class userAddController implements Initializable {

    @FXML
    private Button btnajouter;

    userController obj = new userController();
    @FXML
    private TextField emailtf;
    private TextField lasttf;
    @FXML
    private TextField pwdtf;
    @FXML
    private TextField usernametf;
    private TextField firsttf;
    @FXML
    private ComboBox roletf;
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private TextField imagetf;
    @FXML
    private Button btnajouter1;

    final FileChooser fileChooser = new FileChooser();
    user users = new user();
        
    Stage primaryStage; 
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
    
    @FXML
        public void cancel(ActionEvent event)
        {
            try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();
                    HomeController controller = loader.getController();
                    controller.changePage("Users");

                    btnajouter.getScene().setRoot(root);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
        }

    private boolean validateFields() {
        if (emailtf.getText().isEmpty()) {
            showAlert("Error", "Email field is required.");
            return false;
        } else {
            String email = emailtf.getText().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!email.matches(emailRegex)) {
                showAlert("Error", "Please enter a valid email address.");
                return false;
            }
        }

        if (imagetf.getText().isEmpty()) {
            showAlert("Error", "image field is required.");
            return false;
        }
        if (pwdtf.getText().isEmpty()) {
            showAlert("Error", "Password is required.");
            return false;
        } else if (pwdtf.getText().length() < 8) {
            showAlert("Error", "Password must be at least 8 characters long.");
            return false;
        }

        if (usernametf.getText().isEmpty()) {
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
    private void OnCreate(ActionEvent event) {
        if (event.getSource() == btnajouter) {
            if (validateFields()) {
                insert();
                obj.showusers();
            }
        }

    }

private ResultSet executeQuery(String query, boolean isDataManipulation, Object... parameters) {
    Connection conn = database.getInstance().getCon();
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
        String email = emailtf.getText();
        String username = usernametf.getText();
        String pwd = BCrypt.hashpw(pwdtf.getText(), BCrypt.gensalt());
        String image = imagetf.getText();
        
        String role = list.get(list1.indexOf(roletf.getValue()));
        validateFields();
String queryInsert = "INSERT INTO `user`(`username`, `email`, `password`, `avatar`, `is_verified`, `role`, `question`, `reponse`) VALUES (?, ?, ?, ?, 0, ?,null,null)";
executeQuery(queryInsert, true, username, email, pwd, image, role);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        System.out.println(queryInsert);
        alert.setTitle("user ");
        alert.setHeaderText(null);
        alert.setContentText("added  succesfuly");
        alert.showAndWait();
    }
    
    @FXML
    private void browse(ActionEvent event) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        fileChooser.setTitle("Save");
        File file = fileChooser.showOpenDialog(primaryStage);
        imagetf.clear();

        if (file != null) {
            // generate a fileName
            String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk" + "lmnopqrstuvwxyz!@#$%&";
            Random rnd = new Random();
            StringBuilder sb = new StringBuilder(10);
            for (int i = 0; i < 10; i++) {
                sb.append(chars.charAt(rnd.nextInt(chars.length())));
            }
           String fileName = sb.toString();

            File source = file;
            File dest = new File("C:\\xampp\\htdocs\\integration\\public\\userpic\\" + fileName + ".png");

            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(addCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<File> files = Arrays.asList(file);
            printLog(imagetf, files,fileName);
            users.setAvatar(fileName+".png");
        }
    }
    
        private void printLog(TextField textArea, List<File> files,String fileName) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(fileName+".png");
        }
    }
}
