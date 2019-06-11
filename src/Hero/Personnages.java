package Hero;

import Main.Main;
import Map.Map;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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

public class Personnages  {
    int cptBoss= 0 ;
    private String nom;
    private int hp ;
    private Circle circle ;
    private Circle circle2 ;
    private ImagePattern[] sprite;
    private int damages;
    private Rectangle viePersonnage = new Rectangle() ;
    private BooleanProperty enMarcheUp = new SimpleBooleanProperty(true) ;
    private BooleanProperty enMarcheBombe = new SimpleBooleanProperty(true) ;
    private BooleanProperty enMarcheBombe2 = new SimpleBooleanProperty(true) ;
    final static double  deplacement = 64 ;
    static Image grassImage = ImageLoader.get().load("terrain4.png")  ;
    static ImagePattern grass = new ImagePattern(grassImage);
    static private BooleanProperty bombeActive = new SimpleBooleanProperty(true) ;
    private Rectangle rectangle = new Rectangle(15, 15, Personnages.getDeplacement(), Personnages.getDeplacement()) ;
    private boolean vivant;






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
            this.vivant=true;


    }
    public void creerImage(String nom){
        Image ihaut1 = ImageLoader.get().load(nom+"Haut1.png") ;
        Image ihaut2 = ImageLoader.get().load(nom+"Haut2.png") ;
        Image idroite1 = ImageLoader.get().load(nom+"Droite1.png") ;
        Image idroite2 = ImageLoader.get().load(nom+"Droite2.png") ;
        Image ibas1 = ImageLoader.get().load(nom+"Bas1.png") ;
        Image ibas2 = ImageLoader.get().load(nom+"Bas2.png") ;
        Image igauche1 = ImageLoader.get().load(nom+"Gauche1.png") ;
        Image igauche2 = ImageLoader.get().load(nom+"Gauche2.png") ;
        Image ibombe = ImageLoader.get().load("bomb"+nom+".png")  ;

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
                          if (Main.getHero().rectangle.getY() > 0 + Main.getHero().rectangle.getHeight() && Main.getHero().enMarcheUp.getValue() && isEmpty("UP", Main.getHero().rectangle) ) {
                              Timeline timeline = new Timeline();
                              Main.getHero().enMarcheUp.set(false);
                              timeline.getKeyFrames().addAll(
                                      new KeyFrame(Duration.ZERO, new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[0])),
                                      new KeyFrame(Duration.millis(150), new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[1])),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[0])),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().enMarcheUp.asObject(), true)),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().rectangle.yProperty(), (int) Main.getHero().rectangle.getY() - deplacement))
                              );
                              timeline.play();


                          }
                          break;
                      case RIGHT:
                          if (Main.getHero().rectangle.getX() < scene.getWidth() - Main.getHero().rectangle.getWidth() - deplacement && Main.getHero().enMarcheUp.getValue() && isEmpty("RIGHT", Main.getHero().rectangle)) {
                              Timeline timeline2 = new Timeline();
                              Main.getHero().enMarcheUp.set(false);
                              timeline2.getKeyFrames().addAll(
                                      new KeyFrame(Duration.ZERO, new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[2] )),
                                      new KeyFrame(Duration.millis(150), new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[3])),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[2])),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().enMarcheUp.asObject(), true)),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().rectangle.xProperty(), Main.getHero().rectangle.getX() + deplacement))
                              );
                              timeline2.play();
                          }

                          break;
                      case DOWN:

                          if (Main.getHero().rectangle.getY() < scene.getHeight() - Main.getHero().rectangle.getHeight() - deplacement && Main.getHero().enMarcheUp.getValue() && isEmpty("DOWN", Main.getHero().rectangle)) {
                              Timeline timeline3 = new Timeline();
                              Main.getHero().enMarcheUp.set(false);
                              timeline3.getKeyFrames().addAll(
                                      new KeyFrame(Duration.ZERO, new KeyValue(Personnages.this.rectangle.fillProperty(),sprite[4])),
                                      new KeyFrame(Duration.millis(150), new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[5])),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[4])),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().rectangle.yProperty(), Main.getHero().rectangle.getY() + deplacement)),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().enMarcheUp.asObject(), true))
                              );
                              timeline3.play();


                          }
                          break;
                      case LEFT:
                          if (Main.getHero().rectangle.getX() > 0 + deplacement && Main.getHero().enMarcheUp.getValue() && isEmpty("LEFT", Main.getHero().rectangle)) {
                              Timeline timeline4 = new Timeline();
                              Main.getHero().enMarcheUp.set(false);
                              timeline4.getKeyFrames().addAll(
                                      new KeyFrame(Duration.ZERO, new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[6])),
                                      new KeyFrame(Duration.millis(150), new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[7])),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().rectangle.fillProperty(),sprite[6])),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().rectangle.xProperty(), (Main.getHero().rectangle.getX() - deplacement))),
                                      new KeyFrame(Duration.millis(300), new KeyValue(Main.getHero().enMarcheUp.asObject(), true))
                              );
                              timeline4.play();

                          }
                          break;
                      case E:
                          if (Main.getHero().enMarcheBombe.getValue() && Main.getHero().enMarcheUp.getValue()) {
                              Main.getHero().circle.setCenterX(Main.getHero().rectangle.getX() + Main.getHero().rectangle.getWidth()/2);
                              Main.getHero().circle.setCenterY(Main.getHero().rectangle.getY() + Main.getHero().rectangle.getHeight()-circle.getRadius());
                              Main.getHero().circle.opacityProperty().set(1);
                              Main.getHero().enMarcheBombe.set(false);
                              Main.getHero().enMarcheBombe2.set(false);
                              Timeline timeline5 = new Timeline();

                              timeline5.getKeyFrames().addAll(
                                      new KeyFrame(Duration.ZERO, new KeyValue(Main.getHero().circle.opacityProperty(), 1)),
                                      new KeyFrame(Duration.millis(1499), new KeyValue(Main.getHero().circle.opacityProperty(), 1)),
                                      new KeyFrame(Duration.millis(1500), new KeyValue(Main.getHero().circle.opacityProperty(), 0)),
                                      new KeyFrame(Duration.millis(1000), new KeyValue(Main.getHero().enMarcheBombe2.asObject(), true)),
                                      new KeyFrame(Duration.millis(1500), e -> bombeExplosion(Main.getHero().circle, Main.getBoss()) ),
                                      new KeyFrame(Duration.millis(2000), new KeyValue(Main.getHero().enMarcheBombe.asObject(), true))

                              );
                              timeline5.play();
                          }
                          if (Main.getHero().enMarcheBombe2.getValue() && Main.getHero().enMarcheUp.getValue()) {
                              Main.getHero().circle2.setCenterX( Main.getHero().rectangle.getX() + Main.getHero().rectangle.getWidth()/2);
                              Main.getHero().circle2.setCenterY( Main.getHero().rectangle.getY() + Main.getHero().rectangle.getHeight()-circle.getRadius());
                              Main.getHero().circle2.opacityProperty().set(1);
                              Main.getHero().enMarcheBombe.set(false);
                              Main.getHero().enMarcheBombe2.set(false);
                              Timeline timeline6 = new Timeline();

                              timeline6.getKeyFrames().addAll(
                                      new KeyFrame(Duration.ZERO, new KeyValue(Main.getHero().circle2.opacityProperty(), 1)),
                                      new KeyFrame(Duration.millis(1499), new KeyValue(Main.getHero().circle2.opacityProperty(), 1)),
                                      new KeyFrame(Duration.millis(1500), new KeyValue(Main.getHero().circle2.opacityProperty(), 0)),
                                      new KeyFrame(Duration.millis(1000), new KeyValue(Main.getHero().enMarcheBombe.asObject(), true)),
                                      new KeyFrame(Duration.millis(1500), e -> bombeExplosion(Main.getHero().circle2,Main.getBoss()) ),
                                      new KeyFrame(Duration.millis(2000), new KeyValue(Main.getHero().enMarcheBombe2.asObject(), true))
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

            }
            if (circle.getCenterY() + circle.getRadius() == i.getY() && circle.getCenterX() - i.getWidth() / 2 == i.getX()
                    && i.getFill() == Map.getObstacle1()) {
                cptBas++;
            }

            if (circle.getCenterY() + circle.getRadius() - deplacement - i.getHeight() == i.getY() && circle.getCenterX() - i.getWidth() / 2 == i.getX() && i.getFill() == Map.getObstacle()) {
                i.setFill(grass);
                cptHaut++;
            }
            if (circle.getCenterY() + circle.getRadius() - deplacement - i.getHeight() == i.getY()
                    && circle.getCenterX() - i.getWidth() / 2 == i.getX() && i.getFill() == Map.getObstacle1()) {
                cptHaut++;
            }

            if (circle.getCenterX() + i.getWidth() / 2 == i.getX() && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY() && i.getFill() == Map.getObstacle()) {
                i.setFill(grass);
                cptGauche++;
            }
            if (circle.getCenterX() + i.getWidth() / 2 == i.getX()
                    && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY() && i.getFill() == Map.getObstacle1()) {
                cptGauche++;
            }

            if (circle.getCenterX() - i.getWidth() / 2 - deplacement == i.getX() && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY() && i.getFill() == Map.getObstacle()) {
                i.setFill(grass);
                cptDroite++;
            }
            if (circle.getCenterX() - i.getWidth() / 2 - deplacement == i.getX()
                    && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY() && i.getFill() == Map.getObstacle1()) {
                cptDroite++;
            }


        }
        for (Rectangle i : Map.getMap()) {
            if (circle.getCenterY() + circle.getRadius() + deplacement == i.getY() && circle.getCenterX() - i.getWidth() / 2 == i.getX()
                    && i.getFill() == Map.getObstacle() && cptBas == 0) {
                i.setFill(grass);

            }

            if (circle.getCenterY() + circle.getRadius() - (deplacement * 2) - i.getHeight() == i.getY()
                    && circle.getCenterX() - i.getWidth() / 2 == i.getX() && i.getFill() == Map.getObstacle() && cptHaut == 0) {
                i.setFill(grass);

            }

            if (circle.getCenterX() + i.getWidth() / 2 + deplacement == i.getX()
                    && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY()
                    && i.getFill() == Map.getObstacle() && cptGauche == 0) {
                i.setFill(grass);

            }

            if (circle.getCenterX() - i.getWidth() / 2 - (deplacement * 2) == i.getX()
                    && circle.getCenterY() - i.getHeight() + circle.getRadius() == i.getY()
                    && i.getFill() == Map.getObstacle() && cptDroite == 0) {
                i.setFill(grass);
            }

        }
        if ((circle.getCenterY() + circle.getRadius() <= boss.getRectangle().getY() && circle.getCenterY() + circle.getRadius() + deplacement >= boss.getRectangle().getY() && circle.getCenterX() - rectangle.getWidth() / 2 == boss.getRectangle().getX())
                || circle.getCenterY() + circle.getRadius() + deplacement <= boss.getRectangle().getY() && circle.getCenterY() + circle.getRadius() + (deplacement * 2) > boss.getRectangle().getY() && circle.getCenterX() - rectangle.getWidth() / 2 == boss.getRectangle().getX()
                && cptBas == 0) {

            boss.hp -= this.damages;
            boss.setViePersonnage(boss.hp);


        } else if ((circle.getCenterY() + circle.getRadius() - deplacement - rectangle.getHeight() <= boss.getRectangle().getY() && circle.getCenterY() + circle.getRadius() - deplacement >= boss.getRectangle().getY()
                && circle.getCenterX() - boss.getRectangle().getWidth() / 2 == boss.getRectangle().getX()) ||
                (circle.getCenterY() + circle.getRadius() - (deplacement * 2) - rectangle.getHeight() <= boss.getRectangle().getY() && circle.getCenterY() + circle.getRadius() - (deplacement * 2) >= boss.getRectangle().getY()
                        && circle.getCenterX() - rectangle.getWidth() / 2 == boss.getRectangle().getX() && cptHaut == 0)) {

            boss.hp -= this.damages;
            boss.setViePersonnage(boss.hp);


        } else if ((circle.getCenterX() + rectangle.getWidth() / 2 <= boss.getRectangle().getX() && circle.getCenterX() + rectangle.getWidth() / 2 + deplacement >= boss.getRectangle().getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == boss.getRectangle().getY())
                || (circle.getCenterX() + rectangle.getWidth() / 2 + deplacement >= boss.getRectangle().getX() && circle.getCenterX() + rectangle.getWidth() / 2 + deplacement * 2 >= boss.getRectangle().getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == boss.getRectangle().getY()
                && cptGauche == 0)) {

            boss.hp -= this.damages;
            boss.setViePersonnage(boss.hp);


        } else if ((circle.getCenterX() - rectangle.getWidth() / 2 - deplacement <= boss.getRectangle().getX() && boss.getRectangle().getX() <= circle.getCenterX() - rectangle.getHeight() / 2
                && circle.getCenterY() - boss.getRectangle().getHeight() + circle.getRadius() == boss.getRectangle().getY())
                || (circle.getCenterX() - rectangle.getWidth() / 2 - (deplacement * 2) <= boss.getRectangle().getX()
                && circle.getCenterX() - rectangle.getWidth() / 2 - (deplacement) >= boss.getRectangle().getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == boss.getRectangle().getY()
                && cptDroite == 0)) {

            boss.hp -= this.damages;
            boss.setViePersonnage(boss.hp);


        } else if ((circle.getCenterX() - rectangle.getWidth() / 2 == boss.rectangle.getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == boss.rectangle.getY())) {


            boss.hp -= this.damages;
            boss.setViePersonnage(boss.hp);


        }

        if ((circle.getCenterY() + circle.getRadius() <= rectangle.getY() && circle.getCenterY() + circle.getRadius() + deplacement >= rectangle.getY() && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX())
                || circle.getCenterY() + circle.getRadius() + deplacement <= rectangle.getY() && circle.getCenterY() + circle.getRadius() + (deplacement * 2) > rectangle.getY() && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX()
                && cptBas == 0) {
            this.hp -= this.damages;
            this.setViePersonnage(this.hp);
        } else if ((circle.getCenterY() + circle.getRadius() - deplacement - rectangle.getHeight() <= rectangle.getY() && circle.getCenterY() + circle.getRadius() - deplacement >= rectangle.getY()
                && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX()) ||
                (circle.getCenterY() + circle.getRadius() - (deplacement * 2) - rectangle.getHeight() <= rectangle.getY() && circle.getCenterY() + circle.getRadius() - (deplacement * 2) >= rectangle.getY()
                        && circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX() && cptHaut == 0)) {
            this.hp -= this.damages;
            this.setViePersonnage(this.hp);
        } else if ((circle.getCenterX() + rectangle.getWidth() / 2 <= rectangle.getX() && circle.getCenterX() + rectangle.getWidth() / 2 + deplacement >= rectangle.getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY())
                || (circle.getCenterX() + rectangle.getWidth() / 2 + deplacement >= rectangle.getX() && circle.getCenterX() + rectangle.getWidth() / 2 + deplacement * 2 >= rectangle.getX()
                && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()
                && cptGauche == 0)) {
            this.hp -= this.damages;
            this.setViePersonnage(this.hp);
        } else if ((circle.getCenterX() - rectangle.getWidth() / 2 - deplacement <= rectangle.getX() && rectangle.getX() <= circle.getCenterX() - rectangle.getHeight() / 2 && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY())
                 || (circle.getCenterX() - rectangle.getWidth() / 2 - (deplacement * 2) <= rectangle.getX()
                && circle.getCenterX() - rectangle.getWidth() / 2 - (deplacement) >= rectangle.getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY()
                && cptDroite == 0)){

            this.hp -= this.damages;
            this.setViePersonnage(this.hp);

        } else if ((circle.getCenterX() - rectangle.getWidth() / 2 == rectangle.getX() && circle.getCenterY() - rectangle.getHeight() + circle.getRadius() == rectangle.getY())) {

            this.hp -= this.damages;
            this.setViePersonnage(this.hp);


        }

        if (Main.getBoss().getHp() <=0 ) {
            cptBoss++ ;
            if (cptBoss == 1) {
                Timeline apparitionRectangle = new Timeline();
                int rand = (int) (Math.random() * 2);
                    if(rand==0){
                        apparitionRectangle.getKeyFrames().addAll( new KeyFrame(Duration.ZERO, new KeyValue(Main.getRequin().opacityProperty(),1)),
                            new KeyFrame(Duration.millis(100000), new KeyValue(Main.getRequin().opacityProperty(),0)));

                        Main.getHero().setHp(120);
                        Main.getHero().setViePersonnage(Main.getHero().getHp());
                        Main.getBoss().setHp(Main.getBoss1().getHp());
                        Main.getBoss().setViePersonnage(Main.getBoss().getHp());
                        Main.getBoss().setSprite(Main.getBoss1().getSprite());
                        Main.getBoss().getRectangle().setFill(Main.getBoss().getSprite()[4]);
                        Main.getBoss().getCircle().setFill(Main.getBoss().getSprite()[8]);
                        Main.getBoss().getCircle2().setFill(Main.getBoss().getSprite()[8]);
                        Main.getHero().getRectangle().setY(Personnages.getDeplacement());
                        Main.getHero().getRectangle().setX(Personnages.getDeplacement());
                        Main.getBoss().getRectangle().setX(64*7);
                        Main.getBoss().getRectangle().setY(64*7);
                        apparitionRectangle.play();

                    }
                    else{
                        apparitionRectangle.getKeyFrames().addAll( new KeyFrame(Duration.ZERO, new KeyValue(Main.getTrident().opacityProperty(),1)),
                                new KeyFrame(Duration.millis(100000), new KeyValue(Main.getTrident().opacityProperty(),0)));

                        Main.getHero().setHp(100);
                        Main.getHero().setDamages(35);
                        Main.getHero().setViePersonnage(Main.getHero().getHp());
                        Main.getBoss().setHp(Main.getBoss1().getHp());
                        Main.getBoss().setViePersonnage(Main.getBoss().getHp());
                        Main.getBoss().setSprite(Main.getBoss1().getSprite());
                        Main.getBoss().getRectangle().setFill(Main.getBoss().getSprite()[4]);
                        Main.getBoss().getCircle().setFill(Main.getBoss().getSprite()[8]);
                        Main.getBoss().getCircle2().setFill(Main.getBoss().getSprite()[8]);
                        Main.getHero().getRectangle().setY(Personnages.getDeplacement());
                        Main.getHero().getRectangle().setX(Personnages.getDeplacement());
                        Main.getBoss().getRectangle().setX(64*7);
                        Main.getBoss().getRectangle().setY(64*7);
                        apparitionRectangle.play();
                        }

            }

            if (cptBoss == 2) {
                Timeline apparitionRectangle2 = new Timeline();
                apparitionRectangle2.getKeyFrames().addAll( new KeyFrame(Duration.ZERO, new KeyValue(Main.getCoeurAphrodite().opacityProperty(),1)),
                        new KeyFrame(Duration.millis(100000), new KeyValue(Main.getCoeurAphrodite().opacityProperty(),0)));


                Main.getHero().setHp(120);
                Main.getHero().setViePersonnage(Main.getHero().getHp());

                Main.getBoss().setHp(Main.getBoss2().getHp());
                Main.getBoss().setViePersonnage(Main.getBoss().getHp());
                Main.getBoss().setSprite(Main.getBoss2().getSprite());
                Main.getBoss().getRectangle().setFill(Main.getBoss().getSprite()[4]);
                Main.getBoss().getCircle().setFill(Main.getBoss().getSprite()[8]);
                Main.getBoss().getCircle2().setFill(Main.getBoss().getSprite()[8]);
                Main.getHero().getRectangle().setY(Personnages.getDeplacement());
                Main.getHero().getRectangle().setX(Personnages.getDeplacement());

                Main.getBoss().getRectangle().setX(64*7);
                Main.getBoss().getRectangle().setY(64*7);
                apparitionRectangle2.play();
            }

            if (cptBoss == 3) {

                Timeline apparitionRectangle3 = new Timeline();
                apparitionRectangle3.getKeyFrames().addAll( new KeyFrame(Duration.ZERO, new KeyValue(Main.getEclair().opacityProperty(),1)),
                        new KeyFrame(Duration.millis(100000), new KeyValue(Main.getEclair().opacityProperty(),0)));

                Main.getHero().setHp(100);
                Main.getHero().setViePersonnage(Main.getHero().getHp());

                Main.getBoss().setHp(Main.getBoss0().getHp());
                Main.getBoss().setViePersonnage(Main.getBoss().getHp());
                Main.getBoss().setSprite(Main.getBoss0().getSprite());
                Main.getBoss().getRectangle().setFill(Main.getBoss().getSprite()[4]);
                Main.getBoss().getCircle().setFill(Main.getBoss().getSprite()[8]);
                Main.getBoss().getCircle2().setFill(Main.getBoss().getSprite()[8]);

                Main.getHero().getRectangle().setY(Personnages.getDeplacement());
                Main.getHero().getRectangle().setX(Personnages.getDeplacement());
                Main.getBoss().getRectangle().setX(64 * 7);
                Main.getBoss().getRectangle().setY(64 * 7);
                apparitionRectangle3.play();

            }

              if (cptBoss == 4) { Main.getStage().setScene(Main.getScene3());}

             }
        if (Main.getHero().hp<=0){Main.getStage().setScene(Main.getScene4());}
        }











public String getNom() {
    return nom;
}

public int getHp() {
    return hp;
}

public void setHp(int hp) {
        this.hp = hp ;
}

public void setDamages(int damages) {
        this.damages = damages;
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

public boolean getVivant(){return vivant;}

public BooleanProperty getEnMarcheBombe(){return enMarcheBombe;}
public BooleanProperty getEnMarcheBombe2(){return  enMarcheBombe2;}
public BooleanProperty getEnMarcheUp(){return enMarcheUp;}


public ImagePattern[] getSprite(){return sprite;}


public void setSprite(ImagePattern[] sprite){ this.sprite[0] = sprite[0];
    this.sprite[1] = sprite[1];
    this.sprite[2] = sprite[2];
    this.sprite[3] = sprite[3];
    this.sprite[4] = sprite[4];
    this.sprite[5] = sprite[5];
    this.sprite[6] = sprite[6];
    this.sprite[7] = sprite[7];
    this.sprite[8] = sprite[8] ;


    }

}
