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
    private Circle circle ;
    private Circle circle2 ;
    Image poseidonhaut1 = ImageLoader.get().load("poseidonhaut1.png") ;
    Image poseidonhaut2 = ImageLoader.get().load("poseidonhaut2.png") ;
    Image poseidondroite1 = ImageLoader.get().load("poseidondroite1.png") ;
    Image poseidondroite2 = ImageLoader.get().load("poseidondroite2.png") ;
    Image poseidonbas1 = ImageLoader.get().load("poseidonbas1.png") ;
    Image poseidonbas2 = ImageLoader.get().load("poseidonbas2.png") ;
    Image poseidongauche1 = ImageLoader.get().load("poseidongauche1.png") ;
    Image poseidongauche2 = ImageLoader.get().load("poseidongauche2.png") ;
    Image bombe_eau = ImageLoader.get().load("bombe_eau.png")  ;
    ImagePattern bomb_eauPattern = new ImagePattern(bombe_eau) ;

    private Rectangle viePersonnage = new Rectangle() ;
    static private BooleanProperty enMarcheUp = new SimpleBooleanProperty(true) ;
    static private BooleanProperty enMarcheBombe = new SimpleBooleanProperty(true) ;
    static private BooleanProperty enMarcheBombe2 = new SimpleBooleanProperty(true) ;
    final static double  deplacement = 64 ;
    static Image grassImage = ImageLoader.get().load("terrain4.png")  ;
    static ImagePattern grass = new ImagePattern(grassImage);
    static private BooleanProperty bombeActive = new SimpleBooleanProperty(true) ;
    private Rectangle rectangle = new Rectangle(15, 15, Personnages.getDeplacement(), Personnages.getDeplacement()) ;

        public Personnages() {

            circle = new Circle(30,bomb_eauPattern) ;
            circle2 = new Circle(30,bomb_eauPattern) ;
            circle.opacityProperty().set(0) ;
            circle2.opacityProperty().set(0) ;
            rectangle.setX(Personnages.getDeplacement()) ;
            rectangle.setY(Personnages.getDeplacement()) ;
            rectangle.setFill(new ImagePattern(poseidonbas1));

            hp=100 ;
            viePersonnage.setX(20);
            viePersonnage.setY(10);
            viePersonnage.setOpacity(0.7);
            viePersonnage.setWidth(hp*3);
            viePersonnage.setHeight(30);
            viePersonnage.setFill(Color.GREEN);


    }

    public static ImagePattern getGrass() {
        return grass;
    }

    public boolean isEmpty(String case3, Rectangle rectangle) {
        if ( case3 == "UP" ) {
            for ( Rectangle i : Map.getMap() ) {
                if ( rectangle.getY() - deplacement == i.getY() && rectangle.getX() == i.getX() && i.getFill().equals(grass) ) {
                    return true ;
                }
            }
            return false;
        }
        if ( case3 == "RIGHT" ) {
            for ( Rectangle i : Map.getMap()) {
                if ( rectangle.getX()  + deplacement == i.getX() && rectangle.getY() == i.getY() && i.getFill().equals(grass)) {
                    return true ;
                }
            }
            return false ;
        }
        if ( case3 == "LEFT" ) {
            for ( Rectangle i : Map.getMap() ) {
                if ( rectangle.getX() - deplacement == i.getX() && rectangle.getY() == i.getY() && i.getFill().equals(grass)) {
                    return true ;
                }
            }
            return false ;
        }
        else if ( case3 == "DOWN") {
            for ( Rectangle i : Map.getMap() ) {
                if ( rectangle.getY()+deplacement  == i.getY() && rectangle.getX() == i.getX() && i.getFill().equals(grass)) {
                    return true ;
                }
            }
            return false ;
        }
        return false ;
    }

    public void  moveRectangleOnKeyPress(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                  @Override
                                  public void handle(KeyEvent event) {
                                      switch (event.getCode()) {
                                          case UP :
                                              if (rectangle.getY() > 0 + rectangle.getHeight() && Personnages.enMarcheUp.getValue() && isEmpty("UP",rectangle) ) {
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
                                                  Timeline timeline2 = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline2.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidondroite1))),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidondroite2))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidondroite1))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true)),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.xProperty(), rectangle.getX() + deplacement))
                                                  );
                                                  timeline2.play();
                                              }

                                              break;
                                          case DOWN:

                                              if (rectangle.getY() < scene.getHeight() - rectangle.getHeight() - deplacement && Personnages.enMarcheUp.getValue() && isEmpty("DOWN",rectangle)) {
                                                  Timeline timeline3 = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline3.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidonbas1))),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidonbas2))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidonbas1))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.yProperty(), rectangle.getY() + deplacement)),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true))
                                                  );
                                                  timeline3.play();


                                              }
                                              break;
                                          case LEFT:
                                              if (rectangle.getX() > 0 + deplacement && Personnages.enMarcheUp.getValue() && isEmpty("LEFT",rectangle)) {
                                                  Timeline timeline4 = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline4.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidongauche1))),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidongauche2))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.fillProperty(), new ImagePattern(poseidongauche1))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(rectangle.xProperty(), (rectangle.getX() - deplacement))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true))
                                                  );
                                                  timeline4.play();

                                              }
                                              break;
                                          case E:
                                              if (Personnages.enMarcheBombe.getValue() && Personnages.enMarcheUp.getValue()) {
                                                  circle.setCenterX(rectangle.getX() + rectangle.getWidth()/2);
                                                  circle.setCenterY(rectangle.getY() + rectangle.getHeight()-circle.getRadius());
                                                  circle.opacityProperty().set(1);
                                                  Personnages.enMarcheBombe.set(false);
                                                  Personnages.enMarcheBombe2.set(false);
                                                  Timeline timeline5 = new Timeline();

                                                  timeline5.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(circle.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(1990), new KeyValue(circle.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(2000), new KeyValue(circle.opacityProperty(), 0)),
                                                          new KeyFrame(Duration.millis(1500), new KeyValue(Personnages.enMarcheBombe2.asObject(), true)),
                                                          new KeyFrame(Duration.millis(2000), e -> bombeExplosion(circle) ),
                                                          new KeyFrame(Duration.millis(3000), new KeyValue(Personnages.enMarcheBombe.asObject(), true))

                                                  );
                                                  timeline5.play();
                                              }
                                              if (Personnages.enMarcheBombe2.getValue() && Personnages.enMarcheUp.getValue()) {
                                                  circle2.setCenterX(rectangle.getX() + rectangle.getWidth()/2);
                                                  circle2.setCenterY(rectangle.getY() + rectangle.getHeight()-circle.getRadius());
                                                  circle2.opacityProperty().set(1);
                                                  Personnages.enMarcheBombe.set(false);
                                                  Personnages.enMarcheBombe2.set(false);
                                                  Timeline timeline6 = new Timeline();

                                                  timeline6.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(circle2.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(1990), new KeyValue(circle2.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(2000), new KeyValue(circle2.opacityProperty(), 0)),
                                                          new KeyFrame(Duration.millis(1500), new KeyValue(Personnages.enMarcheBombe.asObject(), true)),
                                                          new KeyFrame(Duration.millis(2000), e -> bombeExplosion(circle2) ),
                                                          new KeyFrame(Duration.millis(3000), new KeyValue(Personnages.enMarcheBombe2.asObject(), true))
                                                  );
                                              timeline6.play() ;
                                      }
                                      }
                                  }
                              }
        );

    }

    public void bombeExplosion(Circle circle) {
        int cptHaut = 0;
        int cptDroite = 0;
        int cptGauche = 0;
        int cptBas = 0;
        for (Rectangle i : Map.getMap()) {
            if (circle.getCenterY() + circle.getRadius() == i.getY() && circle.getCenterX() - i.getWidth() / 2 == i.getX()
                    && i.getFill() == Map.getObstacle()) {

                i.setFill(grass);
                cptBas++;
            } else if (circle.getCenterY() + circle.getRadius() == i.getY() && circle.getCenterX() - i.getWidth() / 2 == i.getX()
                    && i.getFill() == Map.getMur0()) {
                cptBas++;
            }

            if (circle.getCenterY() + circle.getRadius() - deplacement - i.getHeight() == i.getY() && circle.getCenterX() - i.getWidth() / 2 == i.getX() && i.getFill() == Map.getObstacle()) {
                i.setFill(grass);
                cptHaut++;
            } else if (circle.getCenterY() + circle.getRadius() - deplacement - i.getHeight() == i.getY()
                    && circle.getCenterX() - i.getWidth() / 2 == i.getX() && i.getFill() == Map.getMur0()) {
                cptHaut++;
            }

            if (circle.getCenterX() + i.getWidth() / 2 == i.getX() && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY() && i.getFill() == Map.getObstacle()) {
                i.setFill(grass);
                cptGauche++;
            } else if ( circle.getCenterX() + i.getWidth() / 2 == i.getX()
                    && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY() && i.getFill() == Map.getMur0()) {
                cptGauche++;
            }

            if ( circle.getCenterX() - i.getWidth() / 2 - deplacement == i.getX() && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY() && i.getFill() == Map.getObstacle()) {
                i.setFill(grass);
                cptDroite++;
            } else if ( circle.getCenterX() - i.getWidth() / 2 - deplacement == i.getX()
                    && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY() && i.getFill() == Map.getMur0()) {
                cptDroite++;
            }


        }
        for (Rectangle i : Map.getMap()) {
            if ( circle.getCenterY() + circle.getRadius() + deplacement == i.getY() && circle.getCenterX() - i.getWidth() / 2 == i.getX()
                    && i.getFill() == Map.getObstacle() && cptBas == 0) {
                i.setFill(grass);

            }

            if (circle.getCenterY() + circle.getRadius() - (deplacement * 2) - i.getHeight() == i.getY()
                    && circle.getCenterX() - i.getWidth() / 2 == i.getX() && i.getFill() == Map.getObstacle() && cptHaut == 0) {
                i.setFill(grass);

            }

            if ( circle.getCenterX() + i.getWidth() / 2 + deplacement == i.getX()
                    && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY()
                    && i.getFill() == Map.getObstacle() && cptGauche == 0) {
                i.setFill(grass);

            }

            if ( circle.getCenterX() - i.getWidth() / 2 - (deplacement * 2) == i.getX()
                    && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY()
                    && i.getFill() == Map.getObstacle() && cptDroite == 0) {
                i.setFill(grass);
            }

        }
        if (( circle.getCenterY() + circle.getRadius() == rectangle.getY() && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX()) || circle.getCenterY() + circle.getRadius() + deplacement == rectangle.getY() && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX()
                && cptBas == 0) {
            hp-=20 ;
            setViePersonnage(hp);
        }
        if ((circle.getCenterY() + circle.getRadius() - deplacement - rectangle.getHeight() == rectangle.getY()
                && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX()) ||
                (circle.getCenterY() + circle.getRadius() - (deplacement * 2) - rectangle.getHeight() == rectangle.getY()
                        && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX() && cptHaut == 0 )) {
            hp -=20 ;
            setViePersonnage(hp);
        }

        if (( circle.getCenterX() + rectangle.getWidth() / 2 == rectangle.getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()) || (circle.getCenterX() + rectangle.getWidth() / 2 + deplacement == rectangle.getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()
                && cptGauche == 0)) {
            hp -=20 ;
            setViePersonnage(hp);
        }

        if ((circle.getCenterX() - rectangle.getWidth() / 2 - deplacement == rectangle.getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()) || (circle.getCenterX() - rectangle.getWidth() / 2 - (deplacement * 2) == rectangle.getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()
              && cptDroite == 0)) {
            hp-=20 ;
            setViePersonnage(hp);
        }

        if ((circle.getCenterX() - rectangle.getWidth() /2 == rectangle.getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()) ){
            hp -=20 ;
            setViePersonnage(hp);
        }




        }




    public String getNom() {
        return nom;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
            hp = hp ;
    }

    public Rectangle getViePersonnage() {
            return viePersonnage ;
    }

    public void setViePersonnage(int hp) {
         viePersonnage.setWidth(hp*3);
    }

    public static double getDeplacement() {
        return deplacement ;
    }

    public Circle getCircle() {
        return circle;
     }

    public Circle getCircle2() {
        return circle2;
    }

     public Rectangle getRectangle() { return rectangle ;}

}
