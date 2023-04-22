/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.Categorie;
import arcade.Entities.Produit;
import arcade.Service.CategorieService;
import arcade.Service.ProduitService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * FXML Controller class
 *
 * @author Amira
 */
public class addProduitController implements Initializable{
    @FXML
    private TextField nomProduit;
    @FXML
    private Label nomProduitError;
    @FXML
    private TextField description;
    @FXML
    private Label descriptionError;
    @FXML
    private TextField image;
    @FXML
    private Button upload;
    @FXML
    private Label imageError;
    @FXML
    private ComboBox<String> categories;
    @FXML
    private Label categoriesError;
    @FXML
    private TextField prix;
    @FXML
    private Label prixError;
    @FXML
    private TextField quantite;
    @FXML
    private Label quantiteError;
    @FXML
    private Button cancel;
    @FXML
    private Button addProduct;

    Produit p = new Produit();
    final FileChooser fileChooser = new FileChooser();
    
    public void setCombo() {
        //this.p = prod;
        //categories.setValue(p.getCategorie() + "");
        try {
            CategorieService serv = new CategorieService();
            List<Categorie> list = serv.afficher();
            ObservableList<String> listId = FXCollections.observableArrayList();
            for (Categorie cat : list) {
                listId.add(cat.getId() + ":  " + cat.getNomCategorie());
                System.out.println(cat);
            }

            categories.setItems(listId);
        } catch (SQLException ex) {
            Logger.getLogger(addProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

 

    @FXML
    private void cancel(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();
                    HomeController controller = loader.getController();
                    controller.changePage("Produits");

                    addProduct.getScene().setRoot(root);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
    }

    @FXML
    private void addProduct(ActionEvent event) {
        nomProduitError.setText("");
        descriptionError.setText("");
        imageError.setText("");
        categoriesError.setText("");
        prixError.setText("");
        quantiteError.setText("");
        
        if (nomProduit.getText().length() != 0 && description.getText().length() != 0  && categories.getSelectionModel().isEmpty() == false && image.getText().length() != 0) {
            if (Pattern.matches("^[a-zA-Z]*$", nomProduit.getText()) == true && Pattern.matches("^[a-zA-Z]*$", description.getText()) == true && Pattern.matches("^[0-9]*$", prix.getText()) == true && Pattern.matches("^[0-9]*$", quantite.getText()) == true && Integer.parseInt(quantite.getText()) > -1 && Integer.parseInt(prix.getText()) > -1) {
            
                p.setNomProduit(nomProduit.getText());
                p.setDescription(description.getText());

                p.setPrix(Integer.parseInt(prix.getText()));
                p.setQuantiteStock(Integer.parseInt(quantite.getText()));
                String[] categoryIdCombo = categories.getValue().split(":", 2);
                p.setCategorie(Integer.parseInt(categoryIdCombo[0]));

                ProduitService ps = new ProduitService();
                try {
                    ps.ajouter(p);

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                        Parent root = loader.load();
                        HomeController controller = loader.getController();
                        controller.changePage("Produits");

                        addProduct.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(addProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (Pattern.matches("^[a-zA-Z]*$", nomProduit.getText()) == false) {
                    nomProduitError.setText("Seulement des lettres");
                }
                if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                    descriptionError.setText("Seulement des lettres");
                }
                if (Pattern.matches("^[0-9\\.\\-\\/]*$", prix.getText()) == false) {
                    prixError.setText("Seulement des chiffres");
                }
                if (Pattern.matches("^[0-9]*$", quantite.getText()) == false) {
                    quantiteError.setText("Seulement des chiffres");
                }
                if (Integer.parseInt(quantite.getText()) < 0) {
                    quantiteError.setText("doit etre positive");
                }
                if (Integer.parseInt(prix.getText()) < 0) {
                    prixError.setText("doit etre positive");
                }
            }

        } else {
            if (nomProduit.getText().length() == 0) {
                nomProduitError.setText("Champs Obligatoire");
            }

            if (description.getText().length() == 0) {
                descriptionError.setText("Champs Obligatoire");
            }

            if (image.getText().length() == 0) {
                imageError.setText("Champs Obligatoire");
            }
            if (categories.getSelectionModel().isEmpty()) {
                categoriesError.setText("Choisir categorie");
            }
            if (prix.getText().length() == 0) {
                prixError.setText("Champs Obligatoire");
            }

            if (quantite.getText().length() == 0) {
                quantiteError.setText("Champs Obligatoire");
            }
            if (Pattern.matches("^[a-zA-Z]*$", nomProduit.getText()) == false) {
                nomProduitError.setText("Seulement des lettres");
            }
            if (Pattern.matches("^[a-zA-Z]*$", description.getText()) == false) {
                descriptionError.setText("Seulement des lettres");
            }
            if (Pattern.matches("^[0-9\\.\\-\\/]*$", prix.getText()) == false) {
                prixError.setText("Seulement des chiffres");
            }
            if (Pattern.matches("^[0-9]*$", quantite.getText()) == false) {
                quantiteError.setText("Seulement des chiffres");
            }
            if (Integer.parseInt(quantite.getText()) < 0) {
                quantiteError.setText("doit etre positive");
            }
            if (Integer.parseInt(prix.getText()) < 0) {
                prixError.setText("doit etre positive");
            }

        }
        }
        //upload image
    private Desktop desktop = Desktop.getDesktop();
    Stage primaryStage;

    @FXML
    private void upload(ActionEvent event) {

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        fileChooser.setTitle("Save");
        File file = fileChooser.showOpenDialog(primaryStage);
        image.clear();

        if (file != null) {
            // generate a fileName
            String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk" + "lmnopqrstuvwxyz!@#$%&";
            Random rnd = new Random();
            StringBuilder sb = new StringBuilder(10);
            for (int i = 0; i < 10; i++) {
                sb.append(chars.charAt(rnd.nextInt(chars.length())));
            }
            String fileName = sb.toString();

            File source = file;
            File dest = new File("C:\\xampp\\htdocs\\pi\\public\\eshop\\produit\\" + fileName + ".png");

            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(addProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<File> files = Arrays.asList(file);
            printLog(image, files, fileName);
            p.setImage(fileName + ".png");
        }
    }

    private void printLog(TextField textArea, List<File> files, String fileName) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(fileName + ".png");
        }
    }

}
