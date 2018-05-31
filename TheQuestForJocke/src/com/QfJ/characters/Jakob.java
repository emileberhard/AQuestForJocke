package com.QfJ.characters;

import java.awt.image.BufferedImage;

import com.QfJ.Game;
import com.QfJ.Rectangle;

public class Jakob extends Person{

	public Jakob(int xPos, int yPos) {
		super("Jakob", xPos, yPos);
		
		playerImage = Game.loadImage("jakob.png");
		playerImageTemp = playerImage;
		hitBox = new Rectangle(xPos, yPos + this.getHeight() - 20, playerImage.getWidth(), 20);
	}
}
