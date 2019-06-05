package sample ;
import Hero.* ;
import Map.* ;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends Application {
    @Override

    public void start(Stage primaryStage) {
        Personnages hero = new Personnages("poseidon",(int)Personnages.getDeplacement(),(int)Personnages.getDeplacement(),100,20,20,10)  ;




        Pane root = new Pane() ;
        primaryStage.setTitle("Age of Mythorman of Mythology 3");


        Map.putMapImage();

        root.getChildren().addAll(Map.getMap());

        root.getChildren().add(hero.getRectangle()) ;
        root.getChildren().add(hero.getCircle()) ;
        root.getChildren().add(hero.getCircle2()) ;
        root.getChildren().add(hero.getViePersonnage());
        final Scene scene = new Scene(root, 832, 832);
        primaryStage.setScene(scene) ;
        hero.moveRectangleOnKeyPress(scene) ;

        primaryStage.show();



    }











    public static void main(String[] args) {
        launch(args);
    }
}