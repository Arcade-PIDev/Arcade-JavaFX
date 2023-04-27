/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Amira
 */
public class Arcade extends Application{
    
    public static Map<Integer,Integer> panier =new HashMap();
    
    @Override
    public void start(Stage primaryStage) throws IOException{
    //try {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/Home.fxml"));
        Scene scene = new Scene(root,1680,900);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Arcade");
        primaryStage.show();
        /*
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); 
        }*/
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
