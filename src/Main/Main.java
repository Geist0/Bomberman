package Main;

import Hero.Personnages;
import Map.Map;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    Scene scene, scene1, scene2;
    static Pane root = new Pane() ;
    //scene= scene du jeu
    //scene1=scene du menu
    //scene2=scene des règles du jeu
    private static Personnages boss0 = new Personnages("poseidon",64*7,64*7,10,20,512,10) ;
    private static Personnages boss = new Personnages("poseidon",64*7,64*7,10,20,512,10) ;
    private static Personnages boss1 = new Personnages("aphrodite",64*7,64*7,10,20,512,10) ;
    private static Personnages boss2 = new Personnages("zeus",64*7,64*7,10,20,512,10) ;
    private static Personnages hero = new Personnages("poseidon",(int)Personnages.getDeplacement(),(int)Personnages.getDeplacement(),100,20,20,10)  ;

    public void start(Stage primaryStage) {
        //Button
        Button button = new Button("Jouer");

        button.setOnMouseClicked(e -> { primaryStage.setScene(scene) ;
                hero.moveRectangleOnKeyPress(scene) ;
                Threads ia=new Threads();
                ia.start(); });



        //Button1
        Button button1 = new Button("Règle du jeu");

        button1.setOnMouseClicked(e -> primaryStage.setScene(scene2));

        //Button2
        Button button2=new Button("Quitter");
        button2.setOnAction(e -> primaryStage.close());


        Button button3 = new Button("Retour arrière") ;
        button3.setOnAction(e -> primaryStage.setScene(scene1));

        VBox layout1 = new VBox(80);
        layout1.setLayoutX(330);
        layout1.setLayoutY(200);
        layout1.getChildren().add(button);






        Pane root2 = new Pane() ;
        Pane root3 = new Pane() ;
        Pane root4 = new Pane() ;
        root4.setPrefSize(832,832);
        root3.setPrefSize(410,512);
        root2.setPrefSize(832,832);
        root.setPrefSize(832,832);
        scene1 = new Scene(root3, 832, 832);
        primaryStage.setTitle("B²-4AC");
        StackPane layout2 = new StackPane();
        layout1.getChildren().add(button1);
        layout1.getChildren().add(button2);

        layout2.getChildren().add(button3) ;
        layout2.setLayoutX(330);
        layout2.setLayoutY(700);

        button.setPrefWidth(170);
        button.setPrefHeight(50);
        button.setFont(Font.font("Verdana",20));
        button2.setPrefWidth(170);
        button2.setPrefHeight(50);
        button2.setFont(Font.font("Verdana",20));
        button1.setPrefWidth(170);
        button1.setPrefHeight(50);
        button1.setFont(Font.font("Verdana",20));

        button3.setPrefWidth(170);
        button3.setPrefHeight(50);
        button3.setFont(Font.font("Verdana",20));

        scene2 = new Scene(root4, 832, 832);
        BackgroundImage regle= new BackgroundImage(new Image("parchemn.png",410,512,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        BackgroundImage menu= new BackgroundImage(new Image("fondmenu.png",832,832,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);


        root4.setBackground(new Background(menu));
        root2.setBackground(new Background(regle));

        root2.setLayoutX(225);
        root2.setLayoutY(100);
        root3.setBackground(new Background(menu));
        root4.getChildren().add(root2) ;
        root4.getChildren().add(layout2);
        root3.getChildren().add(layout1);
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


        scene = new Scene(root, 832, 832);
        //primaryStage.setScene(scene) ;


        primaryStage.setScene(scene1);
        primaryStage.setTitle("title here");
        primaryStage.show();

    }

    public static Personnages getBoss() {return boss ;}
    public static Personnages getBoss1() {return boss1 ;}
    public static Personnages getBoss2() {return boss2 ;}
    public static Personnages getBoss0() {return boss0 ;}
    public static Personnages getHero() {return hero;}
    public static void setBoss(Personnages boss){ Main.boss = boss ;}
    public static Pane getRoot(){ return root;}
    public static void main(String[] args) {
        launch(args);
    }
}