/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Services.CategorieService;
import java.util.List;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CategorieController implements Initializable {
    @FXML
    private FlowPane content;
    Categorie cat = new Categorie();
    
    
    @FXML
    private Pane contentCategorie;
    
    @FXML
    private HBox itemC;
    @FXML
    private Label idCategorie;
    @FXML
    private Label nomCategorie;
    @FXML
    private Label descriptionCategorie;
    @FXML
    private ImageView imageCategorie;
    @FXML
    private Label creationDate;
    @FXML
    private Label modificationDate;
    @FXML
    private Label isEnabled;
    @FXML
    private ImageView EditCategorie;
    @FXML
    private ImageView deleteCategorie;
    @FXML
    private Button addCategorie;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*
        CategorieService serv = new CategorieService();
                
            List<Categorie> catList = serv.afficher();*/
            /*
        catList.forEach(cat ->{
            //cat = catList.get(0);
            this.nomCategorie.setText(cat.getNomCategorie());
                    this.descriptionCategorie.setText(cat.getDescription());
                    this.imageCategorie.setImage(new Image("http://127.0.0.1:8000/eshop/categorie/"+cat.getImage(),60,60,true,true));
                    this.creationDate.setText(cat.getCreationDate()+"");
                    this.modificationDate.setText(cat.getModificationDate()+"");
                    this.isEnabled.setText(cat.isIsEnabled()+"");
                    System.out.println(cat);
                    
        });*/
            
            /*
            for (int i = 0; i < catList.size(); i++) {
                Categorie cat = catList.get(i);
                this.nomCategorie.setText(cat.getNomCategorie());
                this.descriptionCategorie.setText(cat.getDescription());
                this.imageCategorie.setImage(new Image("http://127.0.0.1:8000/eshop/categorie/" + cat.getImage(), 60, 60, true, true));
                this.creationDate.setText(cat.getCreationDate() + "");
                this.modificationDate.setText(cat.getModificationDate() + "");
                this.isEnabled.setText(cat.isIsEnabled() + "");
                System.out.println(cat);
            }*/
            
            /*
            int i=0;
        for (Categorie cat : catList) {
            cat = catList.get(i);
            this.idCategorie.setText(cat.getId()+"");
            this.nomCategorie.setText(cat.getNomCategorie());
            this.descriptionCategorie.setText(cat.getDescription());
            this.imageCategorie.setImage(new Image("http://127.0.0.1:8000/eshop/categorie/" + cat.getImage()));
            this.creationDate.setText(cat.getCreationDate() + "");
            this.modificationDate.setText(cat.getModificationDate() + "");
            this.isEnabled.setText(cat.isIsEnabled() + "");
            i++;
        }*/
        
        
        CategorieService serv = new CategorieService();
        try {
            List<Categorie> catList = serv.afficher();

            for (Categorie c : catList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("contentCategorie.fxml"));
                Parent root = loader.load();
                ContentCategorieController cont = loader.getController();

                    cont.setIdCategorie(c.getId()+"");
                    cont.setNomCategorie(c.getNomCategorie());
                    cont.setDescription(c.getDescription());
                    cont.setImage("http://127.0.0.1/:8000/eshop/categorie/"+c.getImage());
                    
                    cont.setCreationDate(c.getCreationDate()+"");
                    cont.setModificationDate(c.getModificationDate()+"");
                    cont.setIsEnabled(c.isIsEnabled()+"");
                    
                content.getChildren().add(root);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        
        /*
        try {
            for (Categorie cat : catList) {
                 if(!catList.isEmpty()){
                    cat = catList.get(0);
                    this.nomCategorie.setText(cat.getNomCategorie());
                    this.descriptionCategorie.setText(cat.getDescription());
                    this.imageCategorie.setImage(new Image("http://127.0.0.1:8000/eshop/categorie/"+cat.getImage(),60,60,true,true));
                    this.creationDate.setText(cat.getCreationDate()+"");
                    this.modificationDate.setText(cat.getModificationDate()+"");
                    this.isEnabled.setText(cat.isIsEnabled()+"");
                 }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }*/
    }    
    
    @FXML
    private void addCategorie(MouseEvent event) throws Exception{
        try {
            FXMLLoader loader = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            HomeController controller= loader.getController();
            controller.changePage("ajouterCategorie");
            //contentCategorie.getScene().setRoot(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        
    }

}
