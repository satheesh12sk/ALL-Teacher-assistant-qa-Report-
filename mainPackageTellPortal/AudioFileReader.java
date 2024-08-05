
package mainPackageTellPortal;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.testng.annotations.Test;


public class AudioFileReader {
	
	
	@Test
	public static void readAudiFile(String filename)
	{
		
	   // Path to the audio file
    File audioFile = new File("C:\\Users\\ADMIN\\NavaDhiti_WorkSpace\\TellPortalNavaDhiti\\src\\test\\resources\\"+filename);

    try {
    	
    	
    	
        // Get an audio input stream from the file
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        
        

        // Get a clip resource
        Clip clip = AudioSystem.getClip();

        // Open the clip with the audio stream
        clip.open(audioStream);

        // Start playing the audio
        clip.start();
        
        Thread.sleep(2000);
        clip.drain();

        // Keep the program running until the audio finishes
        System.out.println("Playing audio...");
        while (clip.isRunning()) {
            Thread.sleep(5000); // Sleep for 1 second
        }
        
        clip.stop();
        
        System.out.println("Audio playback completed.");
    } catch (UnsupportedAudioFileException e) {
        System.err.println("The audio file format is not supported.");
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println("An I/O error occurred.");
        e.printStackTrace();
    } catch (Exception e) {
        System.err.println("An error occurred.");
        e.printStackTrace();
    }
}
}


