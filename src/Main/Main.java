package Main;

import Hero.ImageLoader;
import Hero.Personnages;
import Map.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    Scene scene, scene1, scene2 ;

    private static Stage primaryStage;

    public static Stage getStage() {
        return primaryStage;
    }
    public static Scene scene4;
    public static Scene scene3;

    static Pane root = new Pane();
    //scene= scene du jeu
    //scene1=scene du menu
    //scene2=scene des règles du jeu
    private static Personnages boss0 = new Personnages("hades", 64 * 7, 64 * 7, 10, 20, 512, 10);
    private static Personnages boss = new Personnages("poseidon", 64 * 7, 64 * 7, 10, 20, 512, 10);
    private static Personnages boss1 = new Personnages("aphrodite", 64 * 7, 64 * 7, 10, 20, 512, 10);
    private static Personnages boss2 = new Personnages("zeus", 64 * 7, 64 * 7, 10, 20, 512, 10);
    private static Personnages hero = new Personnages("hero", (int) Personnages.getDeplacement(), (int) Personnages.getDeplacement(), 100, 20, 20, 10);
    private static Rectangle eclair=new Rectangle(64,64,128,64);
    private static Rectangle coeurAphrodite=new Rectangle(64,64,128,64);
    private static Rectangle requin=new Rectangle(64,64,128,64);
    private static Rectangle trident=new Rectangle(64,64,128,64);

    public void start(Stage stage) {
        primaryStage = stage;
        //Button
        Personnages.getHades2().setFill(boss0.getSprite()[4]);
        Personnages.getHades2().setX(768);
        Personnages.getHades2().setY(6*64);
        Personnages.getPoseidon2().setFill(boss.getSprite()[4]);
        Personnages.getPoseidon2().setX(768);
        Personnages.getPoseidon2().setY(7*64);
        Personnages.getPoseidon2().setOpacity(0);
        Personnages.getAphrodite2().setFill(boss1.getSprite()[4]);
        Personnages.getAphrodite2().setX(768);
        Personnages.getAphrodite2().setY(8*64);
        Personnages.getZeus2().setFill(boss2.getSprite()[4]);
        Personnages.getZeus2().setX(768);
        Personnages.getZeus2().setY(9*64);


        Button button = new Button("Jouer");

        button.setOnMouseClicked(e -> {
            primaryStage.setScene(scene);
            hero.moveRectangleOnKeyPress(scene);
            Threads ia = new Threads();
            ia.start();
        });


        //Button1
        Button button1 = new Button("Règle du jeu");

        button1.setOnMouseClicked(e -> primaryStage.setScene(scene2));

        //Button2
        Button button2 = new Button("Quitter");
        button2.setOnAction(e -> primaryStage.close());


        Button button3 = new Button("Retour arrière");
        button3.setOnAction(e -> primaryStage.setScene(scene1));

        Button button4 = new Button("Retour à l'écran titre");
        button4.setOnAction(e -> primaryStage.setScene(scene1));

        VBox layout1 = new VBox(80);
        layout1.setLayoutX(330);
        layout1.setLayoutY(200);
        layout1.getChildren().add(button);


        Pane root2 = new Pane();
        Pane root3 = new Pane();
        Pane root4 = new Pane();
        Pane root5 = new Pane() ;
        Pane root6 = new Pane() ;
        root4.setPrefSize(832, 832);
        root3.setPrefSize(410, 512);
        root2.setPrefSize(832, 832);
        root.setPrefSize(832, 832);

        scene1 = new Scene(root3, 832, 832);



        primaryStage.setTitle("Bomberman Odyssey");
        StackPane layout2 = new StackPane();
        layout1.getChildren().add(button1);
        layout1.getChildren().add(button2);

        layout2.getChildren().add(button3);
        layout2.setLayoutX(330);
        layout2.setLayoutY(700);

        button.setPrefWidth(170);
        button.setPrefHeight(50);
        button.setFont(Font.font("Verdana", 20));
        button2.setPrefWidth(170);
        button2.setPrefHeight(50);
        button2.setFont(Font.font("Verdana", 20));
        button1.setPrefWidth(170);
        button1.setPrefHeight(50);
        button1.setFont(Font.font("Verdana", 20));

        button3.setPrefWidth(170);
        button3.setPrefHeight(50);
        button3.setFont(Font.font("Verdana", 20));

        button4.setPrefWidth(250);
        button4.setPrefHeight(50);
        button4.setFont(Font.font("Verdana", 20));

        scene2 = new Scene(root4, 832, 832);
        BackgroundImage regle = new BackgroundImage(new Image("parchemn.png", 410, 512, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        BackgroundImage menu = new BackgroundImage(new Image("fondmenu.png", 832, 832, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);


        root4.setBackground(new Background(menu));
        root5.setBackground(new Background(menu));

        root2.setBackground(new Background(regle));



        root2.setLayoutX(225);
        root2.setLayoutY(100);
        root3.setBackground(new Background(menu));
        root4.getChildren().add(root2);
        root4.getChildren().add(layout2);
        root3.getChildren().add(layout1);
        Map.putMapImage(Map.getMap());
        Map.putMapImage(Map.getMap2());

        VBox layout3 = new VBox(40) ;
        VBox layout4 = new VBox(40) ;
        Text text = new Text();
        Text text1 = new Text() ;
        String messageFin = "vous avez gagné" ;
        String messagePerdu = "vous avez perdu" ;
        text.setText(messageFin);
        text.setFont(Font.font("Verdana",40));
        layout4.getChildren().add(text) ;
        layout4.setLayoutX(250);
        layout4.setLayoutY(300);
        layout3.setLayoutX(290);
        layout3.setLayoutY(600);
        layout3.getChildren().add(button4) ;
        root5.getChildren().add(layout3) ;
        root5.getChildren().add(layout4) ;


        Button button5 = new Button("Quitter");
        button5.setOnAction(e -> System.exit(0));

        button5.setPrefWidth(170);
        button5.setPrefHeight(50);
        button5.setFont(Font.font("Verdana", 20));

        StackPane layout5 = new StackPane();
        layout5.setLayoutX(331);
        layout5.setLayoutY(600);
        layout5.getChildren().add(button5);
        root6.getChildren().add(layout5);


        StackPane layout6 = new StackPane();

        text1.setText(messagePerdu);
        text1.setFont(Font.font("Verdana",40));
        layout6.getChildren().add(text1) ;
        layout6.setLayoutX(250);
        layout6.setLayoutY(300);
        root6.getChildren().add(layout6);

        root6.setBackground(new Background(menu));


        root.getChildren().addAll(Map.getMap());

        root.getChildren().add(hero.getRectangle());
        root.getChildren().add(hero.getCircle());
        root.getChildren().add(hero.getCircle2());
        root.getChildren().add(hero.getViePersonnage());
        root.getChildren().add(boss.getRectangle());
        root.getChildren().add(boss.getCircle());
        root.getChildren().add(boss.getCircle2());
        root.getChildren().add(boss.getViePersonnage());
        root.getChildren().add(Personnages.getHades2());
        root.getChildren().add(Personnages.getAphrodite2());
        root.getChildren().add(Personnages.getZeus2());
        root.getChildren().add(Personnages.getPoseidon2());
        root.getChildren().add(eclair) ;
        root.getChildren().add(requin) ;
        root.getChildren().add(coeurAphrodite) ;
        root.getChildren().add(trident) ;

        scene3 = new Scene(root5, 832, 832);
        scene4=new Scene(root6,832,832);
        scene = new Scene(root, 832, 832);


       creerRect(eclair,"eclair");
       creerRect(trident,"trident");
       creerRect(coeurAphrodite,"coeurAphro");
       creerRect(requin,"requin");

        primaryStage.setScene(scene1);
        primaryStage.setTitle("title here");
        primaryStage.show();

    }

    public static Personnages getBoss() {
        return boss;
    }

    public static Personnages getBoss1() {
        return boss1;
    }

    public static Personnages getBoss2() {
        return boss2;
    }

    public static Personnages getBoss0() {
        return boss0;
    }

    public static Personnages getHero() {
        return hero;
    }

    public static void setBoss(Personnages boss) {
        Main.boss = boss;
    }

    public static Scene getScene3() { return scene3 ;}

    public static Scene getScene4() {return scene4;}

    public static Pane getRoot() {
        return root;

    }

    public void creerRect(Rectangle nom,String objet){
        nom.setX(30);
        nom.setY(30);
        Image bomb = ImageLoader.get().load(objet+".png");
        nom.setFill(new ImagePattern(bomb));
        nom.opacityProperty().set(0);}

    public static Rectangle getEclair() {
        return eclair;
    }

    public static Rectangle getCoeurAphrodite() {
        return coeurAphrodite;
    }

    public static Rectangle getRequin() {
        return requin;
    }

    public static Rectangle getTrident() {
        return trident;
    }

    public static void main(String[] args) {
        launch(args);
    }



}
