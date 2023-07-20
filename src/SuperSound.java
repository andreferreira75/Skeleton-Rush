import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SuperSound {

    protected URL soundURL[] = new URL[10]; // Array of sounds

    // Add sounds here
    public SuperSound() throws MalformedURLException {

        soundURL[0] = new File("resources/Game Music.wav").toURL();
        soundURL[1] = new File("resources/Skeleton Dead.wav").toURL();
        soundURL[2] = new File("resources/Spooky Menu.wav").toURL();

    }
}
