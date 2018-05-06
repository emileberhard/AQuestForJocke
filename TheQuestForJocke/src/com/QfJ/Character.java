package com.QfJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Character {
	int hp = 100;
	double xPos = 0;
	double yPos = 0;
	
	double speed = 6;
	double composantSpeed = speed / Math.sqrt(2);
	
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
	boolean smile = false;
	
	String name;
	BufferedImage playerImage;
	BufferedImage hpImage;
	
	public Character(String name, BufferedImage playerImage) {
		this.name = name;
		this.playerImage = playerImage;
		System.out.println(composantSpeed);
	}
	
	public BufferedImage getPlayerImage() {
		return playerImage;
	}
	
	public BufferedImage getHpImage() {
		hpImage = new BufferedImage(200, 20, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = hpImage.createGraphics();
		graphics.setPaint(Color.lightGray);
		graphics.fill(new Rectangle2D.Double(0, 0, hpImage.getWidth(), hpImage.getHeight()));
		graphics.setPaint(Color.RED);
		graphics.fill(new Rectangle2D.Double(0, 0, hp * 2, hpImage.getHeight()));
		graphics.setPaint(Color.WHITE);
		graphics.setFont(new Font("Arial", Font.PLAIN, 10));
		graphics.drawString("HP: " + Integer.toString(hp), 13, hpImage.getHeight()/2+4);
		return hpImage;
	}
	
	public void move() {
		
		if(right) {
			if(up) {
				xPos += composantSpeed;	
				yPos -= composantSpeed;
			}else if(down){
				xPos += composantSpeed;
				yPos += composantSpeed;
			}else {
				xPos += speed;
			}
		}
		else if(left) {
			if(up) {
				xPos -= composantSpeed;
				yPos -= composantSpeed;
			}else if(down){
				xPos -= composantSpeed;
				yPos += composantSpeed;
			}else {
				xPos -= speed;
			}
		}
		else if(up) {
			yPos -= speed;
		}
		else if(down) {
			yPos += speed;
		}
		
		if(yPos < 0) {
			yPos = 0;
		} 

		if(yPos > (Game.height - playerImage.getHeight())){
			yPos = Game.height - playerImage.getHeight();
		}
		if(xPos < 0) {
			xPos = 0;
		}
		if(xPos > (Game.width - playerImage.getWidth())){
			xPos = Game.width - playerImage.getWidth();
		}
	}
}
