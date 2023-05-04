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
import arcade.Entities.user;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
/**
 *
 * @author Amira
 */
public class Arcade extends Application{
    
    public static Map<Integer,Integer> panier =new HashMap();
    public static int categorieId;
    public static user loggedInUser = new user();

    @Override
    public void start(Stage primaryStage) throws IOException{
    //try {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/main.fxml"));
        /*Scene scene = new Scene(root,1680,900);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Arcade");
        primaryStage.show();*/
        
        Scene scene = new Scene(root,1680,900);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Arcade");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
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
