/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.CategorieService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class ContentCategorieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        public void setIdCategorie(String IdCategorie) {
        this.idCategorie.setText(IdCategorie);
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie.setText(nomCategorie);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.setText(creationDate);
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled.setText(isEnabled);
    }

    public void setImage(String url) {
        this.imageCategorie.setImage(new Image(url));
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate.setText(modificationDate);
    }

    public void setDescription(String descriptionCategorie) {
        this.descriptionCategorie.setText(descriptionCategorie);
    }
    
    
    
    @FXML
    private void deleteCategorie(MouseEvent event) {
        int id = Integer.parseInt(idCategorie.getText());
        CategorieService serv = new CategorieService();
        try {            
            serv.delete(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller= loader.getController();
            controller.changePage("Categories");
            
            idCategorie.getScene().setRoot(root);
            

        } catch (Exception ex) {
                System.out.println(ex);
        }
       
    }

    @FXML
    private void EditCategorie(MouseEvent event) {
/*
            try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCategory.fxml"));
            Parent root = loader.load();
            EditCategoryController controller= loader.getController();
            Categorie c =new Categorie(Integer.parseInt(idCategorie.getText()),nomCategorie.getText(),descriptionCategorie.getText(),Boolean.parseBoolean(isEnabled.getText()));
            System.out.println(c);
            controller.setCategory(c);
            
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = homeLoader.load();
            Home homeCtrl = homeLoader.getController();
            homeCtrl.changePage( " Category - Edit", root);
            idCategorie.getScene().setRoot(homeRoot);

        } catch (IOException ex) {
                System.out.println(ex);
        }*/
    }
}
