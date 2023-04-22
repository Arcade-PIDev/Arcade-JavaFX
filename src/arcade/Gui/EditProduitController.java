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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Amira
 */
public class EditProduitController implements Initializable {

    @FXML
    private TextField nomProduit;
    @FXML
    private Label nomProduitError;
    @FXML
    private TextField description;
    @FXML
    private Label descriptionError;
    @FXML
    private Button cancel;
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
    private Button editProduit;

    private Produit p;
    
    
    public void setProduct(Produit c) {
        this.p = c;

        nomProduit.setText(p.getNomProduit());
        description.setText(p.getDescription());
        quantite.setText(p.getQuantiteStock() + "");
        prix.setText(p.getPrix() + "");
        categories.setValue(p.getCategorie() + "");

        //set combobox
        try {
            CategorieService serv = new CategorieService();
            List<Categorie> list = serv.afficher();
            ObservableList<String> listId = FXCollections.observableArrayList();
            for (Categorie cat : list) {
                listId.add(cat.getId() + ":  " + cat.getNomCategorie());
            }

            categories.setItems(listId);

        } catch (SQLException ex) {
            Logger.getLogger(addProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Initializes the controller class.
     */
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

            editProduit.getScene().setRoot(root);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void editProduit(ActionEvent event) {
        nomProduitError.setText("");
        descriptionError.setText("");
        categoriesError.setText("");
        prixError.setText("");
        quantiteError.setText("");
        
        if (nomProduit.getText().length() != 0 && description.getText().length() != 0  && categories.getSelectionModel().isEmpty() == false) {
            if (Pattern.matches("^[a-zA-Z]*$", nomProduit.getText()) == true && Pattern.matches("^[a-zA-Z]*$", description.getText()) == true && Pattern.matches("^[0-9]*$", prix.getText()) == true && Pattern.matches("^[0-9]*$", quantite.getText()) == true && Integer.parseInt(quantite.getText()) > -1 && Integer.parseInt(prix.getText()) > -1) {
            
                p.setNomProduit(nomProduit.getText());
                p.setDescription(description.getText());

                p.setPrix(Integer.parseInt(prix.getText()));
                p.setQuantiteStock(Integer.parseInt(quantite.getText()));
                String[] categoriesIdCombo = categories.getValue().split(":", 2);
                p.setCategorie(Integer.parseInt(categoriesIdCombo[0]));
                

                ProduitService serv = new ProduitService();
                try {
                    
                    System.out.println("amira");
                    serv.update(p);
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                        Parent root = loader.load();
                        HomeController controller = loader.getController();
                        controller.changePage("Produits");

                        editProduit.getScene().setRoot(root);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
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

}
