/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Amira
 */
public class arcade extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException{
    //try {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/addCategorie.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Arcade");
        primaryStage.show();/*
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); 
        }*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
