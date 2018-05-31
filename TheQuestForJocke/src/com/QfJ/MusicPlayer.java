package com.QfJ;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	public void play(String sound) throws Exception {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Game.class.getClassLoader().getResourceAsStream(sound));
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
	}
}
