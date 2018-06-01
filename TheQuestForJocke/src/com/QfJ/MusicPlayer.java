package com.QfJ;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicPlayer {
	public void play(String sound) throws Exception {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Game.class.getClassLoader().getResourceAsStream(sound));
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);	
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-23.0f);
		clip.start();
	}
}
