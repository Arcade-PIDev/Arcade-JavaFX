package GUI;

import Entities.Evenement;
import Services.ServiceEvenement;
import java.io.InputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FXMLFrontControllerE implements Initializable {
 @FXML
    private FlowPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ServiceEvenement serv = new ServiceEvenement();
        try {
            List<Evenement> evList = serv.afficher();
            
              
            for (Evenement e : evList) {
                FXMLLoader loader ;
                
                Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

                Date endDate =  (Date) e.getDateFinE();
                Date endDateUtil = new Date(endDate.getTime());
                
                if (endDateUtil.before(currentDate) )
                { loader = new FXMLLoader(getClass().getResource("FXMLExpiredEventBox.fxml"));}
                else 
                { loader = new FXMLLoader(getClass().getResource("FXMLEventBox.fxml"));}
   
                Parent root = loader.load();
                EventBoxController cont = loader.getController();

                    cont.setIdEvenement(e.getId()+"");
                    cont.setNomEvent(e.getNomEvent());
                    cont.setDescriptionEvent(e.getDescriptionEvent());
                    cont.setLieu(e.getLieu());
                    cont.setDateDebutE(e.getDateDebutE());
                    cont.setDateFinE(e.getDateFinE());
                    cont.setAfficheE( e.getAfficheE());
                  
                content.getChildren().add(root);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }

 
}