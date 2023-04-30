/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static arcade.Arcade.panier;
import arcade.Entities.Produit;
import arcade.Entities.Commande;
import arcade.Service.PanierService;
import arcade.Service.ProduitService;
import arcade.Utils.Mailing;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Amira
 */
public class validerCommandeController implements Initializable{
    @FXML
    private Label userName;
    @FXML
    private Button validerCommande;
    
    int prixTotal=0;
    @FXML
    private Label prixTotalText;
    @FXML
    private ImageView QrCode;
    
      String msg = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
         ProduitService ps = new ProduitService();
         String data="";
        for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {
            try {
                Produit p = ps.getProduitById(entry.getKey());
                data+="Nom du produit:"+p.getNomProduit() + " Description:"+p.getDescription()+ " Prix:"+p.getPrix() +" Quantite:"+ entry.getValue()+" ";
                
                msg += p.getNomProduit() + ":\t" + p.getPrix() + "*" + entry.getValue() + "\n";
                
                prixTotal+=p.getPrix()*entry.getValue();
            } catch (Exception ex) {
                Logger.getLogger(validerCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String qr="http://api.qrserver.com/v1/create-qr-code/?data="+data+"  Prix Total :"+prixTotal+" ";
         QrCode.setImage(new Image(qr, 300, 300, false, false));
         msg += "\n" + "prixTotal:\t" + prixTotal;
         prixTotalText.setText(prixTotal+"");
    }    

    @FXML
    private void validerCommande(ActionEvent event) {
        try {

        Notifications.create().text("Votre facture a été envoyée par email.")
            .darkStyle().hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).show();
        
        Mailing m = new Mailing("waterproof.application@gmail.com", "jadifaaqzvlxtagw");
        m.sendMail("Votre Facture", msg, "waterproof.application@gmail.com", "amira.benmbarek@esprit.tn");
            panier.clear();
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeFrontController homeCtrl = homeLoader.getController();
            homeCtrl.changePage("Produits");
            userName.getScene().setRoot(homeRoot);
            
        } catch (IOException ex) {
            Logger.getLogger(validerCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
