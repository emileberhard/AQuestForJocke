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
	int xPos = 0;
	int yPos = 0;
	
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
				xPos +=1.4142;	
				yPos -= 1.4142;
			}else if(down){
				xPos += 1.4142;
				yPos += 1.4142;
			}else {
				xPos += 2;
			}
		}
		else if(left) {
			if(up) {
				xPos -= 1.4142;
				yPos -= 1.4142;
			}else if(down){
				xPos -= 1.4142;
				yPos += 1.4142;
			}else {
				xPos -= 2;
			}
		}
		else if(up) {
			yPos -= 2;
		}
		else if(down) {
			yPos += 2;
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
