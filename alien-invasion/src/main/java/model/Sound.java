package model;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * This is Sound model file for building the game.
 *
 * @author Shenquan Wang, Ran Qin
 * @version 1.0
 * @since 08/15/2021
 */
public class Sound {

    /**
     * This method is for play the sound for the game.
     * @param path the path of the sound files.
     */
    public static synchronized void playSound(final String path) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    InputStream is = this.getClass().getResourceAsStream(path);
                    BufferedInputStream buffer = new BufferedInputStream(is);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(buffer);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    /**
     * This method is for return the path of shoot sound.
     * @return the path of shoot sound.
     */
    public static String shootSound(){
        return "/assets/sounds/shoot.wav";
    }

    /**
     * This method is for return the path of alien collision sound.
     * @return the path of alien collision sound.
     */
    public static String alienCollision(){
        return "/assets/sounds/alienCollision.wav";
    }

    /**
     * This method is for return the path of opening sound.
     * @return the path of opening sound.
     */
    public static String opening(){
        return "/assets/sounds/ufo.wav";
    }

    /**
     * This method is for return the path of explosion sound.
     * @return the path of explosion sound.
     */
    public static String explosion(){
        return "/assets/sounds/explosion.wav";
    }

    /**
     * This method is for return the path of bonus sound.
     * @return the path of bonus sound.
     */
    public static String bonusSound(){
        return "/assets/sounds/bonus.wav";
    }
}