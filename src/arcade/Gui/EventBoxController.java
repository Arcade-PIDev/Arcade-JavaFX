/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;


import arcade.Entities.Sponsor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;

/**
 *
 * @author zeine
 */
public class EventBoxController implements Initializable {
    @FXML
    private ImageView affiche;
    @FXML
    private Label nomE;
    @FXML
    private Label descriptionE;
    @FXML
    private Label lieu;
    @FXML
    private Label dateD;
    @FXML
    private Label dateF;
    @FXML
    private Pane evenementBox;
    @FXML
    private Label EventId;
    @FXML
    private Button goToSponsorBtn;

    List<Sponsor> sponsors;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setNomEvent(String NomEvent) {
        this.nomE.setText(NomEvent);
    }
     
  public void setAfficheE(String imagePath) {
    try {
        File file = new File(imagePath);
        URI uri = file.toURI();
        URL url = uri.toURL(); 
        this.affiche.setImage(new Image(url.toString(), 190, 167, false, false));
    } catch (Exception e) {
        System.out.println("Image not found: " + imagePath);
    }
}



    public void setDescriptionEvent(String DescriptionEvent) {
        this.descriptionE.setText(DescriptionEvent);
    }

    public void setIdEvenement(String id) {
        this.EventId.setText(id);
    }
    
     public void setLieu(String lieu) {
        this.lieu.setText(lieu);
    }
     
     
       public void setDateDebutE(Date DateDebutE) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(DateDebutE);
        dateD.setText(formattedDate);
    }
      
      
       public void setDateFinE(Date DateFinE) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(DateFinE);
        dateF.setText(formattedDate);
    }
    
   
    
   
}
