package Hero;
import Map.Map;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.KeyValue ;

import javax.swing.text.StyledEditorKit;
import java.io.File;
import java.security.Key;

public class Personnages  {
    private String nom;
    private int hp ;
    private static Circle circle ;
    static Image poseidonhaut1 = ImageLoader.get().load("poseidonhaut1.png") ;
    static Image poseidonhaut2 = ImageLoader.get().load("poseidonhaut2.png") ;
    static Image poseidondroite1 = ImageLoader.get().load("poseidondroite1.png") ;
    static Image poseidondroite2 = ImageLoader.get().load("poseidondroite2.png") ;
    static Image poseidonbas1 = ImageLoader.get().load("poseidonbas1.png") ;
    static Image poseidonbas2 = ImageLoader.get().load("poseidonbas2.png") ;
    static Image poseidongauche1 = ImageLoader.get().load("poseidongauche1.png") ;
    static Image poseidongauche2 = ImageLoader.get().load("poseidongauche2.png") ;
    static Image bombe_eau = ImageLoader.get().load("bombe_eau.png")  ;
    static private BooleanProperty enMarcheUp = new SimpleBooleanProperty(true) ;
    static private BooleanProperty enMarcheBombe = new SimpleBooleanProperty(true) ;
    final static double  deplacement = 64 ;
    static Image terrain4 = ImageLoader.get().load("terrain4.png")  ;
    static ImagePattern terrain4Pattern = new ImagePattern(terrain4);
    static final Rectangle[] map = Map.getMapRectangle() ;
    public Personnages() {

        circle = new Circle(20,new ImagePattern(bombe_eau)) ;
        circle.opacityProperty().set(0) ;


    }

    public static boolean isEmpty(String case3,Rectangle rectangle) {
        if ( case3 == "UP" ) {
            for ( Rectangle i : map ) {
                if ( rectangle.getY() - deplacement == i.getY() && rectangle.getX() == i.getX() && i.getFill().equals(terrain4Pattern) ) {
                    return true ;
                }
            }
            return false;
        }
        if ( case3 == "RIGHT" ) {
            for ( Rectangle i : map) {
                if ( rectangle.getX()  + deplacement == i.getX() && rectangle.getY() == i.getY() && i.getFill().equals(terrain4Pattern)) {
                    return true ;
                }
            }
            return false ;
        }
        if ( case3 == "LEFT" ) {
            for ( Rectangle i : map ) {
                if ( rectangle.getX() - deplacement == i.getX() && i.getFill().equals(terrain4Pattern)) {
                    return true ;
                }
            }
            return false ;
        }
        else if ( case3 == "DOWN") {
            for ( Rectangle i : map ) {
                if ( rectangle.getY()+deplacement  == i.getY() && rectangle.getX() == i.getX() && i.getFill().equals(terrain4Pattern)) {
                    return true ;
                }
            }
            return false ;
        }
        return false ;
    }

    public static void moveRectangleOnKeyPress(Scene scene, final Rectangle rectangle) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                  @Override
                                  public void handle(KeyEvent event) {
                                      switch (event.getCode()) {
                                          case UP :
                                              if ((int)rectangle.getY() > 0 + rectangle.getHeight() && Personnages.enMarcheUp.getValue() && isEmpty("UP",rectangle) ) {
                                                  Timeline timeline = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidonhaut1))),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidonhaut2))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidonhaut1))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true)),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.yProperty(), (int)rectangle.getY() - deplacement))
                                                  );
                                                  timeline.play();
                                              }
                                              break;
                                          case RIGHT:
                                              if (rectangle.getX() < scene.getWidth() - rectangle.getWidth() - deplacement && Personnages.enMarcheUp.getValue() && isEmpty("RIGHT",rectangle)) {
                                                  Timeline timeline = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidondroite1))),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidondroite2))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidondroite1))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true)),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.xProperty(), rectangle.getX() + deplacement))
                                                  );
                                                  timeline.play();
                                              }

                                              break;
                                          case DOWN:

                                              if (rectangle.getY() < scene.getHeight() - rectangle.getHeight() - deplacement && Personnages.enMarcheUp.getValue() && isEmpty("DOWN",rectangle)) {
                                                  Timeline timeline = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidonbas1))),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidonbas2))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidonbas1))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.yProperty(), rectangle.getY() + deplacement)),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true))
                                                  );
                                                  timeline.play();


                                              }
                                              break;
                                          case LEFT:
                                              if (rectangle.getX() > 0 + deplacement && Personnages.enMarcheUp.getValue() && isEmpty("LEFT",rectangle)) {
                                                  Timeline timeline = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidongauche1))),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidongauche2))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidongauche1))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.xProperty(), (rectangle.getX() - deplacement))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true))
                                                  );
                                                  timeline.play();

                                              }
                                              break;
                                          case E:
                                              if (Personnages.enMarcheBombe.getValue() && Personnages.enMarcheUp.getValue()) {
                                                  Personnages.circle.setCenterX(rectangle.getX() + rectangle.getWidth()/2);
                                                  Personnages.circle.setCenterY(rectangle.getY() + rectangle.getHeight()-Personnages.circle.getRadius());
                                                  Personnages.circle.opacityProperty().set(1);
                                                  Personnages.enMarcheBombe.set(false);
                                                  Timeline timeline5 = new Timeline();

                                                  timeline5.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(Personnages.circle.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(1990), new KeyValue(Personnages.circle.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(2000), new KeyValue(Personnages.circle.opacityProperty(), 0)),
                                                          new KeyFrame(Duration.millis(3000), new KeyValue(Personnages.enMarcheBombe.asObject(), true))
                                                  );
                                                  timeline5.play();
                                              }
                                      }
                                  }
                              }
        );

    }

    public String getNom() {
        return nom;
    }

    public int getHp() {
        return hp;
    }

    public static double getDeplacement() {
        return deplacement ;
    }

    public static Circle getCircle() {
        return circle;
     }

}
