package com.QfJ.characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.QfJ.*;
import com.QfJ.graphics.Screen;

public abstract class Person {
	public int hp = 100;
	public double xPos = 0;
	public double yPos = 0;
	
	double speed = 6;
	double composantSpeed = speed / Math.sqrt(2);
	
	public boolean up = false;
	public boolean down = false;
	public boolean right = false;
	public boolean left = false;
	public boolean smile = false;
	
	public boolean isForeground = false;
	
	String name;
	BufferedImage playerImage;
	BufferedImage hpImage;
	
	public Person(String name, BufferedImage playerImage, int xPos, int yPos) {
		this.name = name;
		this.playerImage = playerImage;
		this.xPos = xPos;
		this.yPos = yPos;
		
		hpImage = new BufferedImage(200, 20, BufferedImage.TYPE_INT_RGB);
	}
	
	public void render(Screen screen) {
		screen.renderImage(playerImage, (int)xPos, (int)yPos, 1, 1);
	}
	
	public int getWidth(){
		return playerImage.getWidth();
	}
	public int getHeight(){
		return playerImage.getHeight();
	}
	public BufferedImage getPlayerImage() {
		return playerImage;
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

		if(yPos > (Game.HEIGHT - playerImage.getHeight())){
			yPos = Game.HEIGHT - playerImage.getHeight();
		}
		if(xPos < 0) {
			xPos = 0;
		}
		if(xPos > (Game.WIDTH - playerImage.getWidth())){
			xPos = Game.WIDTH - playerImage.getWidth();
		}
	}
}
