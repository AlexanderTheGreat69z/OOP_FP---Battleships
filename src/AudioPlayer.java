import javax.sound.sampled.*;
// import java.io.File;
import java.net.URL;

public class AudioPlayer {
    private Clip clip;
    private AudioInputStream audio;

    AudioPlayer(String audioLoc){
        try{
            URL url = this.getClass().getClassLoader().getResource(audioLoc);
            // File file  = new File("./" + audioLoc);
            this.audio = AudioSystem.getAudioInputStream(url);
            this.clip = AudioSystem.getClip();
            this.clip.open(this.audio);
        } catch (Exception e){}
    }

    public void play(float volume, boolean loop){
        FloatControl gainControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue((43*volume)/50 - 80);
        if(loop){
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        this.clip.start();
    }

    public void stop(){
        this.clip.close();
        this.clip.stop();
    }
}
