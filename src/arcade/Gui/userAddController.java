/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;
import arcade.Utils.MyConnection;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

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
        String email = emailtf.getText();
        String username = usernametf.getText();
        String pwd = BCrypt.hashpw(pwdtf.getText(), BCrypt.gensalt());
        String image = imagetf.getText();
        
        String role = list.get(list1.indexOf(roletf.getValue()));
        validateFields();
String queryInsert = "INSERT INTO `user`(`username`, `email`, `password`, `avatar`, `is_verified`, `role`) VALUES (?, ?, ?, ?, 0, ?)";
executeQuery(queryInsert, true, username, email, pwd, image, role);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("user ");
        alert.setHeaderText(null);
        alert.setContentText("added  succesfuly");
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
