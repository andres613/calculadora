package APruebas;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

public class Sounds  extends Thread{
    
    
    public static void main(String[] args) {
        
        
        try{
            AudioClip clip;
            File Url=new File("./src/recursos/Audio/beethoven_virus.wav");
            clip= Applet.newAudioClip(Url.toURI().toURL());
            clip.loop();
        }catch(Exception ex){
            System.err.println(ex+" error");
        }
        
    }
    
    /*
    @Override
    public void run(){
        try{
            AudioClip clip;
            File Url=new File("/home/debian/Desarrollo/NetBeans/NetBeansProjects/SILABACION/WordStick/src/recursos/Audio/beethoven_virus.wav");
            clip= Applet.newAudioClip(Url.toURI().toURL());
            clip.loop();
        }catch(Exception ex){
            System.err.println(ex+" error");
        }
    }
    */
    
    
    
}