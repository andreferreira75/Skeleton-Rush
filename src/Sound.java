import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound extends SuperSound{

    //variables
    private Clip clip;

    public Sound() throws MalformedURLException {

    }

    // Method setFile define the next sound to play passing the position of the array of sounds
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace(); // Print the error for debugging purposes
        }
    }

    // Method to play the sound chose in setFile
    public void play() {
        clip.start();
    }


    // Method to loop the sound file chose in setFile
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    // Method to stop the sound in setFile
    public void stop() {
        clip.stop();
    }
}