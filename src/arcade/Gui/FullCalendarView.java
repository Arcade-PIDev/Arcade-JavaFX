package arcade.Gui;

import arcade.Entities.Evenement;
import java.awt.Color;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * 
 */
public class FullCalendarView {
    private final VBox view;
    private final Text calendarTitle;
    private ArrayList<AnchorPane> allCalendarDays = new ArrayList<>(35);
    private YearMonth currentYearMonth;
    private List<Evenement> listeEvenement;
    

        public FullCalendarView(YearMonth yearMonth) {
            currentYearMonth = yearMonth;
            // Create the calendar grid pane
            GridPane calendar = new GridPane();
            calendar.setPrefSize(1680, 900);
            calendar.setGridLinesVisible(true);
            // Create rows and columns with anchor panes for the calendar
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 7; j++) {
                    AnchorPane ap = new AnchorPane();
                    ap.setPrefSize(300, 165);
                    ap.setStyle("-fx-background-color: #2d292d; ");

                    calendar.add(ap, j, i);
                    allCalendarDays.add(ap);
                }
            }
            // Days of the week labels
            Text[] dayNames = new Text[]{new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
                    new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                    new Text("Saturday")};
            GridPane dayLabels = new GridPane();
            dayLabels.setPrefWidth(700);
            int col = 0;
            for (Text txt : dayNames) {
                AnchorPane ap = new AnchorPane();
                ap.setPrefSize(300, 40);
                ap.setStyle("-fx-background-color: #180d1b");
                txt.setFill(Paint.valueOf("#FFFFFF"));
                txt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                AnchorPane.setBottomAnchor(txt, 5.0);
                ap.getChildren().add(txt);
                dayLabels.add(ap, col++, 0);
            }
            // Create calendarTitle and buttons to change current month

            calendarTitle = new Text();
            calendarTitle.setFill(Paint.valueOf("WHITE"));
            calendarTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
           
            HBox titleBar = new HBox(10, calendarTitle);
            titleBar.setStyle("-fx-background-color: #181b26");
            titleBar.setAlignment(Pos.BASELINE_CENTER);
            // Populate calendar with the appropriate day numbers
            populateCalendar(yearMonth, listeEvenement);
            // Create the calendar view
            
            view = new VBox(titleBar, dayLabels, calendar);
           
        }


   
        public void populateCalendar(YearMonth yearMonth, List<Evenement> events) {
            
          currentYearMonth = yearMonth;
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
          calendarTitle.setText(yearMonth.format(formatter));
          LocalDate firstOfMonth = yearMonth.atDay(1);
          int daysInMonth = yearMonth.lengthOfMonth();
          WeekFields weekFields = WeekFields.of(Locale.getDefault());
          int weekNumber = firstOfMonth.get(weekFields.weekOfWeekBasedYear());
          // Populate calendar with day numbers and events
          for (AnchorPane ap : allCalendarDays) {
              ap.getChildren().clear();
              int row = allCalendarDays.indexOf(ap) / 7;
              int col = allCalendarDays.indexOf(ap) % 7;
              int dayNumber = row * 7 + col + 1 - firstOfMonth.getDayOfWeek().getValue();
              if (dayNumber > 0 && dayNumber <= daysInMonth) {
                VBox vbox = new VBox();
                Label label = new Label(Integer.toString(dayNumber));
                label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 16px; -fx-font-weight: bold;");
                vbox.getChildren().addAll(label);
                ap.getChildren().add(vbox);
                AnchorPane.setTopAnchor(vbox, 5.0);
                AnchorPane.setLeftAnchor(vbox, 5.0);
                AnchorPane.setRightAnchor(vbox, 5.0);

            // Check if there are any events on this day
            if (events != null && !events.isEmpty()) {
                for (Evenement event : events) {
                    Date startDate = event.getDateDebutE();
                    Date endDate = event.getDateFinE();
                    String eventName = event.getNomEvent();

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startDate);
                    

            // Highlight the days in the range of the event in the specified month
            while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
                if (calendar.get(Calendar.MONTH) == yearMonth.getMonthValue() - 1) {
                    int eventDayNumber = calendar.get(Calendar.DAY_OF_MONTH);
                    if (eventDayNumber == dayNumber) {
                        
                        VBox eventBox = new VBox();
                        eventBox.setStyle("-fx-background-color:  #781e77 ; -fx-padding: 15px;");
                        Label eventNameLabel = new Label(eventName);
                        eventNameLabel.setStyle("-fx-font-size: 20px;-fx-text-fill: white; -fx-font-weight: bold;");
                        eventBox.getChildren().add(eventNameLabel);

                        ap.getChildren().add(eventBox);
                        AnchorPane.setTopAnchor(eventBox, 5.0);
                        AnchorPane.setLeftAnchor(eventBox, 5.0);
                        AnchorPane.setRightAnchor(eventBox, 5.0);
                    }
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

        }
                
    }
                  // Highlight the day if it is today's date
                  if (LocalDate.now().equals(yearMonth.atDay(dayNumber))) {
                      ap.setStyle("-fx-background-color:  #413f63; -fx-border-color: #ff6b00;");
                  } else {
                      ap.setStyle("-fx-background-color: #180d1b ;-fx-border-color: #ff6b00; -fx-border-width: 0.2px;");
                  }
              } else {
                  ap.setStyle("-fx-background-color: #2d292d;");
              }
          }
           
      }
 
            public void previousMonth() {
              System.out.println("previousMonth() called");
              currentYearMonth = currentYearMonth.minusMonths(1);
              populateCalendar(currentYearMonth, listeEvenement);
          }

          public void nextMonth() {
              System.out.println("nextMonth() called");
              currentYearMonth = currentYearMonth.plusMonths(1);
              populateCalendar(currentYearMonth, listeEvenement);
          }


        public VBox getView() {
            return view;
        }
        
        
        public YearMonth getCurrentYearMonth() {
            return currentYearMonth;
        }

            public Button getPreviousMonthButton(List<Evenement> listeEvenement) {
             Button previousMonth = new Button("<<");
             previousMonth.setStyle("-fx-font-size: 20px;-fx-background-color: linear-gradient(to bottom, #4b0082, #ee5622); -fx-text-fill: WHITE;");
                 previousMonth.setPrefSize(200, 30);
                 
             previousMonth.setOnAction(e -> {
                 previousMonth();
                 populateCalendar(getCurrentYearMonth(), listeEvenement);
             });
             return previousMonth;
         }
   
            public Button getNextMonthButton(List<Evenement> listeEvenement) {
                Button nextMonth = new Button(">>");
                nextMonth.setStyle("-fx-font-size: 20px;-fx-background-color: linear-gradient(to bottom, #4b0082, #ee5622); -fx-text-fill: WHITE");
                    nextMonth.setPrefSize(200, 30);
                    
                nextMonth.setOnAction(e -> {
                    nextMonth();
                    populateCalendar(getCurrentYearMonth(), listeEvenement);
                });
                return nextMonth;
            }

}




