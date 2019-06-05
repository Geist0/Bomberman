package Hero;
import Map.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.KeyValue ;


public class Personnages  {
    private String nom;
    private int hp ;
    private Circle circle ;
    private Circle circle2 ;
    private ImagePattern[] sprite;
    private int damages;
    private Rectangle viePersonnage = new Rectangle() ;
    static private BooleanProperty enMarcheUp = new SimpleBooleanProperty(true) ;
    static private BooleanProperty enMarcheBombe = new SimpleBooleanProperty(true) ;
    static private BooleanProperty enMarcheBombe2 = new SimpleBooleanProperty(true) ;
    final static double  deplacement = 64 ;
    static Image grassImage = ImageLoader.get().load("terrain4.png")  ;
    static ImagePattern grass = new ImagePattern(grassImage);
    static private BooleanProperty bombeActive = new SimpleBooleanProperty(true) ;
    private Rectangle rectangle = new Rectangle(15, 15, Personnages.getDeplacement(), Personnages.getDeplacement()) ;

        public Personnages(String nom,int caseX,int caseY,int hp, int damages,int vieX,int vieY) {
            sprite= new ImagePattern[9];
            this.nom=nom;
            creerImage(nom);
            circle = new Circle(30,sprite[8]) ;
            circle2 = new Circle(30,sprite[8]) ;
            circle.opacityProperty().set(0) ;
            circle2.opacityProperty().set(0) ;
            rectangle.setX(caseX) ;
            rectangle.setY(caseY) ;
            rectangle.setFill(sprite[4]);
            this.damages=damages;
            this.hp=hp ;
            viePersonnage.setX(vieX);
            viePersonnage.setY(vieY);
            viePersonnage.setOpacity(0.7);
            viePersonnage.setWidth(hp*3);
            viePersonnage.setHeight(30);
            viePersonnage.setFill(Color.GREEN);


    }
    public void creerImage(String nom){
        Image ihaut1 = ImageLoader.get().load(nom+"haut1.png") ;
        Image ihaut2 = ImageLoader.get().load(nom+"haut2.png") ;
        Image idroite1 = ImageLoader.get().load(nom+"droite1.png") ;
        Image idroite2 = ImageLoader.get().load(nom+"droite2.png") ;
        Image ibas1 = ImageLoader.get().load(nom+"bas1.png") ;
        Image ibas2 = ImageLoader.get().load(nom+"bas2.png") ;
        Image igauche1 = ImageLoader.get().load(nom+"gauche1.png") ;
        Image igauche2 = ImageLoader.get().load(nom+"gauche2.png") ;
        Image ibombe = ImageLoader.get().load("bombe_eau.png")  ;

        sprite[0]=new ImagePattern(ihaut1);
        sprite[1]=new ImagePattern(ihaut2);
        sprite[2]=new ImagePattern(idroite1);
        sprite[3]=new ImagePattern(idroite2);
        sprite[4]=new ImagePattern(ibas1);
        sprite[5]=new ImagePattern(ibas2);
        sprite[6]=new ImagePattern(igauche1);
        sprite[7]=new ImagePattern(igauche2);
        sprite[8]=new ImagePattern(ibombe);
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
                                              if (Personnages.this.rectangle.getY() > 0 + Personnages.this.rectangle.getHeight() && Personnages.enMarcheUp.getValue() && isEmpty("UP", Personnages.this.rectangle) ) {
                                                  Timeline timeline = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[0])),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[1])),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[0])),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true)),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.this.rectangle.yProperty(), (int) Personnages.this.rectangle.getY() - deplacement))
                                                  );
                                                  timeline.play();
                                              }
                                              break;
                                          case RIGHT:
                                              if (Personnages.this.rectangle.getX() < scene.getWidth() - Personnages.this.rectangle.getWidth() - deplacement && Personnages.enMarcheUp.getValue() && isEmpty("RIGHT", Personnages.this.rectangle)) {
                                                  Timeline timeline2 = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline2.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[2] )),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[3])),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[2])),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true)),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.this.rectangle.xProperty(), Personnages.this.rectangle.getX() + deplacement))
                                                  );
                                                  timeline2.play();
                                              }

                                              break;
                                          case DOWN:

                                              if (Personnages.this.rectangle.getY() < scene.getHeight() - Personnages.this.rectangle.getHeight() - deplacement && Personnages.enMarcheUp.getValue() && isEmpty("DOWN", Personnages.this.rectangle)) {
                                                  Timeline timeline3 = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline3.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[4])),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[5])),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[4])),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.this.rectangle.yProperty(), Personnages.this.rectangle.getY() + deplacement)),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true))
                                                  );
                                                  timeline3.play();


                                              }
                                              break;
                                          case LEFT:
                                              if (Personnages.this.rectangle.getX() > 0 + deplacement && Personnages.enMarcheUp.getValue() && isEmpty("LEFT", Personnages.this.rectangle)) {
                                                  Timeline timeline4 = new Timeline();
                                                  Personnages.enMarcheUp.set(false);
                                                  timeline4.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[6])),
                                                          new KeyFrame(Duration.millis(150), new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[7])),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[6])),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.this.rectangle.xProperty(), (Personnages.this.rectangle.getX() - deplacement))),
                                                          new KeyFrame(Duration.millis(300), new KeyValue(Personnages.enMarcheUp.asObject(), true))
                                                  );
                                                  timeline4.play();

                                              }
                                              break;
                                          case E:
                                              if (Personnages.enMarcheBombe.getValue() && Personnages.enMarcheUp.getValue()) {
                                                  circle.setCenterX(Personnages.this.rectangle.getX() + Personnages.this.rectangle.getWidth()/2);
                                                  circle.setCenterY(Personnages.this.rectangle.getY() + Personnages.this.rectangle.getHeight()-circle.getRadius());
                                                  circle.opacityProperty().set(1);
                                                  Personnages.enMarcheBombe.set(false);
                                                  Personnages.enMarcheBombe2.set(false);
                                                  Timeline timeline5 = new Timeline();

                                                  timeline5.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(circle.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(1990), new KeyValue(circle.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(2000), new KeyValue(circle.opacityProperty(), 0)),
                                                          new KeyFrame(Duration.millis(1500), new KeyValue(Personnages.enMarcheBombe2.asObject(), true)),
                                                          new KeyFrame(Duration.millis(2000), e -> bombeExplosion(circle, sample.Main.getBoss()) ),
                                                          new KeyFrame(Duration.millis(3000), new KeyValue(Personnages.enMarcheBombe.asObject(), true))

                                                  );
                                                  timeline5.play();
                                              }
                                              if (Personnages.enMarcheBombe2.getValue() && Personnages.enMarcheUp.getValue()) {
                                                  circle2.setCenterX(Personnages.this.rectangle.getX() + Personnages.this.rectangle.getWidth()/2);
                                                  circle2.setCenterY(Personnages.this.rectangle.getY() + Personnages.this.rectangle.getHeight()-circle.getRadius());
                                                  circle2.opacityProperty().set(1);
                                                  Personnages.enMarcheBombe.set(false);
                                                  Personnages.enMarcheBombe2.set(false);
                                                  Timeline timeline6 = new Timeline();

                                                  timeline6.getKeyFrames().addAll(
                                                          new KeyFrame(Duration.ZERO, new KeyValue(circle2.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(1990), new KeyValue(circle2.opacityProperty(), 1)),
                                                          new KeyFrame(Duration.millis(2000), new KeyValue(circle2.opacityProperty(), 0)),
                                                          new KeyFrame(Duration.millis(1500), new KeyValue(Personnages.enMarcheBombe.asObject(), true)),
                                                          new KeyFrame(Duration.millis(2000), e -> bombeExplosion(circle2,sample.Main.getBoss()) ),
                                                          new KeyFrame(Duration.millis(3000), new KeyValue(Personnages.enMarcheBombe2.asObject(), true))
                                                  );
                                              timeline6.play() ;
                                      }
                                      }
                                  }
                              }
        );

    }

    public void bombeExplosion(Circle circle,Personnages boss) {
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
        if (( circle.getCenterY() + circle.getRadius() == boss.getRectangle().getY() && circle.getCenterX() - rectangle.getWidth() / 2 == boss.getRectangle().getX())
                || circle.getCenterY() + circle.getRadius() + deplacement == boss.getRectangle().getY() && circle.getCenterX() - rectangle.getWidth() / 2 == boss.getRectangle().getX()
                && cptBas == 0) {
            System.out.println(hp);
            boss.hp -= this.damages ;
            boss.setViePersonnage(hp);
            System.out.println(hp);

        }
        if ((circle.getCenterY() + circle.getRadius() - deplacement - boss.getRectangle().getHeight() == boss.getRectangle().getY()
                && circle.getCenterX() - rectangle.getWidth() / 2 == boss.getRectangle().getX()) ||
                (circle.getCenterY() + circle.getRadius() - (deplacement * 2) - rectangle.getHeight() == boss.getRectangle().getY()
                        && circle.getCenterX() - rectangle.getWidth() / 2 == boss.getRectangle().getX() && cptHaut == 0 )) {
            System.out.println(hp);

            boss.hp -= this.damages ;
            boss.setViePersonnage(hp);
            System.out.println(hp);

        }

        if (( circle.getCenterX() + rectangle.getWidth() / 2 == boss.getRectangle().getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == boss.rectangle.getY())
                || (circle.getCenterX() + rectangle.getWidth() / 2 + deplacement == boss.rectangle.getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == boss.rectangle.getY()
                && cptGauche == 0)) {
            System.out.println(hp);

            boss.hp -= this.damages ;
            boss.setViePersonnage(hp);
            System.out.println(hp);

        }

        if ((circle.getCenterX() - rectangle.getWidth() / 2 - deplacement == boss.rectangle.getX()
                && circle.getCenterY() - boss.rectangle.getHeight() + circle.getRadius() == boss.rectangle.getY())
                || (circle.getCenterX() - rectangle.getWidth() / 2 - (deplacement * 2) == boss.rectangle.getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == boss.rectangle.getY()
              && cptDroite == 0)) {
            System.out.println(hp);

            boss.hp  -= this.damages ;
            boss.setViePersonnage(hp);
            System.out.println(hp);

        }

        if ((circle.getCenterX() - rectangle.getWidth() /2 == boss.rectangle.getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == boss.rectangle.getY()) ){
            System.out.println(hp);

            boss.hp -= this.damages ;
            boss.setViePersonnage(hp);
            System.out.println(hp);

        }

        if (( circle.getCenterY() + circle.getRadius() == rectangle.getY() && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX())
                || circle.getCenterY() + circle.getRadius() + deplacement == rectangle.getY() && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX()
                && cptBas == 0) {
            this.hp-=this.damages ;
            this.setViePersonnage(hp);
        }
        if ((circle.getCenterY() + circle.getRadius() - deplacement - rectangle.getHeight() == rectangle.getY()
                && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX()) ||
                (circle.getCenterY() + circle.getRadius() - (deplacement * 2) - rectangle.getHeight() == rectangle.getY()
                        && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX() && cptHaut == 0 )) {
            this.hp -=this.damages ;
            this.setViePersonnage(hp);
        }

        if (( circle.getCenterX() + rectangle.getWidth() / 2 == rectangle.getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()) || (circle.getCenterX() + rectangle.getWidth() / 2 + deplacement == rectangle.getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()
                && cptGauche == 0)) {
            this.hp -=this.damages ;
            this.setViePersonnage(hp);
        }

        if ((circle.getCenterX() - rectangle.getWidth() / 2 - deplacement == rectangle.getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()) || (circle.getCenterX() - rectangle.getWidth() / 2 - (deplacement * 2) == rectangle.getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()
                && cptDroite == 0)) {
            this.hp-=this.damages ;
            this.setViePersonnage(hp);
        }

        if ((circle.getCenterX() - rectangle.getWidth() /2 == rectangle.getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()) ){
            this.hp -=this.damages ;
            this.setViePersonnage(hp);
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
         this.viePersonnage.setWidth(hp*3);
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
