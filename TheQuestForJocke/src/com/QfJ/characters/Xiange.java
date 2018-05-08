package com.QfJ.characters;

import java.awt.image.BufferedImage;

import com.QfJ.*;

public class Xiange extends Person{
	
	private SpeechBox speechBox = new SpeechBox();
	private BufferedImage boxImage;
	private BufferedImage temp;
	
	public Xiange(BufferedImage playerImage, int xPos, int yPos) {
		super("Xiange", playerImage, xPos, yPos);
	}
	
	public BufferedImage speak(int voiceLineNum) {
		switch(voiceLineNum) {
		case 1: boxImage = speechBox.getTextImage("Where's my main man?!");
			break;
		}
		return boxImage;
	}
}
