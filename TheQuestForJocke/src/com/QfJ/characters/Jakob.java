package com.QfJ.characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.QfJ.Game;
import com.QfJ.Rectangle;
import com.QfJ.graphics.Screen;

public class Jakob extends Person{

	public Jakob(int xPos, int yPos) {
		super("Jakob", xPos, yPos);
		
		hpImage = new BufferedImage(70, 8, BufferedImage.TYPE_INT_RGB);
		playerImage = Game.loadImage("jakob.png");
		playerImageTemp = playerImage;
		hitBox = new Rectangle(xPos, yPos + this.getHeight() - 20, playerImage.getWidth(), 20);
	}
	
	public void renderHp(Screen screen) {
		screen.renderImage(renderHpImage(), (int)xPos + playerImage.getWidth()/2 - hpImage.getWidth()/2, (int)yPos - 12, 1, 1);
	}
	
	private BufferedImage renderHpImage() {
		Graphics2D graphics = hpImage.createGraphics();
		graphics.setPaint(Color.lightGray);
		graphics.fill(new Rectangle2D.Double(0, 0, hpImage.getWidth(), hpImage.getHeight()));
		graphics.setPaint(Color.RED);
		graphics.fill(new Rectangle2D.Double(0, 0, hp * hpImage.getWidth()/100, hpImage.getHeight()));
		graphics.setPaint(Color.WHITE);
		graphics.setFont(new Font("Calibri", Font.TRUETYPE_FONT, 10));
		graphics.drawString("HP: " + Integer.toString(hp), 1, hpImage.getHeight() - 1);
		graphics.dispose();
		return hpImage;
	}
}
