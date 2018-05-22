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
	
	public Xiange(int xPos, int yPos) {
		super("Xiange", xPos, yPos);
		
		playerImage = Game.loadImage("xiange.png");
		playerImageTemp = playerImage;
		hpImage = new BufferedImage(150, 15, BufferedImage.TYPE_INT_RGB);

		leftImage = Game.loadImage("xiangeLeft.png");
		walkLeftImage = Game.loadImage("xiangeWalkLeft.png");
		rightImage = Game.loadImage("xiangeRight.png");
		walkRightImage = Game.loadImage("xiangeWalkRight.png");
	}
	
	public void speak(String line, Screen screen) {
		boxImage = speechBox.getTextImage(line);

		screen.renderImage(boxImage, (int)xPos + 50, (int)yPos - 27, 1, 1);
	}
	
	public void render(Screen screen) {
		screen.renderImage(playerImage, (int)xPos, (int)yPos, 1, 1);
	}
	
	public void renderHp(Screen screen) {
		screen.renderImage(renderHpImage(), 5, Game.HEIGHT - 20, 1, 1);
	}
	
	private BufferedImage renderHpImage() {
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
