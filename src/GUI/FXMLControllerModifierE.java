/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import Services.ServiceEvenement;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author zeine
 */
public class FXMLControllerModifierE {
    
    
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
    
    private Evenement evenement;
    
     @FXML
    private Button chooseAffiche;
    
    @FXML
        private void chooseAffiche(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une affiche");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
            if (file != null) {
                TFAffiche.setText(file.getAbsolutePath());
            }
        }
    
    
    public void setEvenement(Evenement evenement) {
        
        this.evenement = evenement;
        TFNom.setText(evenement.getNomEvent());
        TFLieu.setText(evenement.getLieu());
        TFAffiche.setText(evenement.getAfficheE());
        TADesc.setText(evenement.getDescriptionEvent());
        TFNbrP.setText(String.valueOf(evenement.getNbrPlaces()));
        TFPrix.setText(String.valueOf(evenement.getPrixTicket()));
        DPDateD.setValue(new java.sql.Date(evenement.getDateDebutE().getTime()).toLocalDate());
        DPDateF.setValue(new java.sql.Date(evenement.getDateFinE().getTime()).toLocalDate());
}

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
        if (TFNom.getText().isEmpty() || TFLieu.getText().isEmpty() || TFAffiche.getText().isEmpty() 
            || TADesc.getText().isEmpty() || TFNbrP.getText().isEmpty() || TFPrix.getText().isEmpty()
            || DPDateD.getValue() == null || DPDateF.getValue() == null)
               {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
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
                    alert.setTitle("Error");
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
                   e.setDescriptionEvent (TADesc.getText());
                   e.setNbrPlaces(Integer.parseInt(TFNbrP.getText()));
                   e.setPrixTicket(Float.parseFloat(TFPrix.getText()));
                   e.setDateDebutE(java.sql.Date.valueOf(DPDateD.getValue())); 
                   e.setDateFinE(java.sql.Date.valueOf(DPDateF.getValue())); 

                   ServiceEvenement sp =new ServiceEvenement();
                   sp.modifier(evenement.getId(),e);


                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succés");
                    alert.setHeaderText(null);
                    alert.setContentText("Evenement modifié");
                    alert.showAndWait();

    
     }
    
    private FXMLEventController EventController;

    public void setFXMLEventController(FXMLEventController EventController) {
        this.EventController = EventController;
    }
    
    @FXML
    private Button backButtonEdit;



    public void initialize() {
        
        backButtonEdit.setOnAction(event -> {
             
             FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEvenement.fxml"));
             Parent displayParent = null;
             try {
                 displayParent = loader.load();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLControllerAjouterE.class.getName()).log(Level.SEVERE, null, ex);
             }

             FXMLEventController EventController = loader.getController();

             Scene displayScene = new Scene(displayParent);
             Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
             window.setScene(displayScene);
             window.show();

             EventController.refreshTable();
         });
          chooseAffiche.setOnAction(this::chooseAffiche);
}
   
}
