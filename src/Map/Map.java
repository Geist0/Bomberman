package Map;

import Hero.ImageLoader;
import Hero.Personnages;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Map{
    static private Case[] listeCase;
    static private final int MAX=169;
    static Rectangle[] mapRectangle = new Rectangle[169] ;
    static public Case[] creation(){
        listeCase=new Case[MAX];
        return listeCase;
    }
    static Image mur0 = ImageLoader.get().load("mur0.png") ;
    private static ImagePattern mur0Pattern = new ImagePattern(mur0) ;
    static Image arbre = ImageLoader.get().load("grass.png") ;
    private static ImagePattern arbrePattern = new ImagePattern(arbre) ;
    static Image mur1 = ImageLoader.get().load("mur1.png") ;
    static Image mur2 = ImageLoader.get().load("mur2.png") ;
    static Image mur3 = ImageLoader.get().load("mur3.png") ;
    static Image mur4 = ImageLoader.get().load("mur4.png") ;
    static Image mur5 = ImageLoader.get().load("mur5.png") ;
    static Image mur6 = ImageLoader.get().load("mur6.png") ;
    static Image mur7 = ImageLoader.get().load("mur7.png") ;
    static Image grass = ImageLoader.get().load("terrain4.png");
    private static Image obstacle = ImageLoader.get().load("obstacle.png");
    private static ImagePattern obstaclePattern = new ImagePattern(obstacle) ;
    static final Rectangle[] map = Map.getMapRectangle() ;

    static final public Case[] creationCase(Case[] listeCase){
        for (int i=0;i<13;i++) {
            for (int j = 0; j < 13; j++) {
                if (i == 0 || i == 12 || j == 0 || j == 12 || (i % 2 == 0 && j % 2 == 0)) {
                    listeCase[i*13+j] = new Case(i * j,Color.BLACK,false);
                } else if (i==1 && (j==1 || j==2)||(i==2 && j==1)||(i==6 && j==7)||(i==7 && (j==6 || j==7 ||j==8)||(i==8 && j==7))){
                    listeCase[i*13+j] = new Terrain(i * j, Color.GREEN,true);
                }
                else {
                    int z ;
                    z = (int)(Math.random()*10) ;

                    if (z>8){
                        listeCase[i*13+j]= new Terrain( i * j,Color.GREEN,true);
                    }
                    else {
                        listeCase[i*13+j]= new Obstacle(i * j,Color.RED,false);
                    }
                }
            }
        }
        return listeCase;
    }
    public static Rectangle[] getMapRectangle() {
        Case[] cases = Map.creation();
        cases = Map.creationCase(cases);
        for (int i=0;i<169;i++){
            int x=(i/13);
            int y=(i-(x*13));
            Rectangle map = new Rectangle(x* Personnages.getDeplacement(),y*Personnages.getDeplacement(),Personnages.getDeplacement(),Personnages.getDeplacement());
            map.setFill(cases[i].getColor());
            mapRectangle[i] = map ;
        }
        return mapRectangle ;
    }

    public static void putMapImage() {
        for(Rectangle i : map ) {
            if (i.getFill().equals(Color.BLACK) && (i.getY()== i.getHeight()*12 || i.getY() == 0) && i.getX() != 0 && i.getX() != i.getWidth()*12  ) {
                i.setFill(new ImagePattern(mur4));
            }
            if(i.getFill().equals(Color.BLACK) && i.getY() == i.getHeight()*12 && i.getX() == 0 ) {
                i.setFill(new ImagePattern(mur3));
            }
            if(i.getFill().equals(Color.BLACK) && i.getY() == i.getHeight()*12 && i.getX() == i.getWidth()*12 ) {
                i.setFill(new ImagePattern(mur5));
            }
            if(i.getFill().equals(Color.BLACK) && i.getY() !=0 && i.getY()!=i.getHeight()*12 && i.getX() == 0 ) {
                i.setFill(new ImagePattern(mur2));
            }
            if(i.getFill().equals(Color.BLACK) && i.getY() ==0 && i.getX() == 0 ) {
                i.setFill(new ImagePattern(mur1));
            }
            if(i.getFill().equals(Color.BLACK) && i.getX() ==i.getHeight()*12 && i.getY() == 0 ) {
                i.setFill(new ImagePattern(mur7));
            }
            if(i.getFill().equals(Color.BLACK) && i.getY() !=0 && i.getY()!=i.getHeight()*12 && i.getX() == i.getHeight()*12 ) {
                i.setFill(new ImagePattern(mur6));
            }
            else if(i.getFill().equals(Color.BLACK)){
                i.setFill(obstaclePattern);
            }
            if (i.getFill().equals(Color.GREEN)){
                i.setFill(Personnages.getGrass());
            }
            if (i.getFill().equals(Color.RED)){
                i.setFill(arbrePattern);
            }
        }
    }

    public static Rectangle[] getMap() {
        return map ;
    }

    public static ImagePattern getObstacle() {return arbrePattern ;}
    public static ImagePattern getMur0() {return mur0Pattern ;}

}

