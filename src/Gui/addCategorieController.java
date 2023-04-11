/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Services.CategorieService;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Amira
 */
public class addCategorieController implements Initializable{
    @FXML
    private TextField nomCategorie;
    @FXML
    private TextField descriptionCategorie;
    @FXML
    private Button uploadCategorie;
    @FXML
    private Button annulerCategorie;
    @FXML
    private Button addCategorie;
    @FXML
    private TextField ImageCategorie;
    Categorie cat = new Categorie();
    final FileChooser fileChooser = new FileChooser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
        public void addCategorie()
        {
            cat.setNomCategorie(nomCategorie.getText());
            cat.setDescription(descriptionCategorie.getText());
            CategorieService serv = new CategorieService();
            
            try{
                serv.ajouter(cat);
                /*
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();
                    Home controller = loader.getController();
                    controller.changePage("Categories");

                    addCategorie.getScene().setRoot(root);*/
            }
        catch (Exception ex){
                System.out.println(ex);
            }

        }
        
        
    private Desktop desktop = Desktop.getDesktop();
    Stage primaryStage;

    @FXML
    private void upload(ActionEvent event) {

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        fileChooser.setTitle("Save");
        File file = fileChooser.showOpenDialog(primaryStage);
        ImageCategorie.clear();

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
            File dest = new File("C:\\Users\\meriam\\Desktop\\webVersionFinal\\public\\shop\\images\\" + fileName + ".png");

            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(addCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<File> files = Arrays.asList(file);
            printLog(ImageCategorie, files,fileName);
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
