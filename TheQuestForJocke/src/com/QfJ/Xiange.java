package com.QfJ;

import java.awt.image.BufferedImage;

public class Xiange extends Character{
	
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
