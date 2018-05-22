package com.QfJ.characters;

import java.awt.image.BufferedImage;

import com.QfJ.*;
import com.QfJ.graphics.Screen;

public abstract class Person {
	public int hp = 100;
	public double xPos = 0;
	public double yPos = 0;
	private int time = 0;
	
	double speed = 2;
	double composantSpeed = speed / Math.sqrt(2);
	
	public boolean up = false;
	public boolean down = false;
	public boolean right = false;
	public boolean left = false;
	
	String name;
	BufferedImage hpImage;
	BufferedImage playerImage;
	BufferedImage playerImageTemp;
	BufferedImage leftImage;
	BufferedImage rightImage;
	BufferedImage walkLeftImage;
	BufferedImage walkRightImage;
	Rectangle playerRect;
	
	public Person(String name, int xPos, int yPos) {
		this.name = name;
		this.xPos = xPos;
		this.yPos = yPos;
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
	
	public void animate() {
		if(right) {
			if(time < 10) {
				if(!playerImage.equals(walkRightImage)) {
					playerImage = walkRightImage;
				}
			}else{
				if(!playerImage.equals(rightImage)) {
					playerImage = rightImage;
				}
				if(time > 20) 
					time = 0;
			}
		}else if(left) {
			if(time < 10) {
				if(!playerImage.equals(walkLeftImage)) {
					playerImage = walkLeftImage;
					xPos -= 5;
				}
			}else{
				if(!playerImage.equals(leftImage)) {
					playerImage = leftImage;
					xPos += 5;
				}
				if(time > 20) 
					time = 0;
			}
		}else if(up || down){
			if(!playerImage.equals(playerImageTemp)) {
				playerImage = playerImageTemp;
			}
		}
		
		time++;
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
