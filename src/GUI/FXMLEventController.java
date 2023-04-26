/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import Services.ServiceEvenement;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
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
public class FXMLEventController implements Initializable{
    
   
    @FXML
    private TableView<Evenement> tableViewE;

    @FXML
    private TableColumn<Evenement, String> nomE;

    @FXML
    private TableColumn<Evenement, String> lieuE;

    @FXML
    private TableColumn<Evenement, String> afficheE;
    
     @FXML
    private TableColumn<Evenement, String> descE;

    @FXML
    private TableColumn<Evenement, String> dateD;

    @FXML
    private TableColumn<Evenement, String> dateF;
    
    @FXML
    private TableColumn<Evenement, Integer> nbrP;

    @FXML
    private TableColumn<Evenement, String> prixE;

   
    @FXML
    private Button addButton;
    
     @FXML
    private TextField searchField;
   
    private ObservableList<Evenement> evenements = FXCollections.observableArrayList();
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        ServiceEvenement sp = new ServiceEvenement();
        evenements.addAll(sp.afficher());
        nomE.setCellValueFactory(new PropertyValueFactory<>("NomEvent"));
        lieuE.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        afficheE.setCellValueFactory(new PropertyValueFactory<>("afficheE"));
            afficheE.setCellFactory(column -> new TableCell<Evenement, String>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);

                    if (empty || imagePath == null) {
                        setGraphic(null);
                    } else {
                        Image image = new Image(new File(imagePath).toURI().toString());
                        imageView.setImage(image);
                        imageView.setFitWidth(100);
                        imageView.setFitHeight(100);
                        setGraphic(imageView);
                    }
                }
            });

        descE.setCellValueFactory(new PropertyValueFactory<>("DescriptionEvent"));
        dateD.setCellValueFactory(new PropertyValueFactory<>("DateDebutE"));
        dateF.setCellValueFactory(new PropertyValueFactory<>("DateFinE"));
        nbrP.setCellValueFactory(new PropertyValueFactory<>("nbrPlaces"));
        prixE.setCellValueFactory(new PropertyValueFactory<>("PrixTicket"));
       
        tableViewE.setItems(evenements);
        
        
        addButton.setOnAction(event -> {
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAjouterEvent.fxml"));
        Parent addFormParent = null;
            try {
                addFormParent = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(FXMLEventController.class.getName()).log(Level.SEVERE, null, ex);
            }

        FXMLControllerAjouterE addFormController = loader.getController();
        addFormController.setFXMLEventController(this);

        Scene addFormScene = new Scene(addFormParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addFormScene);
        window.show();
    });
      
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        rechercheAdvanced(null);
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
    private void ShowAllEvenement(ActionEvent event) {
         ServiceEvenement sp =new ServiceEvenement();
       evenements.setAll(sp.afficher());
       
        
    }
    
    
       @FXML
      
    private void supprimer(ActionEvent event) {
    
           Evenement selectedEvent = tableViewE.getSelectionModel().getSelectedItem();
           if(selectedEvent != null){
           ServiceEvenement sp = new ServiceEvenement();
           sp.supprimer(selectedEvent);
        
           evenements.remove(selectedEvent);
             }

   }

    void refreshTable() {
     evenements.clear();
    ServiceEvenement sp = new ServiceEvenement();
    evenements.addAll(sp.afficher());    }
    
    
    @FXML
    private void editSelectedEvenement(ActionEvent event) throws IOException {
        Evenement selectedEvenement = tableViewE.getSelectionModel().getSelectedItem();
        if (selectedEvenement != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLModifierEvent.fxml"));
            Parent editView = loader.load();
            FXMLControllerModifierE editController = loader.getController();
            editController.setEvenement(selectedEvenement);
            Scene editScene = new Scene(editView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(editScene);
            window.show();
        }
}
    
  

          @FXML
          private void rechercheAdvanced(KeyEvent event) {
              String searchText = searchField.getText();
              if (searchText == null || searchText.isEmpty()) {
                  tableViewE.setItems(evenements);
                  return;
              }
              ObservableList<Evenement> searchResults = FXCollections.observableArrayList();
              for (Evenement evenment : evenements) {
                  if (evenment.getNomEvent().toLowerCase().contains(searchText.toLowerCase()) || evenment.getLieu().toLowerCase().contains(searchText.toLowerCase())) {
                      searchResults.add(evenment);
                  }
              }
              tableViewE.setItems(searchResults);
          }

            @FXML
            private void linkSponsorSideBar(ActionEvent event) throws IOException {
                Parent sponsorView = FXMLLoader.load(getClass().getResource("FXMLSponsor.fxml"));
                Scene sponsorScene = new Scene(sponsorView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sponsorScene);
                window.show();
            }
            
            
            @FXML
            private void linkEvenementSideBar(ActionEvent event) throws IOException {
                Parent sponsorView = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));
                Scene sponsorScene = new Scene(sponsorView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sponsorScene);
                window.show();
            }
            
            @FXML
            private void linkCalendar(ActionEvent event) throws IOException {
                Parent sponsorView = FXMLLoader.load(getClass().getResource("FXMLCalendar.fxml"));
                Scene sponsorScene = new Scene(sponsorView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sponsorScene);
                window.show();
            }


    
}
