package Main.media;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static final Sound fire = new Sound("Main/media/bomb.wav");
    private AudioClip clip;

    public Sound(String fileName){
        try{
            clip = Applet.newAudioClip(Sound.class.getResource(fileName));
        }catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void play() {
        try{
            new Thread(){
                public void Run(){
                    clip.play();
                }
            }.start();
        }catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}