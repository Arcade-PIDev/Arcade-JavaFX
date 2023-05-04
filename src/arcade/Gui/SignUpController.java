/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;


import arcade.Utils.database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane signup_form;
    
    @FXML
    private Button signup;
    userController obj = new userController();
    @FXML
    private PasswordField passwordtf;
    @FXML
    private TextField imagetf;
    @FXML
    private Button browse;
    
    @FXML
    private ComboBox<?> signup_question;

    @FXML
    private TextField signup_reponse;

    
    private String[] questionlist = {"Quel est votre film préféré ?",
    "Dans quelle ville avez-vous rencontré votre conjoint ?",
    "Quel est le nom de votre animal de compagnie ?","Quel est votre plat préféré ?",
    "Quel est le nom de votre meilleur ami d'enfance ?"};
    public void questions(){
        List<String>listQ = new ArrayList<>();
        
        for (String data : questionlist){
         listQ.add(data);
        }
        
        ObservableList listData = FXCollections.observableList(listQ);
        signup_question.setItems(listData);
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        questions();
        
    }

    private boolean validateFields() {
        if (email.getText().isEmpty()) {
            showAlert("Error", "Le champ de Email est obligatoire.");
            return false;
        } else {
            String emaile = email.getText().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!emaile.matches(emailRegex)) {
                showAlert("Error", "Veuillez entrer une adresse e-mail valide.");
                return false;
            }
        }

        if (imagetf.getText().isEmpty()) {
            showAlert("Error", "Le champ de l'avatar est obligatoire.");
            return false;
        }
        if (passwordtf.getText().isEmpty()) {
            showAlert("Error", "Le champ du mot de passe est obligatoire.");
            return false;
        } else if (passwordtf.getText().length() < 8) {
            showAlert("Error", "Le mot de passe doit contenir au moins 8 caracteres.");
            return false;
        }

        if (username.getText().isEmpty()) {
            showAlert("Error", "Vous devez ecrire votre Username.");
            return false;
        }
        if (signup_question.getSelectionModel().getSelectedItem()==null ) {
            showAlert("Error", "Vous devez choisir une question.");
            return false;
        }
        if (signup_reponse.getText().isEmpty()) {
            showAlert("Error", "Vous devez répondre a la question.");
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

public void registerclear(){
    email.setText("");
    username.setText("");
    passwordtf.setText("");
    imagetf.setText("");
    signup_reponse.setText("");
    signup_question.getSelectionModel().clearSelection();

}

private void insert() {
    String emailtf = email.getText();
    String usernametf = username.getText();
    String pwd = BCrypt.hashpw(passwordtf.getText(), BCrypt.gensalt()) ;
    String image = imagetf.getText();
    String reponse = signup_reponse.getText();
  
    String qst = signup_question.getValue().toString();
    validateFields();
    
    // Check if email already exists
    String queryEmail = "SELECT * FROM `user` WHERE `email` = ?";
    ResultSet rs = executeQuery(queryEmail, false, emailtf);
    try {
        if (rs.next()) {
            // Email already exists, show alert and return
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Échec de l'inscription.");
            alert.setHeaderText(null);
            alert.setContentText("L'adresse email existe déjà. Veuillez utiliser une adresse email différente.");
            alert.showAndWait();
            return;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    // Insert new user
String queryInsert = "INSERT INTO `user`(`username`, `email`, `password`, `avatar`, `is_verified`, `role`, `question`, `reponse`) VALUES (?, ?, ?, ?, 0, 'User', ?, ?)";
executeQuery(queryInsert, true, usernametf, emailtf, pwd, image, qst, reponse);

    
    // Show success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Inscription réussie.");
    alert.setHeaderText(null);
    alert.setContentText("Inscription réussie! Veuillez vous connecter.");
    alert.showAndWait();
    registerclear();
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
