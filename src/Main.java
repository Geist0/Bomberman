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
        Personnages hero = new Personnages()  ;


       /* Rectangle viePersonnages = new Rectangle(200.0, 50.0, Color.RED);
        Rectangle vieBoss = new Rectangle(200.0, 50.0, Color.BLUE);

        DoubleProperty healthPercentage1 = new SimpleDoubleProperty(1.0);
        DoubleProperty healthPercentage2 = new SimpleDoubleProperty(1.0);

        DoubleBinding b1 = viePersonnages.widthProperty().multiply(healthPercentage1);
        DoubleBinding b2 = vieBoss.widthProperty().multiply(healthPercentage2);
        viePersonnages.widthProperty().bind(b1);
        vieBoss.widthProperty().bind(b2);*/

        Image image4 = ImageLoader.get().load("bombe_eau.png") ;
        Image poseidon = ImageLoader.get().load("poseidonbas1.png") ;
        Pane root = new Pane() ;
        primaryStage.setTitle("Age of Mythorman of Mythology 3");
        Rectangle rectangle = new Rectangle(15, 15, Personnages.getDeplacement(), Personnages.getDeplacement()) ;
        rectangle.setX(Personnages.getDeplacement()) ;
        rectangle.setY(Personnages.getDeplacement()) ;
        rectangle.setFill(new ImagePattern(poseidon));

        Map.putMapImage();

        root.getChildren().addAll(Map.getMap());

        root.getChildren().add(rectangle) ;
        root.getChildren().add(Personnages.getCircle()) ;
        final Scene scene = new Scene(root, 832, 832);
        primaryStage.setScene(scene) ;
        Personnages.moveRectangleOnKeyPress(scene,rectangle) ;

        primaryStage.show();



    }










    public static void main(String[] args) {
        launch(args);
    }
}