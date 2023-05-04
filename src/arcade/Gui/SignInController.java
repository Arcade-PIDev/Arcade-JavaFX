/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import static arcade.Arcade.loggedInUser;
import arcade.Entities.user;
import arcade.Utils.database;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class SignInController implements Initializable {

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField emailtf;

    @FXML
    private PasswordField passwordtf;

    @FXML
    private Hyperlink login_mdp_oublie;

    @FXML
    private AnchorPane login_page;

    @FXML
    private AnchorPane mdp_oublie_page;

    @FXML
    private CheckBox login_afficher_mdp;

    @FXML
    private Button loginbtn;

    @FXML
    private TextField afficher_mdp_txt;

    @FXML
    private TextField mdp_oublier_email;

    @FXML
    private ComboBox<?> mdp_oublie_question;

    @FXML
    private TextField mdp_oublie_reponse;

    @FXML
    private Button mdp_oublie_continue;

    @FXML
    private AnchorPane new_mdp_page;

    @FXML
    private Button mdp_oublie_back;

    @FXML
    private Button mdp_oublie_back1;

    @FXML
    private PasswordField new_mdp;

    @FXML
    private Button new_mdp_continuer;

    @FXML
    private PasswordField confirm_new_mdp;

    private String[] questionlist = {"Quel est votre film préféré ?",
        "Dans quelle ville avez-vous rencontré votre conjoint ?",
        "Quel est le nom de votre animal de compagnie ?", "Quel est votre plat préféré ?",
        "Quel est le nom de votre meilleur ami d'enfance ?"};

    public void questions() {
        List<String> listQ = new ArrayList<>();

        for (String data : questionlist) {
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableList(listQ);
        mdp_oublie_question.setItems(listData);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questions();
    }

    private boolean validateFields() {
        if (emailtf.getText().isEmpty()) {
            showAlert("Error", "Le champ de l'adresse Email est requis");
            return false;
        } else {
            String emaile = emailtf.getText().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!emaile.matches(emailRegex)) {
                showAlert("Error", "Veuillez entrer une adresse email valide.");
                return false;
            }
        }

        if (passwordtf.getText().isEmpty()) {
            showAlert("Error", "Le mot de passe est requis.");
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

    private ResultSet executeQuery(String query, boolean isDataManipulation) {
        Connection conn = database.getInstance().getCon();
        Statement st;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            if (isDataManipulation) {
                st.executeUpdate(query);
            } else {
                rs = st.executeQuery(query);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    @FXML
    private void login(ActionEvent event) {
        String email = emailtf.getText();
        String password = passwordtf.getText();

        try {
            // Execute SELECT query to retrieve the user with the given email
            String query = "SELECT * FROM user WHERE email='" + email + "'";
            ResultSet rs = executeQuery(query, false);

            // If query returns a result, user with the given email exists
            if (rs.next()) {
                String storedHash = rs.getString("password");
                String role = rs.getString("role");  
                loggedInUser.setId( rs.getInt("id"));
                
                System.out.println(rs.getInt("id"));
                // Use BCrypt to check if entered password matches stored hash
                if (BCrypt.checkpw(password, storedHash)) {
                    // Close the current window
                    Stage stage = (Stage) loginbtn.getScene().getWindow();
                    stage.close();

                    // Load the appropriate FXML file and show it in a new window based on user role
                    if (role.equals("admin")) {
                        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
                        Scene scene = new Scene(root);
                        Stage newStage = new Stage();
                        newStage.setScene(scene);
                        newStage.show();
                    } else {
                        user loggedInUser = new user();
                        Parent root = FXMLLoader.load(getClass().getResource("homeFront.fxml"));
                        Scene scene = new Scene(root);
                        Stage newStage = new Stage();
                        newStage.setScene(scene);
                        newStage.show();
                    }
                } else {
                    // If entered password does not match stored hash, show an alert indicating that authentication failed
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Authentication Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Email ou mot de passe incorrect. Veuillez réessayer.");
                    alert.showAndWait();
                }
            } else {
                // If no result is returned, show an alert indicating that the user with the given email does not exist
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Authentication Error");
                alert.setHeaderText(null);
                alert.setContentText("L'utilisateur avec l'Email donné n'existe pas. Veuillez réessayer.");
                alert.showAndWait();
            }

        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loginclear() {
        emailtf.setText("");
        passwordtf.setText("");
        new_mdp.setText("");
        confirm_new_mdp.setText("");
        mdp_oublier_email.setText("");
        mdp_oublie_reponse.setText("");
        mdp_oublie_question.getSelectionModel().clearSelection();

    }

    public void showpassword() {
        if (login_afficher_mdp.isSelected()) {
            afficher_mdp_txt.setText(passwordtf.getText());
            afficher_mdp_txt.setVisible(true);
            passwordtf.setVisible(false);
        } else {
            afficher_mdp_txt.setVisible(false);
            passwordtf.setVisible(true);
        }
    }

    public void forgotpw() {
        String email = mdp_oublier_email.getText();
        String question = (String) mdp_oublie_question.getSelectionModel().getSelectedItem();
        String reponse = mdp_oublie_reponse.getText();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (mdp_oublier_email.getText().isEmpty() || mdp_oublie_question.getSelectionModel().getSelectedItem() == null || mdp_oublie_reponse.getText().isEmpty()) {
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir le formulaire s'il vous plaît !!");
            alert.showAndWait();
        } else {
            try {
                // Prepare the SELECT query to retrieve the user with the given email and matching security question and answer
                String query = "SELECT * FROM user WHERE email = ? AND question = ? AND reponse = ?";
                PreparedStatement ps = database.getInstance().getCon().prepareStatement(query);
                ps.setString(1, email);
                ps.setString(2, question);
                ps.setString(3, reponse);

                // Execute the SELECT query and retrieve the results
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    login_page.setVisible(false);
                    new_mdp_page.setVisible(true);
                    mdp_oublie_page.setVisible(false);

                } else {
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Informations incorrectes.");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void changerMdp() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

        String pwd = BCrypt.hashpw(new_mdp.getText(), BCrypt.gensalt());
        if (new_mdp.getText().isEmpty() || confirm_new_mdp.getText().isEmpty()) {
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir le formulaire s'il vous plaît !!");
            alert.showAndWait();
        } else if (!new_mdp.getText().equals(confirm_new_mdp.getText())) {
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Le mot de passe ne correspond pas.");
            alert.showAndWait();

        } else if (new_mdp.getText().length() < 8) {
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Le mot de passe doit comporter au moins 8 caractères.");
            alert.showAndWait();
        } else {
            String query = "UPDATE user SET password ='" + pwd + "'"
                    + "WHERE email = '" + mdp_oublier_email.getText() + "'";
            try (Connection conn = database.getInstance().getCon();
                    PreparedStatement pstmt = conn.prepareStatement(query)) {
                //pstmt.setString(1, confirm_new_mdp.getText());
                pstmt.executeUpdate();
                alert1.setTitle("information");
                alert1.setHeaderText(null);
                alert1.setContentText("mot de passe changé !!");
                alert1.showAndWait();
                login_page.setVisible(true);
                new_mdp_page.setVisible(false);
                mdp_oublie_page.setVisible(false);
                loginclear();
            } catch (SQLException e) {
                System.err.println("Error executing update query: " + e.getMessage());
            }

        }

    }

    public void switchpage(ActionEvent event) {
        if (event.getSource() == mdp_oublie_back) {

            login_page.setVisible(true);
            new_mdp_page.setVisible(false);
            mdp_oublie_page.setVisible(false);
            loginclear();

        }

        if (event.getSource() == mdp_oublie_back1) {

            login_page.setVisible(true);
            new_mdp_page.setVisible(false);
            mdp_oublie_page.setVisible(false);
            loginclear();

        }
        if (event.getSource() == login_mdp_oublie) {

            mdp_oublie_page.setVisible(true);
            new_mdp_page.setVisible(false);
            login_page.setVisible(false);

        }

    }

}
