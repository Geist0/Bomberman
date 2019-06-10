package Hero;

import Main.Main;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bot {

    public static void bouger(Personnages bot, ImagePattern[] sprite) {
        int rand = (int) (Math.random() * 4);
        if (bot.getRectangle().getY() > 0 + bot.getRectangle().getHeight() && bot.getEnMarcheUp().getValue() && bot.isEmpty("UP", bot.getRectangle()) && rand == 0) {
            Timeline timeline = new Timeline();
            bot.getEnMarcheUp().set(false);
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(bot.getRectangle().fillProperty(), sprite[0])),
                    new KeyFrame(Duration.millis(150), new KeyValue(bot.getRectangle().fillProperty(), sprite[1])),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getRectangle().fillProperty(), sprite[0])),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getEnMarcheUp().asObject(), true)),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getRectangle().yProperty(), (int) bot.getRectangle().getY() - bot.getDeplacement()))
            );
            timeline.play();
            int rand2 = (int) (Math.random() * 3);
            if (rand2 >= 1) {
                poserBomb(bot);
            }
        }
        if (bot.getRectangle().getX() < 832 - bot.getRectangle().getWidth() - bot.getDeplacement() && bot.getEnMarcheUp().getValue() && bot.isEmpty("RIGHT", bot.getRectangle()) && rand == 1) {
            Timeline timeline2 = new Timeline();
            bot.getEnMarcheUp().set(false);
            timeline2.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(bot.getRectangle().fillProperty(), sprite[2])),
                    new KeyFrame(Duration.millis(150), new KeyValue(bot.getRectangle().fillProperty(), sprite[3])),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getRectangle().fillProperty(), sprite[2])),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getEnMarcheUp().asObject(), true)),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getRectangle().xProperty(), bot.getRectangle().getX() + bot.getDeplacement()))
            );
            timeline2.play();
            int rand2 = (int) (Math.random() * 3);
            if (rand2 >= 1) {
                poserBomb(bot);
            }
        }
        if (bot.getRectangle().getY() < 832 - bot.getRectangle().getHeight() - bot.getDeplacement() && bot.getEnMarcheUp().getValue() && bot.isEmpty("DOWN", bot.getRectangle()) && rand == 2) {
            Timeline timeline3 = new Timeline();
            bot.getEnMarcheUp().set(false);
            timeline3.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(bot.getRectangle().fillProperty(), sprite[4])),
                    new KeyFrame(Duration.millis(150), new KeyValue(bot.getRectangle().fillProperty(), sprite[5])),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getRectangle().fillProperty(), sprite[4])),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getRectangle().yProperty(), bot.getRectangle().getY() + bot.getDeplacement())),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getEnMarcheUp().asObject(), true))
            );
            timeline3.play();
            int rand2 = (int) (Math.random() * 3);
            if (rand2 >= 1) {
                poserBomb(bot);
            }
        }
        if (bot.getRectangle().getX() > 0 + bot.getDeplacement() && bot.getEnMarcheUp().getValue() && bot.isEmpty("LEFT", bot.getRectangle()) && rand == 3) {
            Timeline timeline4 = new Timeline();
            bot.getEnMarcheUp().set(false);
            timeline4.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(bot.getRectangle().fillProperty(), sprite[6])),
                    new KeyFrame(Duration.millis(150), new KeyValue(bot.getRectangle().fillProperty(), sprite[7])),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getRectangle().fillProperty(), sprite[6])),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getRectangle().xProperty(), (bot.getRectangle().getX() - Personnages.getDeplacement()))),
                    new KeyFrame(Duration.millis(300), new KeyValue(bot.getEnMarcheUp().asObject(), true))
            );
            timeline4.play();
            int rand2 = (int) (Math.random() * 2);
            if (rand2 >= 1) {
                poserBomb(bot);
            }
        }
    }

    public static void poserBomb(Personnages bot) {
        if (bot.getEnMarcheBombe().getValue()) {
            bot.getCircle().setCenterX(bot.getRectangle().getX() + bot.getRectangle().getWidth() / 2);
            bot.getCircle().setCenterY(bot.getRectangle().getY() + bot.getRectangle().getHeight() - bot.getCircle().getRadius());
            bot.getCircle().opacityProperty().set(1);
            bot.getEnMarcheBombe().set(false);
            bot.getEnMarcheBombe2().set(false);
            Timeline timeline = new Timeline();

            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(bot.getCircle().opacityProperty(), 1)),
                    new KeyFrame(Duration.millis(1499), new KeyValue(bot.getCircle().opacityProperty(), 1)),
                    new KeyFrame(Duration.millis(1500), new KeyValue(bot.getCircle().opacityProperty(), 0)),
                    new KeyFrame(Duration.millis(1000), new KeyValue(bot.getEnMarcheBombe2().asObject(), true)),
                    new KeyFrame(Duration.millis(1500), e -> bot.bombeExplosion(bot.getCircle(), Main.getHero())),
                    new KeyFrame(Duration.millis(2000), new KeyValue(bot.getEnMarcheBombe().asObject(), true))

            );
            timeline.play();
        }
        if (bot.getEnMarcheBombe2().getValue()) {
            bot.getCircle2().setCenterX(bot.getRectangle().getX() + bot.getRectangle().getWidth() / 2);
            bot.getCircle2().setCenterY(bot.getRectangle().getY() + bot.getRectangle().getHeight() - bot.getCircle2().getRadius());
            bot.getCircle2().opacityProperty().set(1);
            bot.getEnMarcheBombe().set(false);
            bot.getEnMarcheBombe2().set(false);
            Timeline timeline2 = new Timeline();

            timeline2.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(bot.getCircle2().opacityProperty(), 1)),
                    new KeyFrame(Duration.millis(1499), new KeyValue(bot.getCircle2().opacityProperty(), 1)),
                    new KeyFrame(Duration.millis(1500), new KeyValue(bot.getCircle2().opacityProperty(), 0)),
                    new KeyFrame(Duration.millis(1000), new KeyValue(bot.getEnMarcheBombe().asObject(), true)),
                    new KeyFrame(Duration.millis(1500), e -> bot.bombeExplosion(bot.getCircle2(), Main.getHero())),
                    new KeyFrame(Duration.millis(2000), new KeyValue(bot.getEnMarcheBombe2().asObject(), true))
            );
            timeline2.play();
        }
    }

    public static boolean bombNearby(Circle circle, Personnages bot) {
        boolean nearby = false;
        if (((circle.getCenterY() + circle.getRadius() + Personnages.getDeplacement() >= bot.getRectangle().getY()) && (circle.getCenterY() - circle.getRadius() - Personnages.getDeplacement() <= bot.getRectangle().getY())) ||
                ((circle.getCenterX() + circle.getRadius() + Personnages.getDeplacement() >= bot.getRectangle().getX()) && (circle.getCenterX() - circle.getRadius() - Personnages.getDeplacement() <= bot.getRectangle().getY()))) {
            nearby = true;
        }
        return nearby;
    }

    public static void enMarcheBot(Personnages bot) {
        int rand;
        ImagePattern[] sprite = bot.getSprite();
        while (bot.getVivant()) {
            rand = (int) (Math.random() * 100);
            if (bombNearby(bot.getCircle(), bot) || bombNearby(bot.getCircle2(), bot)) {
                if (rand <= 90) {
                    bouger(bot, sprite);
                }
            } else {
                if (rand <= 90) {
                    bouger(bot, sprite);
                }
            }
        }
    }
}
