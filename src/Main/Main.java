package Main;

import Hero.Personnages;
import Hero.Bot;
import Map.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private static Personnages boss = new Personnages("poseidon",64*7,64*7,100,20,512,10) ;
    private static Personnages hero = new Personnages("poseidon",(int)Personnages.getDeplacement(),(int)Personnages.getDeplacement(),100,20,20,10)  ;

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
        System.out.println("okok");
        Threads ia=new Threads();
        ia.start();
        System.out.println("okok2");
        hero.moveRectangleOnKeyPress(scene) ;

        primaryStage.show();

    }

    public static Personnages getBoss() {return boss ;}
    public static Personnages getHero() {return hero;}


    public static void main(String[] args) {
        launch(args);
    }
}