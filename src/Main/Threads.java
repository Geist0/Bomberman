package Main;

import Hero.Bot;
import Hero.Personnages;

public class Threads extends Thread {
    public void run(){
        try{
            System.out.println("coucou");
            Personnages boss= Main.getBoss();
            Bot.enMarcheBot(boss);
        }
        catch (Exception e)
        {
            System.out.println ("Exception is caught");
        }
    }
}
