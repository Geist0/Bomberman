package Hero;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;



public class Bombe extends Circle {
    private String nom ;
    private float damage ;
    private Circle circle ;
    private static final int radius = 18 ;
    private Image image ;

    public Bombe(String nom) {
        final Circle circle = new Circle() ;
        circle.setRadius(radius);
        this.nom = nom ;
    }



    public float getDamage() {
        return damage;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public String getNom()  {
        return nom ;
    }
}
