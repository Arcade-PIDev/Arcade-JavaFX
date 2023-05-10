/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Evenement;
import arcade.Service.ServiceEvenement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author zeine
 */
public class FXMLControllerAjouterE {
    
    @FXML
    private TextField TFNom;
    @FXML
    private TextField TFLieu;
    @FXML
    private TextField TFAffiche;
    @FXML
    private TextArea TADesc;
    @FXML
    private TextField TFNbrP;
    @FXML
    private TextField TFPrix;
    @FXML
    private DatePicker DPDateD;
    @FXML
    private DatePicker DPDateF;
    
    @FXML
    private Button chooseAffiche;
    @FXML
    private AnchorPane content;
    final FileChooser fileChooser = new FileChooser();
    Evenement events = new Evenement();
        
    Stage primaryStage;    
    @FXML
    private void chooseAffiche(ActionEvent event) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        fileChooser.setTitle("Save");
        File file = fileChooser.showOpenDialog(primaryStage);
        TFAffiche.clear();

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
            File dest = new File("C:\\xampp\\htdocs\\integration\\public\\afficheEvent\\" + fileName + ".png");

            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(addCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<File> files = Arrays.asList(file);
            printLog(TFAffiche, files,fileName);
            events.setAfficheE(fileName+".png");
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
    
     @FXML
    private void ajouter(ActionEvent event) {
       
        if (TFNom.getText().isEmpty() || TFLieu.getText().isEmpty() || TFAffiche.getText().isEmpty() 
                || TADesc.getText().isEmpty() || TFNbrP.getText().isEmpty() || TFPrix.getText().isEmpty()
                || DPDateD.getValue() == null || DPDateF.getValue() == null) 
            {
           
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("ce champs ne peut pas etre vide");
            alert.showAndWait();
            return;
            }

    
            int nbrPlaces;
            try {
                nbrPlaces = Integer.parseInt(TFNbrP.getText());
                if (nbrPlaces <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Nombre de places doit etre un entier positive");
                alert.showAndWait();
                return;
            }

    
            float prixTicket;
            try {
                prixTicket = Float.parseFloat(TFPrix.getText());
                if (prixTicket <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Prix ticket doit etre un entier positive");
                alert.showAndWait();
                return;
            }
            
            LocalDate dateDebutE = DPDateD.getValue();
            LocalDate dateFinE = DPDateF.getValue();
            if (dateFinE.isBefore(dateDebutE)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La date de fin doit être après la date de début");
                alert.showAndWait();
                return;
            }
            
            LocalDate currentDate = LocalDate.now();
            if (dateDebutE.isBefore(currentDate)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La date de début doit être après la date actuelle");
                alert.showAndWait();
                return;
            }


            Evenement e = new Evenement();
            e.setNomEvent(TFNom.getText());
            e.setLieu(TFLieu.getText());
           String affichePath = TFAffiche.getText();
            if (affichePath == null || affichePath.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir une affiche");
                alert.showAndWait();
                return;
            }
            e.setAfficheE(affichePath);

            e.setDescriptionEvent(TADesc.getText());
            e.setNbrPlaces(nbrPlaces);
            e.setPrixTicket(prixTicket);
            e.setDateDebutE(java.sql.Date.valueOf(DPDateD.getValue()));
            e.setDateFinE(java.sql.Date.valueOf(DPDateF.getValue()));

    
            ServiceEvenement sp = new ServiceEvenement();
            sp.ajouter(e);

    
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succés");
            alert.setHeaderText(null);
            alert.setContentText("Evenement ajouté");
            alert.showAndWait();


            TFNom.clear();
            TFLieu.clear();
            TFAffiche.clear();
            TADesc.clear();
            TFNbrP.clear();
            TFPrix.clear();
            DPDateD.setValue(null);
            DPDateF.setValue(null);
}

    private FXMLEventController EventController;

    public void setFXMLEventController(FXMLEventController EventController) {
        this.EventController = EventController;
    }
    
    @FXML
    private Button backButton;



   public void initialize() {
    
   }



    @FXML
    public void cancelButton(ActionEvent event)
    {
        try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                        Parent root = loader.load();
                        HomeController controller = loader.getController();
                        controller.changePage("Evenement");

                        TFNom.getScene().setRoot(root);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
    }
    }
