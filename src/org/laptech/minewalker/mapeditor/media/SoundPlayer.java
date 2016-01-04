package org.laptech.minewalker.mapeditor.media;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;

/**
 * Played sounds concurrently
 * @author rlapin
 */
public class SoundPlayer {
    private static SoundPlayer soundPlayer = new SoundPlayer();
    private boolean isPlaying = false;
    private SoundPlayer(){

    }
    public void play(InputStream inputStream){
        if(!isPlaying){
            isPlaying = true;
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            } catch (UnsupportedAudioFileException | IOException e) {
            }
            System.out.println(3);
            Clip clip;
            try {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();

            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
            isPlaying = false;
        }
    }
    /**
     * Play sound from input stream
     * @param inputStream
     */
    public static void playsound(InputStream inputStream){
        Executors.newSingleThreadExecutor().submit(()->soundPlayer.play(inputStream));
    }
}
