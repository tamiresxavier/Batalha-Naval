package Classes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	private Clip clip;
	public Audio(String path){
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),16,baseFormat.getChannels(), baseFormat.getChannels()*2, baseFormat.getSampleRate(), false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat,ais);
			try {
				clip = AudioSystem.getClip();
				clip.open(dais);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedAudioFileException | IOException e) {}
		
	}
	public void play(){
		if(clip == null)
			return;
		stop();
		clip.setFramePosition(0); 
		clip.start();
		
	}
	public void stop(){
		if(clip.isRunning()){
			clip.stop();
		}
		
	}
}