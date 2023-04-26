
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import Services.ServiceEvenement;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author zeine
 */
public class FullCalendarController implements Initializable{

   private Button previousMonth;

    private Button nextMonth;
    @FXML
    private Pane calendarPane;

    private FullCalendarView fullCalendarView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ServiceEvenement serviceEvenement = new ServiceEvenement();
        fullCalendarView = new FullCalendarView(YearMonth.now());
        List<Evenement> listeEvenement = serviceEvenement.afficher();
        ArrayList<String> eventNames = new ArrayList<>();

       fullCalendarView.populateCalendar(YearMonth.now(), listeEvenement);



/*previousMonth = fullCalendarView.getPreviousMonthButton();
nextMonth = fullCalendarView.getNextMonthButton();

// set the action for the "previous" button
previousMonth.setOnAction(event -> {
    YearMonth previousMonth = fullCalendarView.getCurrentMonth().minusMonths(1);
    fullCalendarView.updateCalendar(previousMonth);
    for (Evenement evenement : listeEvenement) {
        fullCalendarView.highlightDays(evenement, previousMonth);
    }
});

// set the action for the "next" button
nextMonth.setOnAction(event -> {
    YearMonth nextMonth = fullCalendarView.getCurrentMonth().plusMonths(1);
    fullCalendarView.updateCalendar(nextMonth);
    for (Evenement evenement : listeEvenement) {
        fullCalendarView.highlightDays(evenement, nextMonth);
    }
});*/

      calendarPane.getChildren().add(fullCalendarView.getView());
    }
}
