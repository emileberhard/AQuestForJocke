package com.QfJ.characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.QfJ.*;
import com.QfJ.graphics.Screen;

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
	
	public void render(Screen screen) {
		screen.renderImage(playerImage, (int)xPos, (int)yPos, 1, 1);
	}
	
	public void renderHp(Screen screen) {
		screen.renderImage(renderHp(), 10, Game.HEIGHT - 30, 1, 1);
	}
	
	private BufferedImage renderHp() {
		Graphics2D graphics = hpImage.createGraphics();
		graphics.setPaint(Color.lightGray);
		graphics.fill(new Rectangle2D.Double(0, 0, hpImage.getWidth(), hpImage.getHeight()));
		graphics.setPaint(Color.RED);
		graphics.fill(new Rectangle2D.Double(0, 0, hp * 2, hpImage.getHeight()));
		graphics.setPaint(Color.WHITE);
		graphics.setFont(new Font("Arial", Font.PLAIN, 10));
		graphics.drawString("HP: " + Integer.toString(hp), 13, hpImage.getHeight()/2+4);
		graphics.dispose();
		return hpImage;
	}
}
