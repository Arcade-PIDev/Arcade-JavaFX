/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Evenement;
import arcade.Gui.seancecoachingController;
import arcade.Entities.seancecoaching;
import arcade.Utils.database;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.poi.ss.usermodel.* ; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook ; 
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class seancecoachingController implements Initializable {

    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private Button updatebutton;

    @FXML
    private TableColumn<seancecoaching, String> actions;

    @FXML
    private TextField desctf;
    @FXML
    private TextField imagetf;
    @FXML
    private TextField prixtf;
    @FXML
    private TextField titretf;
    @FXML
    private Button btnbrowse;
    @FXML
    private DatePicker datedebtf;
    @FXML
    private DatePicker datefintf;
    @FXML
    private TableView<seancecoaching> tabseance;
    @FXML
    private TableColumn<seancecoaching, String> coltitre;
    @FXML
    private TableColumn<seancecoaching, Date> coldatedeb;
    @FXML
    private TableColumn<seancecoaching, Date> coldatefin;
    @FXML
    private TableColumn<seancecoaching, Double> colprix;
    @FXML
    private TableColumn<seancecoaching, String> coldesc;
    @FXML
    private TableColumn<seancecoaching, String> colimage;
    @FXML
    private ImageView imageview;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button btnfiltre;
    private boolean buttonClicked = false;
    @FXML
    private Button btnexcel;
    @FXML
    private TextField searchField;

    private ObservableList<seancecoaching> seances = FXCollections.observableArrayList();

    @FXML
    private ImageView searchNotFoundImage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showseancecoachings();
        //showRec();
        //searchRec();
        
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        rechercheAdvanced(null);
    });
    }

    @FXML
            private void rechercheAdvanced(KeyEvent event) {
                System.out.println(seances);
                String searchText = searchField.getText();
                System.out.println();
                if (searchText == null || searchText.isEmpty()) {
                    tabseance.setItems(seances);
                    searchNotFoundImage.setVisible(false);
                    return;
                }
                ObservableList<seancecoaching> searchResults = FXCollections.observableArrayList();
                for (seancecoaching seance : seances) {
                    if (seance.getTitre_seance().toLowerCase().contains(searchText.toLowerCase()) || seance.getDescription_seance().toLowerCase().contains(searchText.toLowerCase())) {
                        searchResults.add(seance);
                    }
                }

                if (searchResults.isEmpty()) {
                    searchNotFoundImage.setVisible(true);
                } else {
                    tabseance.setItems(searchResults);
                    searchNotFoundImage.setVisible(false);
                }
            }
            
    public ObservableList<seancecoaching> getseancecoachingList() {
        ObservableList<seancecoaching> compteList = FXCollections.observableArrayList();
        Connection conn = database.getInstance().getCon();
        String query = "SELECT * FROM `seancecoaching`";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            seancecoaching seancecoaching;

            while (rs.next()) {

                seancecoaching = new seancecoaching(rs.getInt("id"), rs.getDate("date_debut_seance"), rs.getDate("date_fin_seance"), rs.getDouble("prix_seance"), rs.getString("description_seance"), rs.getString("image_seance"), rs.getString("titre_seance"));
                compteList.add(seancecoaching);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return compteList;
    }

public ObservableList<seancecoaching> getseancecoachingListfiltre(LocalDate fromDate, LocalDate toDate) {
    ObservableList<seancecoaching> compteList = FXCollections.observableArrayList();
    Connection conn = database.getInstance().getCon();
    String query = "SELECT * FROM `seancecoaching` WHERE (date_debut_seance <= ? AND date_fin_seance >= ?) OR (date_debut_seance >= ? AND date_debut_seance <= ?)";

    PreparedStatement st;
    ResultSet rs;
    try {
        st = conn.prepareStatement(query);
        st.setString(1, String.valueOf(fromDate));
        st.setString(2, String.valueOf(toDate));
        st.setString(3, String.valueOf(fromDate));
        st.setString(4, String.valueOf(toDate));
        rs = st.executeQuery();

        if (!rs.next()) { // no data found
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Seance Found");
            alert.setHeaderText(null);
            alert.setContentText("No data found between selected dates.");
            alert.showAndWait();
            return compteList;
        }

        do {
            seancecoaching seancecoaching = new seancecoaching(rs.getInt("id"), rs.getDate("date_debut_seance"), rs.getDate("date_fin_seance"), rs.getDouble("prix_seance"), rs.getString("description_seance"), rs.getString("image_seance"), rs.getString("titre_seance"));
            compteList.add(seancecoaching);
        } while (rs.next());

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return compteList;
}



    /*/////////////////////////////////////////////////Controle de saisie/////////////////////////////////////////////////////////////////////*/
    private boolean validateFields() {
        LocalDate datedeb = datedebtf.getValue();
        LocalDate datefin = datefintf.getValue();
        LocalDate today = LocalDate.now();
        if (desctf.getText().isEmpty()) {
            showAlert("Error", "Description field is required.");
            return false;
        }
        if (titretf.getText().isEmpty()) {
            showAlert("Error", "Titre field is required.");
            return false;
        }
        if (prixtf.getText().isEmpty()) {
            showAlert("Error", "Prix field is required.");
            return false;
        } else if (!prixtf.getText().matches("\\d+(\\.\\d+)?")) {
            showAlert("Error", "Prix must be a number.");
            return false;
        }
        if (datedeb == null) {
            showAlert("Error", "Please select a start date.");
            return false;
        }

        if (datedeb.isBefore(today)) {
            showAlert("Error", "The start date must be in the future.");
            return false;
        }

        if (datefin == null) {
            showAlert("Error", "Please select an end date.");
            return false;
        }

        if (datefin.isBefore(today)) {
            showAlert("Error", "The end date must be in the future.");
            return false;
        }

        if (datefin.isBefore(datedeb)) {
            showAlert("Error", "The end date must be after the start date.");
            return false;
        }
        return true;
    }

    public void showseancecoachings() {
        String str = "http://127.0.0.1/integration/public/userpic/";
        LocalDate fromDate = startDate.getValue();
        LocalDate toDate = endDate.getValue();
        ObservableList<seancecoaching> list;
        if (buttonClicked && fromDate != null && toDate != null) {
            list = getseancecoachingListfiltre(fromDate, toDate);
            coltitre.setCellValueFactory(new PropertyValueFactory<>("titre_seance"));
            coldatedeb.setCellValueFactory(new PropertyValueFactory<>("date_debut_seance"));
            coldatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin_seance"));
            colprix.setCellValueFactory(new PropertyValueFactory<>("prix_seance"));
            coldesc.setCellValueFactory(new PropertyValueFactory<>("description_seance"));
            colimage.setCellValueFactory(new PropertyValueFactory<>("image_seance"));
            tabseance.setItems(list);
        } else {
            list = getseancecoachingList();
            coltitre.setCellValueFactory(new PropertyValueFactory<>("titre_seance"));
            coldatedeb.setCellValueFactory(new PropertyValueFactory<>("date_debut_seance"));
            coldatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin_seance"));
            colprix.setCellValueFactory(new PropertyValueFactory<>("prix_seance"));
            coldesc.setCellValueFactory(new PropertyValueFactory<>("description_seance"));
            colimage.setCellValueFactory(new PropertyValueFactory<>("image_seance"));
            tabseance.setItems(list);
            seances.addAll(list);
        }

        Callback<TableColumn<seancecoaching, String>, TableCell<seancecoaching, String>> cellFoctory = (TableColumn<seancecoaching, String> param) -> {
            // make cell containing buttons
            final TableCell<seancecoaching, String> cell = new TableCell<seancecoaching, String>() {
                @Override
                public void updateItem(String item, boolean empty) {

                    seancecoaching seancecoaching = null;
                    super.updateItem(str+item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Alert!");
                                alert.setContentText("This is an alert");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {

                                    try {
                                        PreparedStatement ps = null;
                                        seancecoaching seancecoachings;
                                        seancecoachings = tabseance.getSelectionModel().getSelectedItem();
                                        String query = "DELETE FROM `seancecoaching` WHERE id =" + seancecoachings.getId();
                                        Connection conn = database.getInstance().getCon();
                                        ps = conn.prepareStatement(query);
                                        ps.execute();
                                        showseancecoachings();

                                    } catch (SQLException ex) {
                                        Logger.getLogger(seancecoachingController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else if (result.get() == ButtonType.CANCEL) {
                                    showseancecoachings();
                                }
                            }
                        });

                        editIcon.setOnMouseClicked((new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {

                                seancecoaching seancecoachings = tabseance.getSelectionModel().getSelectedItem();

                                desctf.setText(String.valueOf(seancecoachings.getDescription_seance()));
                                titretf.setText(String.valueOf(seancecoachings.getTitre_seance()));
                                prixtf.setText(String.valueOf(seancecoachings.getPrix_seance()));
                                datedebtf.setValue(seancecoachings.getDate_debut_seance().toLocalDate());
                                datefintf.setValue(seancecoachings.getDate_fin_seance().toLocalDate());

                            }

                        }));
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;
        };
        actions.setCellFactory(cellFoctory);
        tabseance.setItems(list);

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onupdate(ActionEvent event) {
        if (event.getSource() == updatebutton) {
            if (validateFields()) {
                String desc = desctf.getText();
                String image = imagetf.getText();
                Double prix = Double.valueOf(prixtf.getText());
                String titre = titretf.getText();
                String datedeb = String.valueOf(datedebtf.getValue());
                String datefin = String.valueOf(datefintf.getValue());

                seancecoaching seancecoaching = tabseance.getSelectionModel().getSelectedItem();
                String query = "UPDATE `seancecoaching` SET date_debut_seance = ?, date_fin_seance = ?, prix_seance = ?, description_seance = ?, image_seance = ?, titre_seance = ? WHERE id = ?";
                try {
                    PreparedStatement pstmt = database.getInstance().getCon().prepareStatement(query);
                    pstmt.setString(1, datedeb);
                    pstmt.setString(2, datefin);
                    pstmt.setDouble(3, prix);
                    pstmt.setString(4, desc);
                    pstmt.setString(5, image);
                    pstmt.setString(6, titre);
                    pstmt.setInt(7, seancecoaching.getId());
                    pstmt.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Seance Coaching ");
                    alert.setHeaderText(null);
                    alert.setContentText("updated successfully");
                    alert.showAndWait();
                    showseancecoachings();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void executeQuery(String query) {
        Connection conn = database.getInstance().getCon();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    @FXML
    private void actionperformed(ActionEvent event) throws FileNotFoundException {
        FileChooser filechooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

        //filechooser.setInitialDirectory(new File("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Notex\\src\\com\\gn\\module\\evenements\\image"));
        filechooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = filechooser.showOpenDialog(new Stage());
        if (file != null) {
            Image image = new Image(new FileInputStream(file));
            imagetf.setText(file.getName());
            // set fit height and width of ImageView to the image size
            imageview.setImage(image);
            // set the image view in your UI, e.g.

        }
    }

    @FXML
    private void filtre(ActionEvent event) {
        buttonClicked = true;
        showseancecoachings();
    }

    @FXML
    private void excel(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Export Seancecoaching");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"));
    File file = fileChooser.showSaveDialog(tabseance.getScene().getWindow());
    if (file != null) {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream outputStream = new FileOutputStream(file)) {
            Sheet sheet = workbook.createSheet("Seancecoaching");
            // Add header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Titre");
            headerRow.createCell(1).setCellValue("Date début");
            headerRow.createCell(2).setCellValue("Date fin");
            headerRow.createCell(3).setCellValue("Prix");
            headerRow.createCell(4).setCellValue("Description");
            headerRow.createCell(5).setCellValue("Image");

            // Add data rows
            ObservableList<seancecoaching> list = tabseance.getItems();
            for (int i = 0; i < list.size(); i++) {
                seancecoaching seance = list.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(seance.getTitre_seance());
                row.createCell(1).setCellValue(seance.getDate_debut_seance().toString());
                row.createCell(2).setCellValue(seance.getDate_fin_seance().toString());
                row.createCell(3).setCellValue(seance.getPrix_seance());
                row.createCell(4).setCellValue(seance.getDescription_seance());
                row.createCell(5).setCellValue(seance.getImage_seance());
            }

            workbook.write(outputStream);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("Seancecoaching exported successfully.");
            alert.showAndWait();
            // Open the file
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while exporting the file.");
            alert.showAndWait();
            ex.printStackTrace();
        }
    }
    }

    @FXML
    private void search(ActionEvent event) {
        
   
    seancecoachingController Sc = new seancecoachingController();
    String searchText = searchField.getText();
    ObservableList<seancecoaching> allArticles = getseancecoachingList();

    // Filter the articles based on the search text
    ObservableList<seancecoaching> filteredList = allArticles.filtered(seancecoaching -> {
       // return article.getTitre().toLowerCase().contains(searchText.toLowerCase()) ||
               // article.getDescription().toLowerCase().contains(searchText.toLowerCase()) ||
            return seancecoaching.getTitre_seance().toLowerCase().contains(searchText.toLowerCase());
    });

    // Show the filtered articles in the table
    tabseance.setItems(filteredList);

   
    }

}
