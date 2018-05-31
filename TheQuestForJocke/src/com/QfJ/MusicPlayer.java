package com.QfJ;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	public void play(String deantown) throws Exception {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(deantown));
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
	}
}
