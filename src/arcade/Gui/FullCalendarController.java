
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Evenement;
import arcade.Service.ServiceEvenement;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author zeine
 */
public class FullCalendarController implements Initializable{
    
    @FXML
    private Pane calendarPane;

    private FullCalendarView fullCalendarView;
    private List<Evenement> listeEvenement;

   @Override
        public void initialize(URL location, ResourceBundle resources) {
            ServiceEvenement serviceEvenement = new ServiceEvenement();
            List<Evenement> listeEvenement = serviceEvenement.afficher();

            fullCalendarView = new FullCalendarView(YearMonth.now());
            fullCalendarView.populateCalendar(YearMonth.now(), listeEvenement);
            calendarPane.getChildren().add(fullCalendarView.getView());

            
            Button previousMonth = fullCalendarView.getPreviousMonthButton(listeEvenement);
            Button nextMonth = fullCalendarView.getNextMonthButton(listeEvenement);

            
            HBox buttonsBox = new HBox(previousMonth, nextMonth);
            buttonsBox.setSpacing(1280);
            calendarPane.getChildren().add(buttonsBox);
        }

}
