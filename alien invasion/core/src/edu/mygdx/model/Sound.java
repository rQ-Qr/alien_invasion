package edu.mygdx.model;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {

    public static synchronized void playSound(final String path) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new File(path));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public static String shootSound(){
        return "./core/assets/sounds/shoot.wav";
    }

    public static String alienCollision(){
        return "./core/assets/sounds/alienCollision.wav";
    }

    public static String opening(){
        return "./core/assets/sounds/ufo.wav";
    }

    public static String explosion(){
        return "./core/assets/sounds/explosion.wav";
    }

    public static String bonusSound(){
        return "./core/assets/sounds/bonus.wav";
    }
}