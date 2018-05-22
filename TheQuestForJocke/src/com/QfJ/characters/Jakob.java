package com.QfJ.characters;

import java.awt.image.BufferedImage;

import com.QfJ.Game;
import com.QfJ.Rectangle;

public class Jakob extends Person{

	public Jakob(int xPos, int yPos) {
		super("Jakob", xPos, yPos);
		
		playerImage = Game.loadImage("jakob.png");
		playerImageTemp = playerImage;
		playerRect = new Rectangle(xPos, yPos, playerImage.getWidth(), playerImage.getHeight());
	}
}
