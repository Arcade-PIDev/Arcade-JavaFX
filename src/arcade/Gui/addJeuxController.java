/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Jeux;
import arcade.Service.JeuxService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class addJeuxController implements Initializable{
    @FXML
    private TextField nomJeux;
    @FXML
    private AnchorPane contentJeux;
    @FXML
    private VBox content;
    @FXML
    private TextField description;
    @FXML
    private Button uploadJeux;
    @FXML
    private Button annulerJeux;
    @FXML
    private Button addJeux;
    @FXML
    private TextField image;
    @FXML
    private TextField Genre;
    @FXML
    private TextField color;
    @FXML
    private Label nomJeuxError;
    @FXML
    private Label descriptionError;
    @FXML
    private Label imageError;
    @FXML
    private Label genreError;
    @FXML
    private Label colorError;
    Jeux cat = new Jeux();
    final FileChooser fileChooser = new FileChooser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
        public void annulerJeux(ActionEvent event)
        {
            try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();
                    HomeController controller = loader.getController();
                    controller.changePage("Jeux");

                    addJeux.getScene().setRoot(root);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
        }
        
    @FXML
    private void addJeux(ActionEvent event) {
        nomJeuxError.setText("");
        descriptionError.setText("");
        imageError.setText("");
        genreError.setText("");
        colorError.setText("");
        if (nomJeux.getText().length() != 0 && description.getText().length() != 0 && image.getText().length() != 0 && Genre.getText().length() != 0 && color.getText().length() != 0) {
            if (Pattern.matches("^[a-zA-Z]*$", nomJeux.getText()) == true && Pattern.matches("^[a-zA-Z]*$", description.getText()) == true) {

                cat.setNom(nomJeux.getText());
                cat.setDescription(description.getText());
                cat.setGenre(Genre.getText());
                cat.setColor(color.getText());


                JeuxService serv = new JeuxService();

                try {
                    serv.ajouter(cat);


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();
                    HomeController controller = loader.getController();
                    controller.changePage("Jeux");


                    addJeux.getScene().setRoot(root);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (Pattern.matches("^[a-zA-Z]*$", nomJeux.getText()) == false) {
                    nomJeuxError.setText("Seulement des lettres");
                }
                if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                    descriptionError.setText("Seulement des lettres");
                }
            }

        } else {
            if (nomJeux.getText().length() == 0) {
                nomJeuxError.setText("Champs Obligatoire");
            }

            if (description.getText().length() == 0) {
                descriptionError.setText("Champs Obligatoire");
            }

            if (image.getText().length() == 0) {
                imageError.setText("Champs Obligatoire");
            }
             if (Genre.getText().length() == 0) {
                genreError.setText("Champs Obligatoire");
            }
              if (color.getText().length() == 0) {
                colorError.setText("Champs Obligatoire");
            }
            if (Pattern.matches("^[a-zA-Z]*$", nomJeux.getText()) == false) {
                nomJeuxError.setText("Seulement des lettres");
            }
            if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                descriptionError.setText("Seulement des lettres");
            }

        }

    }
        
    private Desktop desktop = Desktop.getDesktop();
    Stage primaryStage;    
    
    @FXML
    private void uploadJeux(ActionEvent event) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        fileChooser.setTitle("Save");
        File file = fileChooser.showOpenDialog(primaryStage);
        image.clear();

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
            File dest = new File("C:\\xampp\\htdocs\\ArcadePi\\public\\Jeux\\" + fileName + ".png");

            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(addJeuxController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<File> files = Arrays.asList(file);
            printLog(image, files,fileName);
            cat.setImage(fileName+".png");
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
