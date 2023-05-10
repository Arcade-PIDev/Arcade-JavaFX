/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import arcade.Entities.Jeux;
import arcade.Service.JeuxService;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.sun.scenario.effect.ImageData;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class JeuxController implements Initializable {
    public Button downloadBtn;
    @FXML
    private FlowPane content;
    Jeux cat = new Jeux();
    
    @FXML
    private Pane contentJeux;
    @FXML
    private ImageView EditJeux;
    @FXML
    private ImageView deleteJeux;
    @FXML
    private Button addBtn;
Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JeuxService serv = new JeuxService();
        try {
            List<Jeux> catList = serv.afficher();

            for (Jeux c : catList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("contentJeux.fxml"));
                Parent root = loader.load();
                ContentJeuxController cont = loader.getController();

                     cont.setId(c.getId()+"");

                    cont.setNom(c.getNom());
                    cont.setDescription(c.getDescription());
                    cont.setImage("http://127.0.0.1/integration/public/Jeux/"+c.getImage());
                    
                    cont.setgenre(c.getGenre()+"");
                    cont.setcolor(c.getColor()+"");
                   
                    
                content.getChildren().add(root);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void handleDownload() {
        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream("JeuxTable.pdf"));
            //document.addTitle("Tableau des jeux");
            document.open();
        
            Paragraph title = new Paragraph("Liste des jeux");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
                document.add(new Paragraph(" "));
               

            // Create table
            PdfPTable table = new PdfPTable(4);

            // Add headers
            table.addCell(new PdfPCell(new Phrase("ID")));
            table.addCell(new PdfPCell(new Phrase("Nom du Jeux")));
            table.addCell(new PdfPCell(new Phrase("Description")));
            table.addCell(new PdfPCell(new Phrase("Genre")));

            // Add data rows
            JeuxService serv = new JeuxService();
            List<Jeux> jeuxList = serv.afficher();
            for (Jeux jeu : jeuxList) {
                table.addCell(new PdfPCell(new Phrase(String.valueOf(jeu.getId()))));
                table.addCell(new PdfPCell(new Phrase(jeu.getNom())));
                table.addCell(new PdfPCell(new Phrase(jeu.getDescription())));
                table.addCell(new PdfPCell(new Phrase(jeu.getGenre())));
            }

            // Add table to document
            document.add(table);
            document.close();
        } catch (DocumentException | FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void stat() {
                try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticsView.fxml"));
                
                Parent root = loader.load();
                StatisticsController cont = loader.getController();

                content.getChildren().add(root);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
