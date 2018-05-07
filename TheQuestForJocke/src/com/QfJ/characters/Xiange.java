package com.QfJ.characters;

import java.awt.image.BufferedImage;

import com.QfJ.*;

public class Xiange extends Person{
	
	private TextBox speechBox = new TextBox();
	private BufferedImage boxImage;
	private BufferedImage temp;
	private BufferedImage smileImage;
	
	public Xiange(BufferedImage playerImage) {
		super("Xiange", playerImage);
		smileImage = Game.loadImage("xiangeSmile.png");
	}
	
	public BufferedImage speak(int voiceLineNum) {
		
		switch(voiceLineNum) {
		
		case 1: boxImage = speechBox.getTextImage(name + ":  Where's my main man?!");
			break;
			
		}
		return boxImage;
	}
	
	public void smile() {
		playerImage = smileImage;
	}
}