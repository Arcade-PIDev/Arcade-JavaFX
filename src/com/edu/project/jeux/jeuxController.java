/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.jeux;

import DBCnx.MyConnection;
import static DBCnx.MyConnection.MyConnection;
import com.edu.project.entities.jeux;
import com.edu.project.entities.jeux;
import com.edu.project.entities.jeux;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class jeuxController implements Initializable {

    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private Button updatebutton;

    @FXML
    private TableColumn<jeux, String> actions;

    @FXML
    private TextField desctf;
    @FXML
    private TextField imagetf;

    @FXML
    private Button btnbrowse;

    @FXML
    private TableColumn<jeux, String> coldesc;
    @FXML
    private TableColumn<jeux, String> colimage;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField genretf;
    @FXML
    private TextField nomtf;
    @FXML
    private ColorPicker colortf;
    @FXML
    private TableView<jeux> tabjeux;
    @FXML
    private TableColumn<jeux, String> colnom;
    @FXML
    private TableColumn<jeux, String> colgenre;
    @FXML
    private TableColumn<jeux, String> colcolor;
    @FXML
    private Button btnstat;
    @FXML
    private Button btnpdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showjeuxs();
        //showRec();
        //searchRec();
    }

    public ObservableList<jeux> getjeuxList() {
        ObservableList<jeux> compteList = FXCollections.observableArrayList();
        Connection conn = MyConnection();
        String query = "SELECT * FROM jeux";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            jeux jeux;

            while (rs.next()) {

                jeux = new jeux(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getString("image"), rs.getString("genre"), rs.getString("color"));
                compteList.add(jeux);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return compteList;
    }

    /*/////////////////////////////////////////////////Controle de saisie/////////////////////////////////////////////////////////////////////*/
    private boolean validateFields() {

        LocalDate today = LocalDate.now();
        if (desctf.getText().isEmpty()) {
            showAlert("Error", "Le champ est vide.");
            return false;
        }
        if (genretf.getText().isEmpty()) {
            showAlert("Error", "Le champ est vide.");
            return false;
        }
        if (nomtf.getText().isEmpty()) {
            showAlert("Error", "Le champ est vide.");
            return false;
        }

        return true;
    }

    public void showjeuxs() {
        ObservableList<jeux> list = getjeuxList();
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colcolor.setCellValueFactory(new PropertyValueFactory<>("color"));

        tabjeux.setItems(list);
        Callback<TableColumn<jeux, String>, TableCell<jeux, String>> cellFoctory = (TableColumn<jeux, String> param) -> {
            // make cell containing buttons
            final TableCell<jeux, String> cell = new TableCell<jeux, String>() {
                @Override
                public void updateItem(String item, boolean empty) {

                    jeux jeux = null;
                    super.updateItem(item, empty);
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
                                alert.setContentText("Voulez-vous supprimer ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {

                                    try {
                                        PreparedStatement ps = null;
                                        jeux jeuxs;
                                        jeuxs = tabjeux.getSelectionModel().getSelectedItem();
                                        String query = "DELETE FROM `jeux` WHERE id =" + jeuxs.getId();
                                        Connection conn = MyConnection();
                                        ps = conn.prepareStatement(query);
                                        ps.execute();
                                        showjeuxs();

                                    } catch (SQLException ex) {
                                        Logger.getLogger(jeuxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else if (result.get() == ButtonType.CANCEL) {
                                    showjeuxs();
                                }
                            }
                        });

                        editIcon.setOnMouseClicked((new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {

                                jeux jeuxs = tabjeux.getSelectionModel().getSelectedItem();

                                desctf.setText(String.valueOf(jeuxs.getDescription()));
                                nomtf.setText(String.valueOf(jeuxs.getNom()));
                                genretf.setText(String.valueOf(jeuxs.getGenre()));
                                Color color = Color.web(jeuxs.getColor());
                                colortf.setValue(color);
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
        tabjeux.setItems(list);

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
                String nom = nomtf.getText();
                String genre = genretf.getText();
                Color color = colortf.getValue();
                String colorString = color.toString();

                jeux jeux = tabjeux.getSelectionModel().getSelectedItem();
                String query = "UPDATE jeux SET nom = ?, description = ?, image = ?, genre = ?, color = ? WHERE id = ?";
                try {
                    PreparedStatement pstmt = MyConnection().prepareStatement(query);
                    pstmt.setString(1, nom);
                    pstmt.setString(2, desc);
                    pstmt.setString(3, image);
                    pstmt.setString(4, genre);
                    pstmt.setString(5, colorString);

                    pstmt.setInt(6, jeux.getId());
                    pstmt.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Jeux ");
                    alert.setHeaderText(null);
                    alert.setContentText("Modifier");
                    alert.showAndWait();
                    showjeuxs();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void executeQuery(String query) {
        Connection conn = MyConnection();
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

    public ObservableList<PieChart.Data> getPieChartData() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Connection conn = MyConnection();
        String query = "SELECT genre, COUNT(*) AS count FROM jeux GROUP BY genre";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String genre = rs.getString("genre");
                int count = rs.getInt("count");
                pieChartData.add(new PieChart.Data(genre, count));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pieChartData;
    }

    @FXML
    private void stat(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData = getPieChartData();
        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Jeux par genre");
        Scene scene = new Scene(new Group(chart), 500, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
private void pdf(ActionEvent event) throws DocumentException {
    Document document = new Document();
    try {
        // Create a temporary file with a unique name to store the PDF
        File tempFile = File.createTempFile("liste Jeux", ".pdf");

        // Set the file to be deleted on exit
        tempFile.deleteOnExit();

        // Write the PDF to the temporary file
        PdfWriter.getInstance(document, new FileOutputStream(tempFile));
        document.open();

        // Add a title to the PDF
        Paragraph title = new Paragraph("Liste Jeux");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable pdfTable = new PdfPTable(4);
        addTableHeader(pdfTable);
        addRows(pdfTable, tabjeux.getItems());
        document.add(pdfTable);

        document.close();

        // Create a new FileChooser to allow the user to choose where to save the file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("Liste Jeux.pdf");

        // Set the initial directory for the FileChooser to the user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show the FileChooser and wait for the user to select a file
        File file = fileChooser.showSaveDialog(tabjeux.getScene().getWindow());

        // If the user selected a file, copy the contents of the temporary file to the selected file
        if (file != null) {
            Files.copy(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    } catch (IOException | DocumentException e) {
        e.printStackTrace();
    }
}

private void addTableHeader(PdfPTable pdfTable) {
    pdfTable.addCell("ID");
    pdfTable.addCell("Nom");
    pdfTable.addCell("Description");
    pdfTable.addCell("Genre");
    
}

private void addRows(PdfPTable pdfTable, ObservableList<jeux> jeuxList) {
    for (jeux jeu : jeuxList) {
        pdfTable.addCell(String.valueOf(jeu.getId()));
        pdfTable.addCell(jeu.getNom());
        pdfTable.addCell(jeu.getDescription());
        pdfTable.addCell(jeu.getGenre());

        // Add the image to the table
        if (jeu.getImage() != null && !jeu.getImage().isEmpty()) {
            try {
                String imagePath = "C:\\Users\\pc\\Pictures\\Screenshots\\" + jeu.getImage();
                com.itextpdf.text.Image pdfImg = com.itextpdf.text.Image.getInstance(imagePath);

                // Set the size of the image to 100 x 100 pixels
                pdfImg.scaleAbsolute(100, 100);

                PdfPCell cell = new PdfPCell(pdfImg, true);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pdfTable.addCell(cell);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pdfTable.addCell("");
        }
    }
}


}
