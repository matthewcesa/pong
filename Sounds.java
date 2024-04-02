package gui;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class Sounds {

    Clip clip;
    String[] paths = new String[30];
    Long currentFrame;
    public String currentFilePath;

    public Sounds(){

        this.paths[0] = "menu theme.wav";
        this.paths[1] = "bruh.wav";
        this.paths[2] = "bonk.wav";
        this.paths[3] = "options theme.wav";
        this.paths[4] = "Death by glamour.wav";
        this.paths[5] = "settings theme.wav";
        this.paths[6] = "game theme.wav";
        this.paths[7] = "select.wav";

    }

    public void playMusic(int i){
        String location = this.paths[i];
        this.currentFilePath = location;
        try{
            File musicPath = new File(location);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                this.clip = AudioSystem.getClip();
                this.clip.open(audioInput);
                this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void playSFX(int i){
        String location = this.paths[i];
        try{
            File musicPath = new File(location);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                this.clip = AudioSystem.getClip();
                this.clip.open(audioInput);
                this.clip.start();
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void stopMusic(){
        this.currentFilePath="";
        this.clip.stop();
    }

    public void pause()  { 
        this.currentFrame = this.clip.getMicrosecondPosition(); 
        clip.stop(); 
    }

    public void resume(int i) { 
        clip.close();  
        String location = this.paths[i];
        File musicPath = new File(location);
        try{
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                this.clip = AudioSystem.getClip();
                this.clip.open(audioInput);
            }
            clip.setMicrosecondPosition(currentFrame); 
            clip.start(); 
        } catch(Exception e){
            System.out.println(e);
        }
    } 

}
