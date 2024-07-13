package audio;

import exception.ExceptionHandler;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static utils.Constants.Audio.AUDIO_1;
import static utils.Constants.Exceptions.ERROR_4;

public class Music {
    private Clip clip;
    private AudioInputStream music;
    private static boolean isSoundOn = true;

    public Music(){
        setMusic();
    }

    public void setMusic(){
        try {
            this.music = AudioSystem.getAudioInputStream(new File(AUDIO_1));
            this.clip = AudioSystem.getClip();
            clip.open(this.music);
            loop();
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            ExceptionHandler.handleIoExceptionWithGuiMessage(ex,ERROR_4);
        }
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
        clip.close();
    }

    public void start(){
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public void toggleSound() {
            if (isSoundOn) {
                stop();
                isSoundOn = false;
            } else {
                start();
                isSoundOn = true;
            }
    }

    public boolean isIsSoundOn(){
        return isSoundOn;
    }
}
