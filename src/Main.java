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
    private static Personnages boss = new Personnages("poseidon",64*7,64*7,100,20,512,10) ;
    private static Personnages boss2 = new Personnages("poseidon",44*7,64*7,100,20,512,10) ;
    private static Personnages hero = new Personnages("poseidon",(int)Personnages.getDeplacement(),(int)Personnages.getDeplacement(),100,20,20,10)  ;


    @Override

    public void start(Stage primaryStage) {





        Pane root = new Pane() ;
        primaryStage.setTitle("Age of Mythorman of Mythology 3");


        Map.putMapImage();

        root.getChildren().addAll(Map.getMap());

        root.getChildren().add(hero.getRectangle()) ;
        root.getChildren().add(hero.getCircle()) ;
        root.getChildren().add(hero.getCircle2()) ;
        root.getChildren().add(hero.getViePersonnage());
        root.getChildren().add(boss.getRectangle()) ;
        root.getChildren().add(boss.getCircle()) ;
        root.getChildren().add(boss.getCircle2()) ;
        root.getChildren().add(boss.getViePersonnage());

        final Scene scene = new Scene(root, 832, 832);

        primaryStage.setScene(scene) ;
        hero.moveRectangleOnKeyPress(scene) ;

        primaryStage.show();



    }

    public static Personnages getBoss() {
        return boss ;
    }









    public static void main(String[] args) {
        launch(args);
    }
}