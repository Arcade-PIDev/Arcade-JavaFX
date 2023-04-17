/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import Entities.Sponsor;
import Services.ServiceEvenement;
import Services.ServiceSponsor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author zeine
 */
public class FXMLSponsorController implements Initializable{

   
    @FXML
    private TableView<Sponsor> tableViewS;

    @FXML
    private TableColumn<Sponsor, String> nomSp;

    @FXML
    private TableColumn<Sponsor, Integer> numTel;

    @FXML
    private TableColumn<Sponsor, String> email;
    
     @FXML
    private TableColumn<Sponsor, String> adresse;

    @FXML
    private TableColumn<Sponsor, String> domaine;

    @FXML
    private TableColumn<Sponsor, String> logo;
    
    @FXML
    private TableColumn<Sponsor, Float> montant;

    @FXML
    private TableColumn<Sponsor, Integer> eventFk;

   
    @FXML
    private Button addButton;
   
    private ObservableList<Sponsor> sponsors = FXCollections.observableArrayList();
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        ServiceSponsor sp = new ServiceSponsor();
        sponsors.addAll(sp.afficher());
        nomSp.setCellValueFactory(new PropertyValueFactory<>("NomSponsor"));
        numTel.setCellValueFactory(new PropertyValueFactory<>("NumTelSponsor"));
        logo.setCellValueFactory(new PropertyValueFactory<>("logoSponsor"));
       /*afficheE.setCellFactory(column -> new TableCell<Evenement, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(getImageView(imagePath).getImage());
                    setGraphic(imageView);
                }
            }
        });*/
        email.setCellValueFactory(new PropertyValueFactory<>("EmailSponsor"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("AdresseSponsor"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("DomaineSponsor"));
        montant.setCellValueFactory(new PropertyValueFactory<>("MontantSponsoring"));
        eventFk.setCellValueFactory(new PropertyValueFactory<>("IDEventsFK"));
       
        tableViewS.setItems(sponsors);
        
        
        addButton.setOnAction(event -> {
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAddSponsor.fxml"));
        Parent addFormParent = null;
            try {
                addFormParent = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(FXMLSponsorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        FXMLControllerAjouterS addFormController = loader.getController();
        addFormController.setFXMLSponsorController(this);

        Scene addFormScene = new Scene(addFormParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addFormScene);
        window.show();
    });
      
    }
    
   private ImageView getImageView(String imagePath) {
    Image image = new Image(imagePath);
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(50);
    imageView.setPreserveRatio(true);
    return imageView;
}

    @FXML
    private void ShowAllSponsor(ActionEvent event) {
         ServiceSponsor sp =new ServiceSponsor();
       sponsors.setAll(sp.afficher());
       
        
    }
    
    
       @FXML
      
    private void supprimer(ActionEvent event) {
    
           Sponsor selectedSponsor = tableViewS.getSelectionModel().getSelectedItem();
           if(selectedSponsor != null){
           ServiceSponsor sp = new ServiceSponsor();
           sp.supprimer(selectedSponsor);
        
           sponsors.remove(selectedSponsor);
             }

   }

    void refreshTable() {
     sponsors.clear();
    ServiceSponsor sp = new ServiceSponsor();
    sponsors.addAll(sp.afficher());    }
    
    
    @FXML
    private void editSelectedSponsor(ActionEvent event) throws IOException {
        Sponsor selectedSponsor = tableViewS.getSelectionModel().getSelectedItem();
        if (selectedSponsor != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditSponsor.fxml"));
            Parent editView = loader.load();
            FXMLControllerModifierS editController = loader.getController();
            editController.setSponsor(selectedSponsor);
            Scene editScene = new Scene(editView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(editScene);
            window.show();
        }
     }
     
    
}
