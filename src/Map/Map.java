package Map;

import Hero.Personnages;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Map{
    static private Case[] listeCase;
    static private final int MAX=169;
    static Rectangle[] mapRectangle = new Rectangle[169] ;
    static public Case[] creation(){
        listeCase=new Case[MAX];
        return listeCase;
    }

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

}
